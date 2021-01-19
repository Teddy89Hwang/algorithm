import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 * LeetCode 49. Group Anagrams
 * https://leetcode.com/problems/group-anagrams/
 */

public class leetCode49 {

	public static void main(String[] args) {
		String[] strs = {"eat","tea","tan","ate","nat","bat"};
		System.out.println(groupAnagrams(strs).toString());
	}
	
	public static List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> returnList = new ArrayList<>();
		
		HashMap<String, Integer> map = new HashMap<>();
		char[] charSet;
		String key;
		for(String str : strs) {
			charSet = str.toCharArray();
			Arrays.sort(charSet);
			key = String.valueOf(charSet);
			if(!map.containsKey(key)) {
				returnList.add(new ArrayList<>());
				map.put(key, map.size());
			}
			returnList.get(map.get(key)).add(str);
		}
		
		return returnList;
    }

}
