import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 5012번 불만 정렬
 * https://www.acmicpc.net/problem/5012
 */

public class boj5012 {

	static int N;
	static final int MAXN = 100000;
	static int[] input = new int[MAXN+1];
	static int[] a = new int[MAXN+1];
	static int[] b = new int[MAXN+1];
	static int[] tree = new int[MAXN * 4];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) input[i] = Integer.parseInt(st.nextToken());
		
		int idx = init();
		for(int i = 1;  i<= N; i++) {
			a[i] = query(idx + input[i] + 1, idx + N);
			update(idx + input[i]);
		}
		
		idx = init();
		for(int i = N;  i >= 1; i--) {
			b[i] = query(idx + 1, idx + input[i] - 1);
			update(idx + input[i]);
		}
		
		long ans = 0;
		for(int i = 1; i <= N; i++) {
			ans += (long)a[i] * (long)b[i];
		}
		
		bw.write("" + ans);
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static int query(int start, int end) {
		int rtn = 0;
		while(start <= end) {
			if (start % 2 == 1) rtn += tree[start];
			if (end % 2 == 0) rtn += tree[end];
			start = (start + 1) / 2;
			end = (end - 1) / 2;
		}
		return rtn;
	}

	static int init() {
		for(int i = 0; i < 4*N; i++) tree[i] = 0;
		int rtn = 1;
		while(rtn < N) rtn *= 2;
		return rtn-1;
	}
	
	static void update(int node) {
		int now = node;
		while(now > 0) {
			tree[now] += 1;
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
