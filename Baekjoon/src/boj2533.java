import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 백준 2533번 사회망 서비스(SNS)
 * https://www.acmicpc.net/problem/2533
 */

public class boj2533 {

	static int N;
	static ArrayList<Integer>[] graph = new ArrayList[1000001];
	static int[][] dp = new int[1000001][2];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine().trim());
		
		for(int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
		
		StringTokenizer st;
		for(int u, v, i = 0 ; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}
		
		bw.write("" + Math.min(TreeDP(0, 1, 1), TreeDP(0, 1, 0)));
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static int TreeDP(int prev, int curr, int selected) {
		
		if(dp[curr][selected] != 0) return dp[curr][selected];
		
		int res = selected == 1? 1 : 0;
		for(int next : graph[curr]) {
			if(prev == next) continue;
			if(selected == 1) {
				res += Math.min(TreeDP(curr, next, 1), TreeDP(curr, next, 0));
			} else {
				res += TreeDP(curr, next, 1);
			}
		}
		
		return dp[curr][selected] = res;
	}

}
