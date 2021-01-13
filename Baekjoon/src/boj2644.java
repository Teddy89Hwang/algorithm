import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 2644번 촌수계산
 * https://www.acmicpc.net/problem/2644
 */

public class boj2644 {

	static int N;
	static int X, Y;
	static int M;
	static final int MAX_N = 100;
	static ArrayList<Integer>[] adjList = new ArrayList[MAX_N + 1];
	static int[] visited = new int[MAX_N + 1];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine().trim());
		for(int i = 1; i <= N; i++) adjList[i] = new ArrayList<Integer>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(br.readLine().trim());
		
		int a, b;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		Arrays.fill(visited, -1);
		Queue<Integer> queue = new LinkedList<>();
		
		visited[X] = 0;
		queue.add(X);
		int now;
		while(!queue.isEmpty()) {
			now = queue.poll();
			if (now == Y) break;
			for(int i : adjList[now]) {
				if(visited[i] == -1) {
					queue.offer(i);
					visited[i] = visited[now] + 1;
				}
			}
		}
		
		bw.write(visited[Y] + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}