import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * LeetCode 15. 3Sum
 * https://leetcode.com/problems/3sum/
 */

public class leetCode15 {

	public static void main(String[] args) {
		int[] input = new int[] {-1,0,1,2,-1,-4};
		System.out.println(threeSum(input).toString());
	}
	
    public static List<List<Integer>> threeSum(int[] nums) {
    	List<List<Integer>> returnList = new ArrayList<List<Integer>>();
    	
    	Arrays.sort(nums);
    	
    	int n = nums.length;
    	
    	int x, y, z, yIdx, zIdx;
    	List<Integer> temp;
    	
    	for(int i = 0; i < n; i++) {
    		x = nums[i];
    		if(i > 0 && x == nums[i-1]) continue;
    		yIdx = i + 1;
    		zIdx = n - 1;
    		while(yIdx < zIdx) {
    			y = nums[yIdx];
    			z = nums[zIdx];
    			if(x + y + z > 0) {
    				while(--zIdx >= 0 && z == nums[zIdx]);
    			} else if(x + y + z < 0) {
    				while(++yIdx < n && y == nums[yIdx]);
    			} else {
    				temp = new ArrayList<>();
    				temp.add(x);
    				temp.add(y);
    				temp.add(z);
    				returnList.add(temp);
    				while(--zIdx >= 0 && z == nums[zIdx]);
    				while(++yIdx < n && y == nums[yIdx]);
    			}
    		}
    	}
    	return returnList;
    }

}
