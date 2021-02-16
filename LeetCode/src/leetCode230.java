/*
 * LeetCode 230. Kth Smallest Element in a BST
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 */

public class leetCode230 {

	/**
	 * Definition for a binary tree node.
	 */
	public static class TreeNode {
		     int val;
		     TreeNode left;
		     TreeNode right;
		     TreeNode() {}
		     TreeNode(int val) { this.val = val; }
		     TreeNode(int val, TreeNode left, TreeNode right) {
		         this.val = val;
		         this.left = left;
		         this.right = right;
		     }
		 }
	class Solution {
		int count = 0;
	    int ans;
	    public int kthSmallest(TreeNode root, int k) {
	        count = k;
	        findkth(root);
	        return ans;
	    }

	    public void findkth(TreeNode root) {
	        if(root == null || count == 0) return;
	        findkth(root.left);
	        if(--count == 0) ans = root.val;
	        findkth(root.right);
	    }
	}
	 
	public static void main(String[] args) {
		TreeNode root = new TreeNode();
		int k = 1;
		Solution sol = new leetCode230().new Solution();
		sol.kthSmallest(root, k);
	}
}
