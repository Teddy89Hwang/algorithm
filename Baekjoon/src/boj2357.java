import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 백준 2357번 최솟값과 최댓값
 * https://www.acmicpc.net/problem/2357
 */

public class boj2357 {

	static int N, M, K;
	static final int MAXN = 100000;
	static int[][] tree = new int[MAXN * 4][2];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int idx = init();
		
		for(int i = 1; i <= N; i++) Arrays.fill(tree[idx+i], Integer.parseInt(br.readLine()));
		for(int i = idx; i > 0; i--) {
			tree[i][0] = Math.min(tree[2*i][0], tree[2*i + 1][0]);
			tree[i][1] = Math.max(tree[2*i][1], tree[2*i + 1][1]);
		}
		
		for(int a, b, i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			bw.write(find(idx+a, idx+b) + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int init() {
		for(int i = 0; i < 4*N; i++) {
			tree[i][0] = Integer.MAX_VALUE;
			tree[i][1] = Integer.MIN_VALUE;
		}
		int rtn = 1;
		while(rtn < N) rtn *= 2;
		return rtn-1;
	}
	
	static String find(int start, int end) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		while(start <= end) {
			if(start % 2 == 1) {
				min = Math.min(min, tree[start][0]);
				max = Math.max(max, tree[start][1]);
			}
			if(end % 2 == 0) {
				min = Math.min(min, tree[end][0]);
				max = Math.max(max, tree[end][1]);
			}
			start = (start + 1) / 2;
			end = (end-1) / 2;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(min).append(" ").append(max);
		return sb.toString();
	}
	
}
