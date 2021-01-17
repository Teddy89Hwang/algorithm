import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 백준 10473번 인간 대포
 * https://www.acmicpc.net/problem/10473
 */

public class boj10473 {

	static class Position {
		double x;
		double y;

		public Position(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static class Vertex implements Comparable<Vertex> {
		int posit;
		double time;

		public Vertex(int posit, double time) {
			super();
			this.posit = posit;
			this.time = time;
		}

		@Override
		public int compareTo(Vertex o) {
			return time - o.time >= 0 ? 1 : -1;
		}

	}

	static int N;
	static Position[] pList = new Position[102];
	static Double[] time = new Double[102];
	static boolean isFirst = true;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		pList[0] = new Position(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
		time[0] = 0.;

		st = new StringTokenizer(br.readLine());
		pList[1] = new Position(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
		time[1] = Double.MAX_VALUE;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		for (int i = 2; i < N + 2; i++) {
			st = new StringTokenizer(br.readLine());
			pList[i] = new Position(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
			time[i] = Double.MAX_VALUE;
		}

		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(0, time[0]));

		Vertex ver;
		Position curr, next;
		double moveTime;
		while (!pq.isEmpty()) {
			ver = pq.poll();
			curr = pList[ver.posit];
			for (int i = 1; i <= N + 1; i++) {
				if (ver.posit == i) continue;
				next = pList[i];
				
				moveTime = getTime(getDistance(curr, next));
				
				if(time[i] > time[ver.posit] + moveTime) {
					time[i] = time[ver.posit] + moveTime;
					pq.add(new Vertex(i, time[i]));
				}
			}
			isFirst = false;
		}

        bw.write("" + time[1]);
		bw.flush();
		bw.close();
		br.close();
	}

	public static double getDistance(Position a, Position b) {
		double x = a.x - b.x;
		double y = a.y - b.y;

		return Math.sqrt(x * x + y * y);
	}

	public static double getTime(double dist) {
		double t1 = dist / 5.0;
		double t2 = isFirst? t1 : Math.abs(dist - 50) / 5.0 + 2;
		return Double.min(t1, t2);
	}

}