import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 백준 1572번 중앙값
 * https://www.acmicpc.net/problem/1572
 */

public class boj1572 {

	static int N, K, idx;
	static int MAXV = 65537;
	static int[] input = new int[250001];
	static int[] tree = new int[MAXV * 4];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		idx = init();
		
		long ans = 0;
		for(int i = 1; i <= N; i++) {
			input[i] = Integer.parseInt(br.readLine().trim());
			update(idx+input[i], 1);
			if(i < K) continue;
			ans += query((K+1)/2);
			update(idx+input[i-K+1], -1);
		}
		
		
		bw.write("" + ans);
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static int query(int k) {
		int curr = 1;
	    while(curr < idx){
	        if(k <= tree[2*curr]) curr*=2;
	        else {
	        	k -= tree[2*curr];
	        	curr = 2*curr + 1;
	        }
	    }
	    return curr-idx;
	}

	private static void update(int node, int val) {
		while(node > 0) {
			tree[node] += val;
			node /= 2;
		}
	}

	static int init() {
		Arrays.fill(tree, 0);
		int rtn = 1;
		while(rtn < MAXV) rtn *= 2;
		return rtn;
	}

}
