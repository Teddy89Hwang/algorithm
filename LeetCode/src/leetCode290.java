import java.util.HashMap;

/*
 * LeetCode 290. Word Pattern
 * https://leetcode.com/problems/word-pattern/
 */

public class leetCode290 {

	public static void main(String[] args) {
		System.out.println(wordPattern("abba", "dog dog dog dog"));
	}

    public static boolean wordPattern(String pattern, String s) {
    	HashMap<String, Character> map = new HashMap<>();
    	String[] word = new String[26];

    	String[] words = s.split(" ");
    	
    	char p;
    	if (pattern.length() != words.length) return false;
    	for(int i = 0; i < pattern.length(); i++) {
    		p = pattern.charAt(i);
    		if(word[p-'a'] == null) {
    			if(map.containsKey(words[i])) return false;
    			word[p-'a'] = words[i];
    			map.put(words[i], p);
    		} else {
    			if(!word[p-'a'].equals(words[i])) return false;
    		}
    	}
    	return true;
    }
}
