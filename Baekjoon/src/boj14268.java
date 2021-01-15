import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 백준 14268번 회사 문화2
 * https://www.acmicpc.net/problem/14268
 */

public class boj14268 {

	static int N, M, cnt;
	static final int MAXN = 100000;
	static long[] tree = new long[MAXN + 1];
	static int[] in = new int[MAXN + 1];
	static int[] out = new int[MAXN + 1];
	static ArrayList<Integer>[] senior = new ArrayList[MAXN + 1];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for(int i = 1; i <= N; i++) senior[i] = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		st.nextToken();
		for(int a, i = 2; i <= N; i++) {
			a = Integer.parseInt(st.nextToken());
			senior[a].add(i);
		}
		
		dfs(1);
		
		StringBuilder sb = new StringBuilder();
		for(int o, i, w, j = 0; j < M; j++) {
			st = new StringTokenizer(br.readLine());
			o = Integer.parseInt(st.nextToken());
			
			if(o == 1) {
				i = Integer.parseInt(st.nextToken());
				w = Integer.parseInt(st.nextToken());
				update(in[i], w);
				update(out[i]+1, -w);
			} else {
				i = Integer.parseInt(st.nextToken());
				sb.append(sum(in[i])).append("\n");
			}
			
		}
		
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void dfs(int i) {
		in[i] = ++cnt;
		for(int k : senior[i]) {
			dfs(k);
		}
		out[i] = cnt;
	}

	static void update(int i, int val) {
		while(i <= N) {
			tree[i] += val;
			i += (i & -i);
		}
	}
	
	static int sum(int i) {
		int ans = 0;
		while(i > 0) {
			ans += tree[i];
			i -= (i & -i);
		}
		return ans;
	}
}
