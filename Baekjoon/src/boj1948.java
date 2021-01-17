import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 백준 1948번 임계경로
 * https://www.acmicpc.net/problem/1948
 */

public class boj1948 {

	static class Vertex{
		int next;
		int time;
		public Vertex(int city, int time) {
			super();
			this.next = city;
			this.time = time;
		}
	}
	static int N, M;
	static final int MAXN = 10001;
	static ArrayList<Vertex>[] adj = new ArrayList[MAXN];
	static int[] maxTime = new int[MAXN];
	static int start, end;
	static boolean[] visit = new boolean[MAXN];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        for(int i = 1; i <= N; i++) {
        	adj[i] = new ArrayList<>();
        }
        
        StringTokenizer st;
        for(int i = 0, a, b, c; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	a = Integer.parseInt(st.nextToken());
        	b = Integer.parseInt(st.nextToken());
        	c = Integer.parseInt(st.nextToken());
        	adj[a].add(new Vertex(b, c));
        }
        
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        
        setMaxTime(start);
        int count = countRoute(start);
        
        
        bw.write(maxTime[start] + "\n" + count);
		bw.flush();
        bw.close();
        br.close();
	}

	private static int countRoute(int city) {
		if(visit[city]) return 0;
		visit[city] = true;
		int rtn = 0;
		for(Vertex v : adj[city]) {
			if(maxTime[city] == maxTime[v.next] + v.time) {
				rtn += countRoute(v.next) + 1;
			}
		}
		return rtn;
	}

	private static void setMaxTime(int city) {
		if (city == end) return;
		for(Vertex v : adj[city]) {
			if(maxTime[v.next] == 0) setMaxTime(v.next);
			maxTime[city] = max(maxTime[city], maxTime[v.next]+ v.time); 
		}
	}

	private static int max(int a, int b) {
		return (a > b)? a : b;
	}

}
