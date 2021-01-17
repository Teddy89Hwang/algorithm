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
 * 백준 1504번 특정한 최단 경로
 * https://www.acmicpc.net/problem/1504
 */

public class boj1504 {

	static int N, E;
	static int v1, v2;
	static final int MAX_N = 800;
	private static final int INF = 200000000;
	static ArrayList<Edge>[] adjList = new ArrayList[MAX_N + 1];
	static int[] cost = new int[MAX_N + 1];
	static PriorityQueue<Edge> PQ = new PriorityQueue<>();

	static class Edge implements Comparable<Edge> {
		int b;
		int c;

		public Edge(int b, int c) {
			super();
			this.b = b;
			this.c = c;
		}

		@Override
		public int compareTo(Edge o) {
			return this.c - o.c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
			cost[i] = Integer.MAX_VALUE;
		}

		for (int a, b, c, i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			adjList[a].add(new Edge(b, c));
            adjList[b].add(new Edge(a, c));
		}
		
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		bw.write(solve(v1, v2) + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static int solve(int v1, int v2) {
		int case1 = getCost(v1, v2);
		int case2 = getCost(v2, v1);
		
		if(case1 >= INF && case2 >= INF) return -1;
		else return Math.min(case1, case2);
	}
	
	private static int getCost(int vertex1, int vertex2) {
		return Dijkstra(1, vertex1) + Dijkstra(vertex1, vertex2) + Dijkstra(vertex2, N);
	}

	private static int Dijkstra(int start, int end) {
		Arrays.fill(cost, INF);
		PQ.clear();
		
		cost[start] = 0;
		PQ.offer(new Edge(start, 0));
		
		Edge curr;
		while (!PQ.isEmpty()) {
			curr = PQ.poll();
			for (Edge e : adjList[curr.b]) {
				if (curr.c + e.c < cost[e.b]) {
					cost[e.b] = curr.c + e.c;
					PQ.offer(new Edge(e.b, cost[e.b]));
				}
			}
		}
		return cost[end];
	}

}
