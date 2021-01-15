import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * 백준 4195번 친구 네트워크
 * https://www.acmicpc.net/problem/4195
 */

public class boj4195 {
	static int T, N;
	static Map<String, Integer> friend = new HashMap<>();
	static final int MAX = 200000;
	static int[] root = new int[MAX];
	static int[] count = new int[MAX];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for(int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			for(int i = 0; i < MAX; i++) {
				root[i] = i;
				count[i] = 1;
			}
			friend.clear();
			String name1, name2;
			int cnt = -1;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				name1 = st.nextToken();
				name2 = st.nextToken();
				if(!friend.containsKey(name1)) {
					friend.put(name1, ++cnt);
				}
				if(!friend.containsKey(name2)) {
					friend.put(name2, ++cnt);
				}
				int ans = join(friend.get(name1), friend.get(name2));
				bw.write(""+ans+"\n");
				
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	static int find(int node) {
		if(root[node] == node) return node;
		else return root[node] = find(root[node]);
	}
	
	static int join(int a, int b){
		   a = find(a);
		   b = find(b);

		   if(a != b) {
		       root[b] = a; 
		       count[a] += count[b]; 
		       count[b] = 1; 
		   }
		   return count[a]; 
		}

}
