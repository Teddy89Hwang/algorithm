import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * 백준 13306번 트리
 * https://www.acmicpc.net/problem/13306
 */

public class boj13306 {

	static int N, Q;
	static int[] input = new int[200001];
	static int[] parent = new int[200001];
	static int[][] query = new int[400000][3];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		for(int i = 2; i <= N; i++) {
			input[i] = Integer.parseInt(br.readLine().trim());
			parent[i] = i;
		}
		for(int i = 0;  i < N + Q - 1; i++) {
			st = new StringTokenizer(br.readLine());
			query[i][0] = Integer.parseInt(st.nextToken());
			query[i][1] = Integer.parseInt(st.nextToken());
			if(query[i][0] == 1) query[i][2] = Integer.parseInt(st.nextToken());
		}
		
		Stack<String> ans = new Stack<>();
		for(int i = N+Q-1; i >= 0; i--) {
			if(query[i][0] == 0) {
				union(query[i][1], input[query[i][1]]);
			} else {
				if(find(query[i][1]) == find(query[i][2])) ans.push("YES");
				else ans.push("NO");
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(!ans.isEmpty()) {
			sb.append(ans.pop()).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
		
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return;
		parent[a] = b;
	}

	private static int find(int curr) {
		if(curr == parent[curr]) return curr;
		return parent[curr] = find(parent[curr]);
	}

}

	
