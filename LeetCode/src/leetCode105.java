import java.util.HashMap;

/*
 * LeetCode 105. Construct Binary Tree from Preorder and Inorder Traversal
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */

public class leetCode105 {

	/**
	 * Definition for a binary tree node.
	 */
	public class TreeNode {
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
		int preIdx;
		HashMap<Integer, Integer> map;
	    public TreeNode buildTree(int[] preorder, int[] inorder) {
	    	preIdx = 0;
	    	map = new HashMap<>();
	    	
	    	for(int i = 0; i < inorder.length; i++) map.put(inorder[i], i);
	    	
	    	return build(0, inorder.length - 1, preorder, inorder);
	    }

	    public TreeNode build(int start, int end, int[] preOrder, int[] inOrder) {
			if(start > end) return null;
			TreeNode node = new TreeNode(preOrder[preIdx++]);
			int idx = map.get(node.val);
			node.left = build(start, idx - 1, preOrder, inOrder);
			node.right = build(idx + 1, end, preOrder, inOrder);
			return node;
		}
	}
	 
	public static void main(String[] args) {
		int[] preOrder = new int[] {3,9,20,15,7};
		int[] inOrder = new int[] {9,3,15,20,7};
		Solution sol = new leetCode105().new Solution();
		sol.buildTree(preOrder, inOrder);
	}
}
