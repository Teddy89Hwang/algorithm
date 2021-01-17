import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 백준 14554번 The Other Way
 * https://www.acmicpc.net/problem/14554
 */

public class boj14554 {

	static int N, M, S, E;
	static final int MAX_N = 100000;
	static final int MOD_NUM = 1000000009;
	static ArrayList<Edge>[] adjList = new ArrayList[MAX_N+1];
	static long[] minDist = new long[MAX_N+1];
	static int[] count = new int[MAX_N+1];
	
	static class Edge implements Comparable<Edge> {
		int city;
		long dist;
		public Edge(int city, long dist) {
			super();
			this.city = city;
			this.dist = dist;
		}
		@Override
		public int compareTo(Edge o) {
			return dist-o.dist > 0? 1:-1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
			minDist[i] = Long.MAX_VALUE;
			count[i] = 0;
		}
		
		for(int a, b, c, i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			adjList[a].add(new Edge(b, c));
			adjList[b].add(new Edge(a, c));
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		minDist[S] = 0;
		count[S] = 1;
		pq.offer(new Edge(S, 0));
		Edge curr;
		while(!pq.isEmpty()) {
			curr = pq.poll();
			
			if(curr.dist > minDist[curr.city]) continue;
			
			for(Edge next : adjList[curr.city]) {
				if (minDist[next.city] > minDist[curr.city] + next.dist) {
					minDist[next.city] = minDist[curr.city] + next.dist;
					count[next.city] = count[curr.city];
					pq.offer(new Edge(next.city, minDist[next.city]));
 				} else if (minDist[next.city] == minDist[curr.city] + next.dist ) {
 					count[next.city] = (count[next.city] + count[curr.city]) % MOD_NUM;
 				}
			}
		}
		
		bw.write(""+ count[E]);
		
		bw.flush();
		bw.close();
		br.close();
	}
}
