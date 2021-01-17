import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 백준 2096번 내려가기
 * https://www.acmicpc.net/problem/2096
 */

public class boj2096 {
	
	static int N;
	static int[][] dpMax = new int[100000][3];
	static int[][] dpMin = new int[100000][3];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine().trim());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int a, i = 0; i < 3; i++) {
			a = Integer.parseInt(st.nextToken());
			dpMax[0][i] = a;
			dpMin[0][i] = a;
		}
		
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int v, j = 0; j < 3; j++) {
				v = Integer.parseInt(st.nextToken());
				if(j == 0) {
					dpMin[i][0] = Math.min(dpMin[i-1][0], dpMin[i-1][1]) + v;
					dpMax[i][0] = Math.max(dpMax[i-1][0], dpMax[i-1][1]) + v;
				} else if(j == 2) {
					dpMin[i][2] = Math.min(dpMin[i-1][2], dpMin[i-1][1]) + v;
					dpMax[i][2] = Math.max(dpMax[i-1][2], dpMax[i-1][1]) + v;
				} else {
					dpMin[i][1] = Math.min(dpMin[i-1][2], Math.min(dpMin[i-1][1], dpMin[i-1][0])) + v;
					dpMax[i][1] = Math.max(dpMax[i-1][2], Math.max(dpMax[i-1][1], dpMax[i-1][0])) + v;
				}
			}
		}
		
		int ansMin = Math.min(dpMin[N-1][2], Math.min(dpMin[N-1][1], dpMin[N-1][0]));
		int ansMax = Math.max(dpMax[N-1][2], Math.max(dpMax[N-1][1], dpMax[N-1][0]));
		
		bw.write(ansMax + " " + ansMin);
		bw.flush();
		bw.close();
		br.close();
	}
}
