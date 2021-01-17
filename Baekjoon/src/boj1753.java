import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 백준 1753번 최단경로
 * https://www.acmicpc.net/problem/1753
 */

public class boj1753 {
	
	static int V, E;
	static final int MAX_V = 20000;
	static ArrayList<Edge>[] adjList = new ArrayList[MAX_V+1];
	static int[] cost = new int[MAX_V+1];
	
	static class Edge implements Comparable<Edge>{
		int vertex;
		int cost;
		public Edge(int vertex, int cost) {
			super();
			this.vertex = vertex;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
			cost[i] = Integer.MAX_VALUE;
		}
		
		
		int start = Integer.parseInt(br.readLine().trim());
		
		for(int u, v, w, i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			adjList[u].add(new Edge(v, w));
		}
		
		PriorityQueue<Edge> PQ = new PriorityQueue<>();
		cost[start] = 0;
		PQ.offer(new Edge(start, 0));
		Edge curr;
		while(!PQ.isEmpty()) {
			curr = PQ.poll();
			for(Edge e : adjList[curr.vertex]) {
				if(curr.cost + e.cost < cost[e.vertex]) {
					cost[e.vertex] = curr.cost + e.cost;
					PQ.offer(new Edge(e.vertex, cost[e.vertex]));
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= V; i++) {
			if(cost[i] == Integer.MAX_VALUE) sb.append("INF").append("\n");
			else sb.append(cost[i]).append("\n");
		}
		
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
