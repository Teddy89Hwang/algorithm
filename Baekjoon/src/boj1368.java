import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * 백준 1368번 물대기
 * https://www.acmicpc.net/problem/1368
 */

public class boj1368 {

	static int N;
	
	static class Edge implements Comparable<Edge>{
		int start;
		int end;
		int cost;
		
		public Edge(int start, int end, int cost) {
			super();
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return cost - o.cost;
		}
		
	}
	static ArrayList<Edge> edgeList = new ArrayList<>();
	static int[] parent = new int[301];
	static int[] rank = new int[301];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		 
		N = Integer.parseInt(br.readLine().trim());
		
		for(int c, i = 1 ; i <= N; i++) {
			c = Integer.parseInt(br.readLine().trim());
			edgeList.add(new Edge(0, i, c));
		}
		
		for(int i = 1; i <=N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int c, j = 1; j <= N; j++) {
				c = Integer.parseInt(st.nextToken());
				if(i < j) edgeList.add(new Edge(i, j, c));
			}
		}
		
		Collections.sort(edgeList);
		
		int minCost = kruscal();

		bw.write("" + minCost);
		bw.flush();
		bw.close();
		br.close();
	}

	private static int kruscal() {
		for(int i = 1; i <= N; i++) parent[i] = i;
		Arrays.fill(rank, 0);
		
		int rtn = 0;
		for(Edge e : edgeList) {
			if(find(e.start) != find(e.end)) {
				rtn += e.cost;
				union(e.start, e.end);
			}
		}
		
		return rtn;
	}

	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return;
		
		if(rank[x] > rank[y]) {
			parent[y] = x; 
		} else {
			parent[x] = y;
		}
		
		if(rank[x] == rank[y]) {
			rank[y]++;
		}
	}

	private static int find(int curr) {
		if(curr == parent[curr]) return curr;
		return parent[curr] = find(parent[curr]);
	}
	
}
