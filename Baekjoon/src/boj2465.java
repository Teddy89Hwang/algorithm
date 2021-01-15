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
 * 백준 2465번 줄 세우기
 * https://www.acmicpc.net/problem/2465
 */


public class boj2465 {

	static int N, idx;
	static ArrayList<Integer> list = new ArrayList<>();
	static int[] tree = new int[100000 * 4];
	static int[] S = new int[100000];
	static int[] ans = new int[100000];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine().trim());

		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(br.readLine().trim()));
		}

		Collections.sort(list);

		idx = init();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			update(idx + i, 1);
			S[i] = Integer.parseInt(st.nextToken());
		}

		for (int seq, i = N - 1; i >= 0; i--) {
			seq = query(S[i] + 1);
			ans[i] = list.get(seq);
			update(idx + seq, -1);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(ans[i]).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static int query(int val) {
		int node = 1;
		while (node < idx) {
			if (tree[node * 2] >= val)
				node *= 2;
			else {
				val -= tree[node * 2];
				node = node * 2 + 1;
			}
		}
		return node - idx;
	}

	private static void update(int node, int val) {
		while (node > 0) {
			tree[node] += val;
			node /= 2;
		}
	}

	private static int init() {
		Arrays.fill(tree, 0);
		int rtn = 1;
		while (rtn < N)
			rtn *= 2;
		return rtn;
	}
}
