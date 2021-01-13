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
 * 백준 15709번 정기검진
 * https://www.acmicpc.net/problem/15709
 */

public class boj15709 {

	static int N, M, B, K, Q;
	static ArrayList<Edge>[] edgeList;
	static long[][] minTime;
	static final long INF = Long.MAX_VALUE / 2;

	static class Edge implements Comparable<Edge> {
		int num;
		long time;

		public Edge(int num, long time) {
			super();
			this.num = num;
			this.time = time;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(time, o.time);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		edgeList = new ArrayList[N + M + B + 1];
		minTime = new long[B + 1][N + M + B + 1];
		for (int i = 1; i <= N + M + B; i++)
			edgeList[i] = new ArrayList<>();

		long k;
		for (int a, b, i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			k = Long.parseLong(st.nextToken());
			edgeList[a].add(new Edge(b, k));
			edgeList[b].add(new Edge(a, k));
		}

		for (int i = 1; i <= B; i++) {
			minTime[i] = dijkstra(N + M + i);
		}
		
		long ans;
		for(int s, e, i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			ans = INF;
			for(int j = 1; j <= B; j++) {
				ans = Math.min(ans, minTime[j][s] + minTime[j][e]);
			}
			if (ans == INF) ans = -1;
			bw.write(ans + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

	private static long[] dijkstra(int b) {
		long[] cost = new long[N + M + B + 1];
		Arrays.fill(cost, INF);

		PriorityQueue<Edge> pq = new PriorityQueue<>();

		cost[b] = 0;
		pq.offer(new Edge(b, 0));

		Edge curr;
		while (!pq.isEmpty()) {
			curr = pq.poll();
			if (cost[curr.num] < curr.time) continue;

			for (Edge next : edgeList[curr.num]) {
				if (curr.time + next.time < cost[next.num]) {
					cost[next.num] = curr.time + next.time;
					pq.offer(new Edge(next.num, cost[next.num]));
				}
			}
		}
		return cost;
	}

}
