import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 17131번 여우가 정보섬에 올라온 이유
 * https://www.acmicpc.net/problem/17131
 */

public class boj17131 {

	static int N;
	static final int MOD = 1000000007;
	static final int addX = 200000;
	static class Position implements Comparable<Position> {
		int x;
		int y;
		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Position o) {
			return o.y - y;
		}
	}
	static ArrayList<Position> stars = new ArrayList<>();
	static int[] tree = new int[addX * 2 * 4];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		for(int x, y, i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			stars.add(new Position(x, y));
		}
		Collections.sort(stars);
		int idx = init() + addX;
		
		Queue<Position> updateQueue = new LinkedList<>();
		Position before = null;
		long ans = 0;
		long left, right;
		Position temp;
		for(Position curr : stars) {
			if(before != null && before.y != curr.y) {
				while(!updateQueue.isEmpty()) {
					temp = updateQueue.poll();
					update(idx + temp.x);
				}
			}
			left = query(idx - addX, idx + curr.x - 1) % MOD;
			right  = query(idx + curr.x + 1, idx + addX) % MOD;
			ans = (ans + (left * right) % MOD) % MOD;
			updateQueue.offer(curr);
			before = curr;
		}
		
		bw.write("" + ans);
		bw.flush();
		bw.close();
		br.close();
	
	}

	private static void update(int curr) {
		while(curr > 0) {
			tree[curr] += 1;
			curr /= 2;
		}
	}

	private static long query(int start, int end) {
		long rtn = 0;
		while(start <= end) {
			if(start % 2 == 1) rtn += tree[start];
			if(end % 2 == 0) rtn += tree[end];
			start = (start + 1) / 2;
			end = (end - 1) / 2;
		}
		return rtn;
	}

	private static int init() {
		Arrays.fill(tree, 0);
		int rtn = 1;
		while(rtn < addX * 2) {
			rtn *= 2;
		}
		return rtn;
	}

}
