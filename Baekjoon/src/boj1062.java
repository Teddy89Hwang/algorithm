import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 1062번 가르침
 * https://www.acmicpc.net/problem/1062
 */

public class boj1062 {

	static int N, K;
	static boolean[][] word;
	static boolean[] learn;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		word = new boolean[N][26];
		learn = new boolean[26];

		String input;
		for (int i = 0; i < N; i++) {
			input = br.readLine().trim();
			for (int j = 0; j < input.length(); j++) {
				word[i][input.charAt(j) - 'a'] = true;
			}
		}

		ans = 0;
		dfs(0, 0);
		
		bw.write("" + ans);
		bw.flush();
		bw.close();
		br.close();
	}

	private static void dfs(int idx, int count) {
		if (count == K) {
			ans = Math.max(ans, check());
			return;
		}

		for (int i = idx; i < 26; i++) {
			if (!learn[i]) {
				learn[i] = true;
				dfs(i, count + 1);
				learn[i] = false;
			}
		}
	}

	private static int check() {
		int ret = 0;

		boolean flag;
		for (int i = 0; i < N; i++) {
			flag = true;
			for (int j = 0; j < 26; j++) {
				if (word[i][j] && !learn[j]) {
					flag = false;
					break;
				}
			}
			if (flag) ret++;
		}
		
		return ret;
	}

}
