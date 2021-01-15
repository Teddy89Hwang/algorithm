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
 * 백준 13907번 세금
 * https://www.acmicpc.net/problem/13907
 */

public class boj13907 {

	static int N, M, K, S, D;
	static ArrayList<Edge>[] adjList = new ArrayList[1001];
	static int[][] minCost = new int[1001][1001];
	static final int INF = 30000 * 1000;
	
	static class Edge implements Comparable<Edge>{
		int city;
		int cost;
		int count;
		public Edge(int city, int cost) {
			super();
			this.city = city;
			this.cost = cost;
		}
		public Edge(int city, int cost, int visitCount) {
			super();
			this.city = city;
			this.cost = cost;
			this.count = visitCount;
		}
		@Override
		public int compareTo(Edge o) {
			return cost-o.cost;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= N; i++) adjList[i] = new ArrayList<>();
		
		for(int a, b, w, i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			adjList[a].add(new Edge(b, w));
			adjList[b].add(new Edge(a, w));
		}
		
//		minCost = new int[N+1][N+1];
		for(int i = 0; i <= N; i++) Arrays.fill(minCost[i], INF);
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		minCost[S][0] = 0;
		pq.offer(new Edge(S, 0, 0));
		
		Edge curr;
		int nextCost;
		while(!pq.isEmpty()) {
			curr = pq.poll();
			
			if(curr.count >= N) continue;
			if(minCost[curr.city][curr.count] < curr.cost) continue;
			
			for(Edge next : adjList[curr.city]) {
				nextCost = curr.cost + next.cost;
				if(isAble(next.city, curr.count, nextCost) && minCost[next.city][curr.count+1] > nextCost) {
					minCost[next.city][curr.count+1] = nextCost;
					pq.offer(new Edge(next.city, nextCost, curr.count+1));
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int ans;
		for(int tax, i = 0; i <= K; i++) {
			if(i == 0) tax = 0;
			else tax = Integer.parseInt(br.readLine().trim());

			ans = Integer.MAX_VALUE;
			for(int j = 0; j < N; j++) {
				minCost[D][j] += (j * tax);
				ans = Math.min(ans, minCost[D][j]);
			}
			sb.append(ans).append("\n");
		}
		
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static boolean isAble(int city, int count, int nextCost) {
		for(int i = count; i >= 0; i--) {
			if(minCost[city][i] <= nextCost) return false;
		}
		return true;
	}

}
