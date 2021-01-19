import java.util.HashSet;

/*
 * LeetCode 3. Longest Substring Without Repeating Characters
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */

public class leetCode3 {

	public static void main(String[] args) {
		String s = " ";
		System.out.println(lengthOfLongestSubstring(s));
	}
	
	public static int lengthOfLongestSubstring(String s) {
		int sIdx = 0;
		int eIdx = 0;
		int count = 0;
		HashSet<Character> hashSet = new HashSet<Character>();
		
		char addChar, removeChar;
		while(sIdx < s.length() && eIdx < s.length()) {
			addChar = s.charAt(eIdx++);
			while(hashSet.contains(addChar)) {
				removeChar = s.charAt(sIdx++);
				hashSet.remove(removeChar);
			}
			hashSet.add(addChar);
			count = Math.max(count, hashSet.size());
		}
        return count;
	}
}
