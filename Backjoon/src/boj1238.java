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
 * 백준 1238번 파티
 * https://www.acmicpc.net/problem/1238
 */

public class boj1238 {
	
	static int N, M, X;
	static final int MAX_N = 1000;
	static ArrayList<Edge>[] route = new ArrayList[MAX_N+1]; 
	static ArrayList<Edge>[] rtnRoute = new ArrayList[MAX_N+1];
	
	static class Edge implements Comparable<Edge> {
		int town;
		int time;
		public Edge(int town, int time) {
			super();
			this.town = town;
			this.time = time;
		}
		@Override
		public int compareTo(Edge o) {
			return time-o.time;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= N; i++) {
			route[i] = new ArrayList<>();
			rtnRoute[i] = new ArrayList<>();
		}
		
		for(int a, b, c, i = 0 ; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			route[a].add(new Edge(b, c));
			rtnRoute[b].add(new Edge(a, c));
		}
		
		int[] comeCost = Dijkstra(X, route);
		int[] rtnCost = Dijkstra(X, rtnRoute);
		int ANS = Integer.MIN_VALUE;
		for(int i = 1; i <= N; i++) {
			ANS = Math.max(ANS, comeCost[i] + rtnCost[i]);
		}
		
		bw.write("" + ANS);
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static int[] Dijkstra(int X, ArrayList<Edge>[] adjList) {
		int[] minTime = new int[N+1];
		Arrays.fill(minTime, Integer.MAX_VALUE);
		
		PriorityQueue<Edge>pq = new PriorityQueue<>();
		
		minTime[X] = 0;
		pq.offer(new Edge(X, 0));
		
		Edge curr;
		while (!pq.isEmpty()) {
			curr = pq.poll();
			for (Edge next : adjList[curr.town]) {
				if (minTime[next.town] > curr.time + next.time) {
					minTime[next.town] = curr.time + next.time;
					pq.offer(new Edge(next.town, minTime[next.town]));
				}
			}
		}
		return minTime;
	}

}
