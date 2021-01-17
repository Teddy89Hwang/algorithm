import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * 백준 2517번 달리기
 * https://www.acmicpc.net/problem/2517
 */

public class boj2517 {

	static int N;
	static final int MAXN = 500000;
	static int[] tree = new int[MAXN * 4];
	static ArrayList<Player> list = new ArrayList<>();
	static int[] ANS = new int[MAXN+1];
	
	static class Player implements Comparable<Player> {
		int idx;
		int ability;
		public Player(int idx, int ability) {
			super();
			this.idx = idx;
			this.ability = ability;
		}
		@Override
		public int compareTo(Player o) {
			return o.ability - ability;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int idx = init();
		
		for(int i = 1; i <= N; i++) list.add(new Player(i, Integer.parseInt(br.readLine())));
		
		Collections.sort(list);

		for(Player p : list) {
			update(idx+p.idx);
			ANS[p.idx] = count(idx+1, idx+p.idx);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			sb.append(ANS[i]).append("\n");
		}
		
		bw.write(sb.toString());
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