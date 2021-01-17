import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 백준 7578번 공장
 * https://www.acmicpc.net/problem/7578
 */

public class boj7578 {

	static int N;
	static final int MAXN = 500000;
	static int[] tree = new int[MAXN * 4];
	static int[] machine = new int[1000001];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int idx = init();

		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) machine[Integer.parseInt(st.nextToken())] = i;
		
		st = new StringTokenizer(br.readLine());
		long ans = 0;
		for(int num, i = 0; i < N; i++) {
			num = Integer.parseInt(st.nextToken());
			ans += count(idx + machine[num] + 1, idx + N);
			update(idx + machine[num]);
		}
		
		bw.write("" + ans);
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int init() {
        Arrays.fill(machine, 0);
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
	
	static int count(int start, int end) {
		int cnt = 0;
		while(start <= end) {
			if(start % 2 == 1) cnt += tree[start];
			if(end % 2 == 0) cnt += tree[end];
			start = (start + 1) / 2;
			end = (end-1) / 2;
		}
		return cnt;
	}
}
