
/*
 * LeetCode 5. Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring/
 */

public class leetCode5 {

	public static void main(String[] args) {
		String s = "abbbacaa";
		System.out.println(longestPalindrome(s));
	}

	public static String longestPalindrome(String s) {
		String output = "";
		int len = s.length();
		int maxLen = 0;
		boolean[][] dp = new boolean[len][len];

		for (int i = 0; i < len; i++) {
			dp[i][i] = true;
			if(maxLen < 1) {
				maxLen = 1;
				output = s.substring(i, 1);
			}
		}

		for (int i = 0; i < len - 1; i++) {
			if (s.charAt(i) == s.charAt(i + 1)) {
				dp[i][i + 1] = true;
				if(maxLen < 2) {
					maxLen = 2;
					output = s.substring(i, i + 2);
				}
			}
		}
		
		for(int k = 3; k <= len; k++) {
			for(int j, i = 0; i <= len-k; i++) {
				j = i + k - 1;
				if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1]) {
					dp[i][j] = true;
					if(maxLen < k) {
						maxLen = k;
						output = s.substring(i, j + 1);
					}
				}
			}
		}

		return output;
	}
}
