import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 백준 11779번 최소비용 구하기 2
 * https://www.acmicpc.net/problem/11779
 */

public class boj11779 {
	
	static int N, M;
	static final int MAX_N = 1000;
	static ArrayList<Edge>[] adjList = new ArrayList[MAX_N+1];
	static int[] minCost = new int[MAX_N + 1];
	static int[] trace = new int[MAX_N + 1];
	static StringBuilder sb = new StringBuilder();
	
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
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine().trim());
		M = Integer.parseInt(br.readLine().trim());
		
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
			minCost[i] = Integer.MAX_VALUE;
			trace[i] = i;
		}
		
		StringTokenizer st;
		for(int from, to, cost, i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			adjList[from].add(new Edge(to, cost));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		minCost[start] = 0;
		pq.offer(start);
		int curr;
		while(!pq.isEmpty()) {
			curr = pq.poll();
			for(Edge e : adjList[curr]) {
				if (minCost[e.city] > minCost[curr] + e.cost) {
					minCost[e.city] = minCost[curr] + e.cost;
					trace[e.city]= curr;
					pq.offer(e.city);
 				}
			}
		}
		
		bw.write(minCost[end] + "\n" + backTrace(end) + "\n" + sb.toString());
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	static public int backTrace(int city) {
		if(city == trace[city]) {
			sb.append(city);
			return 1;
		}
		int count = backTrace(trace[city]) + 1;
		sb.append(" ").append(city);
		return count;
	}
}
