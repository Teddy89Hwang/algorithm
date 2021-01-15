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
 * 백준 1162번 도로포장
 * https://www.acmicpc.net/problem/1162
 */

public class boj1162 {

	static class Road implements Comparable<Road> {
		int num;
		long time;
		int paveCount;
		
		public Road(int num, long time) {
			super();
			this.num = num;
			this.time = time;
		}

		public Road(int num, long time, int paveCount) {
			super();
			this.num = num;
			this.time = time;
			this.paveCount = paveCount;
		}

		@Override
		public int compareTo(Road o) {
			return time - o.time >= 0 ? 1 : -1;
		}
	}

	static int N, M, K;
	static ArrayList<Road>[] adjList;
	static long[][] sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[N + 1];
		sum = new long[N + 1][K + 1];
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
			Arrays.fill(sum[i], Long.MAX_VALUE);
		}

		for(int a, b, t, i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			adjList[a].add(new Road(b, (long)t));
			adjList[b].add(new Road(a, (long)t));
		}
		
		PriorityQueue<Road> pq = new PriorityQueue<>();
		
		sum[1][0] = 0;
		pq.offer(new Road(1, 0, 0));
		
		Road curr;
		while (!pq.isEmpty()) {
			curr = pq.poll();
			if(curr.time > sum[curr.num][curr.paveCount]) continue;
			
			for(Road next : adjList[curr.num]) {
				
				if(sum[next.num][curr.paveCount] > sum[curr.num][curr.paveCount] + next.time) {
					sum[next.num][curr.paveCount] = sum[curr.num][curr.paveCount] + next.time;
					pq.offer(new Road(next.num, sum[next.num][curr.paveCount], curr.paveCount));
				}
				
				if(curr.paveCount < K && 
						sum[next.num][curr.paveCount + 1] > curr.time) {
					sum[next.num][curr.paveCount + 1] = curr.time;
					pq.offer(new Road(next.num, curr.time, curr.paveCount + 1));
				}
				
			}
		}
		
		long ans = Long.MAX_VALUE;
		for(int i = 0; i <= K; i++) ans = Math.min(ans, sum[N][i]);

		bw.write("" + ans);
		bw.flush();
		bw.close();
		br.close();
	}
}
