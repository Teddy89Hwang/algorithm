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
 * 백준 2325번 개코전쟁
 * https://www.acmicpc.net/problem/2325
 */

public class boj2325 {

	static int N, M;
	static ArrayList<Edge>[] edgeList;
	static int[] cost;
	static int[] pre;
	static final int INF = Integer.MAX_VALUE;
	static int rm_from, rm_to;

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
			return Integer.compare(cost, o.cost);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		edgeList = new ArrayList[N+1];
		cost = new int[N+1];
		pre = new int[1001];
		
		for(int i = 1; i <= N; i++) edgeList[i] = new ArrayList<>();
		
		for(int x, y, z, i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			edgeList[x].add(new Edge(y, z));
			edgeList[y].add(new Edge(x, z));
		}
		
		dijkstra(false);
		
		int ans = 0;
		int now = N;
		while(now != 1) {
			rm_to = now;
			rm_from = pre[now];
			ans = Math.max(ans, dijkstra(true));
			now = pre[now];
		}

		bw.write(ans + "");
		bw.flush();
		bw.close();
		br.close();
	}

	private static int dijkstra(boolean flag) {
		
		Arrays.fill(cost, INF);
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		cost[1] = 0;
		pq.offer(new Edge(1, 0));
		
		Edge curr;
		while (!pq.isEmpty()) {
			curr = pq.poll();
			if(cost[curr.city] < curr.cost) continue;
			
			for (Edge next : edgeList[curr.city]) {
				if(flag && ((curr.city == rm_from && next.city == rm_to) || (curr.city == rm_to && next.city == rm_from))) continue;
				if (curr.cost + next.cost < cost[next.city]) {
					cost[next.city] = curr.cost + next.cost;
					if (!flag) pre[next.city] = curr.city;
					pq.offer(new Edge(next.city, cost[next.city]));
				}
			}
		}
		
		return cost[N];
	}
}
