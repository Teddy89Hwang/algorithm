import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 백준 1321번 군인
 * https://www.acmicpc.net/problem/1321
 */

public class boj1321 {

	static int N, M, idx;
	static int[] tree = new int[500000 * 4];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine().trim());

		idx = init();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			tree[idx + i] = Integer.parseInt(st.nextToken());
		}
		for (int i = idx; i > 0; i--)
			tree[i] = tree[i * 2] + tree[i * 2 + 1];

		M = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		for (int o, i, a, j = 0; j < M; j++) {
			st = new StringTokenizer(br.readLine());
			o = Integer.parseInt(st.nextToken());

			if (o == 1) {
				i = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				update(idx + i, a);
			} else {
				i = Integer.parseInt(st.nextToken());
				sb.append(query(i)).append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static int query(int val) {
		int node = 1;
		while (node < idx + 1) {
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
		return rtn - 1;
	}

}
