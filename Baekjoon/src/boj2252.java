import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 2252번 줄 세우기
 * https://www.acmicpc.net/problem/2252
 */

public class boj2252 {

	static int N, M;
	static ArrayList<Integer>[] map;
	static int[] indegree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N+1];
		indegree = new int[N+1];
		for(int i = 0; i <= N; i++) map[i] = new ArrayList<>();
		
		for(int a, b, i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			map[a].add(b);
			indegree[b]++;
		}
		
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i = 1; i <= N; i++) {
			if(indegree[i] == 0) queue.add(i);
		}
		
		int curr;
		StringBuilder sb = new StringBuilder();
		while(!queue.isEmpty()) {
			curr = queue.poll();
			for(int next : map[curr]) {
				indegree[next]--;
				if(indegree[next] == 0) {
					queue.add(next);
				}
			}
			sb.append(curr).append(" ");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
