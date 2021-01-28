/*
 * LeetCode 334. Increasing Triplet Subsequence
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 */

public class leetCode334 {

	public static void main(String[] args) {
		int[] input = new int[] {1, 2, 3};
		System.out.println(increasingTriplet(input));
	}

    public static boolean increasingTriplet(int[] nums) {
    	if(nums == null || nums.length < 3) return false;
    	
    	int first = nums[0];
    	int second = Integer.MAX_VALUE;
    	
    	for(int num : nums) {
    		if(num < first) {
    			first = num;
    		} else if(num > first) {
    			second = Math.min(second, num);
    			if(num > second) return true;
    		}
    	}
        return false;
    }
}
