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
 * 백준 14938번 서강그라운드
 * https://www.acmicpc.net/problem/14938
 */


public class boj14938 {

	static class Info implements Comparable<Info> {
		int fieldNum;
		int distance;

		public Info(int fieldNum, int distance) {
			super();
			this.fieldNum = fieldNum;
			this.distance = distance;
		}

		@Override
		public int compareTo(Info o) {
			return distance - o.distance;
		}
	}

	static int N, M, R;
	static int[] item = new int[101];
	static ArrayList<Info>[] adjList = new ArrayList[101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
			item[i] = Integer.parseInt(st.nextToken());
		}

		for(int a, b, l, i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			adjList[a].add(new Info(b, l));
			adjList[b].add(new Info(a, l));
		}
		
		int ans = 0;
		for(int i=1; i<=N; i++) ans = Math.max(ans, dijkstra(i));

		bw.write("" + ans);
		bw.flush();
		bw.close();
		br.close();
	}

	public static int dijkstra(int start) {
		PriorityQueue<Info> pq = new PriorityQueue<>();
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[start] = 0;
		pq.offer(new Info(start, 0));
		
		Info curr;
		while (!pq.isEmpty()) {
			curr = pq.poll();
			for(Info next : adjList[curr.fieldNum]) {
				if(dist[next.fieldNum] > dist[curr.fieldNum] + next.distance) {
					dist[next.fieldNum] = dist[curr.fieldNum] + next.distance;
					pq.add(new Info(next.fieldNum, dist[next.fieldNum]));
				}
			}
		}
		
		int countItem = 0;
		for(int i = 1; i <= N; i++) {
			if(dist[i] <= M) countItem += item[i];
		}

		return countItem;
	}

}
