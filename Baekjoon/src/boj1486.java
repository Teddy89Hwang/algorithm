import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 백준 1486번 등산
 * https://www.acmicpc.net/problem/1486
 */


public class boj1486 {

	static int N, M, T, D;
	static int MAX_HIGH = Integer.MIN_VALUE;
	static int[][] map = new int[25][25];
	static int[][] dir = new int[][] {{1, 0},{0, 1},{-1, 0},{0, -1}};

	static class Node implements Comparable<Node> {
		int i;
		int j;
		int time;

		public Node(int i, int j, int time) {
			super();
			this.i = i;
			this.j = j;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			return time - o.time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		String row;
		char temp;
		for (int i = 0; i < N; i++) {
			row = br.readLine().trim();
			for (int j = 0; j < M; j++) {
				temp = row.charAt(j);
				map[i][j] = temp >= 'a' ? temp + 26 - 'a' : temp - 'A';
			}
		}
		
		int ANS = map[0][0];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] <= ANS) continue;
				if(ableRound(i, j)) ANS = map[i][j];
			}
		}

		bw.write("" + ANS);
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static boolean ableRound(int i, int j) {
		return dijkstra(0, 0, i, j) + dijkstra(i, j, 0, 0) <= D;
	}

	public static int dijkstra(int fromI, int fromJ, int toI, int toJ) {
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
	
		int[][] minTime = new int[N][M];
		for(int i = 0;  i < N; i++) Arrays.fill(minTime[i], D);
		minTime[fromI][fromJ] = 0;
		pq.offer(new Node(fromI, fromJ, 0));

		Node curr;
		int nextI, nextJ, nextTime;
		while(!pq.isEmpty()) {
			curr = pq.poll();
			if(curr.i == toI && curr.j == toJ) break;
			
			for(int i = 0; i < 4; i++) {
				nextI = curr.i + dir[i][0];
				nextJ = curr.j + dir[i][1];
				if(isAble(curr.i, curr.j, nextI, nextJ)) {
					nextTime = curr.time + getTime(map[curr.i][curr.j], map[nextI][nextJ]);
					if(minTime[nextI][nextJ] > nextTime) {
						minTime[nextI][nextJ] = nextTime;
						pq.offer(new Node(nextI, nextJ, minTime[nextI][nextJ]));
					}
				}
			}
		}
		return minTime[toI][toJ];
	}

	public static boolean isAble(int i, int j, int nextI, int nextJ) {
		if(!isInbound(nextI, nextJ)) return false;
		if(Math.abs(map[i][j] - map[nextI][nextJ]) > T) return false;
		return true;
	}
	
	public static boolean isInbound(int i, int j) {
		if (i < 0 || i >= N) return false;
		if (j < 0 || j >= M) return false;
		return true;
	}

	public static int getTime(int from, int to) {
		if (from >= to)
			return 1;
		else
			return (to - from) * (to - from);
	}
}
