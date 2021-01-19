import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

/*
 * 백준 1874번 스택 수열
 * https://www.acmicpc.net/problem/1874
 */

public class boj1874 {

	static int N;
	static Stack<Integer> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine().trim());
		
		int input, cursor = 0;
		while(N-- > 0) {
			input = Integer.parseInt(br.readLine().trim());
			
			if(input > cursor) {
				for(int i = cursor + 1; i <= input; i++) {
					stack.push(i);
					sb.append("+\n");
				}
                cursor = input;
			} else if(stack.peek() != input) {
				sb = new StringBuilder();
				sb.append("NO");
				break;
			}
			stack.pop();
			sb.append("-\n");
		}
		
		

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}