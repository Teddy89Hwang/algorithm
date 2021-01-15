import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * 백준 6497번 전력난
 * https://www.acmicpc.net/problem/6497
 */

public class boj6497 {

	static int N, M;
	static ArrayList<Edge> edgeList;
	static int[] parent;
	static int[] rank;
	
	static class Edge implements Comparable<Edge> {
		int start;
		int end;
		int weight;

		Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return weight - o.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			if (M == 0 && N == 0) {
				break;
			}

			edgeList = new ArrayList<>();

			int cost = 0; // 전체 비용
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());

				edgeList.add(new Edge(x, y, z));
				cost += z;
			}
			
			Collections.sort(edgeList);
			int minCost = kruscal();

			bw.write((cost - minCost) + "\n"); 
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static int kruscal() {
		parent = new int[M];
		rank = new int[M];
		for (int i = 0; i < M; i++) {
			parent[i] = i;
		}

		int rtn = 0; 
		for (int i = 0; i < edgeList.size(); i++) {
			Edge edge = edgeList.get(i);

			if (find(edge.start) != find(edge.end)) {
				rtn += edge.weight;
				rankUnion(edge.start, edge.end);
			}
		}
		return rtn;
	}
	
	public static int find(int curr) {
		if (curr == parent[curr]) {
			return curr;
		}

		return parent[curr] = find(parent[curr]);
	}

	public static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y) {
			parent[y] = x;
		}
	}
	
	public static void rankUnion(int x, int y) {
        x = find(x);
        y = find(y);
        
        if (x == y) {
            return;
        }
       
        if (rank[x] > rank[y]) {
            parent[y] = x;
        } else { 
        	parent[x] = y;
        }
      
        if (rank[x] == rank[y]) {
            rank[x]++;
        }
    }
}
