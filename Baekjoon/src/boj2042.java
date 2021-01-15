import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 2042번 구간 합 구하기
 * https://www.acmicpc.net/problem/2042
 */

public class boj2042 {

	static int N, M, K;
	static final int MAXN = 1000000;
	static long[] tree = new long[MAXN * 4];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int idx = init();
		
		for(int i = 1; i <= N; i++) tree[idx + i] = Integer.parseInt(br.readLine());
		for(int i = idx; i > 0; i--) tree[i] = tree[2*i] + tree[2*i + 1];
		
		int a, b;
		long c;
		for(int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Long.parseLong(st.nextToken());
			switch(a) {
			case 1:
				update(idx+b, c-tree[idx+b]);
				break;
			case 2:
				bw.write(""+sum(idx+b, (int)(idx+c)) + "\n");
				break;
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int init() {
		for(int i = 0; i < 4*N; i++) tree[i] = 0;
		int rtn = 1;
		while(rtn < N) rtn *= 2;
		return rtn-1;
	}
	
	static void update(int node, long value) {
		int now = node;
		while(now > 0) {
			tree[now] += value;
			now /= 2;
		}
	}
	
	static long sum(int start, int end) {
		long ans = 0;
		while(start <= end) {
			if(start % 2 == 1) ans += tree[start];
			if(end % 2 == 0) ans += tree[end];
			start = (start + 1) / 2;
			end = (end-1) / 2;
		}
		return ans;
	}
}
