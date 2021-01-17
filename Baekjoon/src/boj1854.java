import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 백준 1854번 K번째 최단경로 찾기
 * https://www.acmicpc.net/problem/1854
 */

public class boj1854 {
	
	static class Edge implements Comparable<Edge> {
		int city;
		int cost;
		public Edge(int city, int cost) {
			super();
			this.city = city;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			return cost - o.cost;
		}
		
	}
	
	static int N, M, K;
	static ArrayList<Edge>[] adjList = new ArrayList[1001];
	static int[] kCost = new int[1001];
	static int[] count = new int[1001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= N; i++) adjList[i] = new ArrayList<>();
		Arrays.fill(kCost, Integer.MAX_VALUE);
		Arrays.fill(count, 0);
		

		for(int a, b, c, i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			adjList[a].add(new Edge(b, c));
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(1, 0));
		kCost[1] = 0;
		count[1] = 0;

		Edge curr;
		while(!pq.isEmpty()) {
			curr = pq.poll();
			if (count[curr.city] >= K) continue;
			else {
				count[curr.city]++;
				kCost[curr.city] = curr.cost;
			}
			for(Edge next : adjList[curr.city]) {
				if(count[next.city] < K) {
					pq.offer(new Edge(next.city, curr.cost + next.cost));
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			if(count[i] != K) sb.append("-1");
			else sb.append(kCost[i]);
			sb.append("\n");
 		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
