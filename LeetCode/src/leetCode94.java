import java.util.ArrayList;
import java.util.List;

/*
 * LeetCode 94. Binary Tree Inorder Traversal
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 */

public class leetCode94 {

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
	public static void main(String[] args) {
		TreeNode root = new TreeNode();
		inorderTraversal(root);
	}
	
    static List<Integer> rtnList = new ArrayList<>();
    public static List<Integer> inorderTraversal(TreeNode root) {
        dfs(root);
        return rtnList;
    }
    
    public static void dfs(TreeNode node) {
        if(node == null) return;
        dfs(node.left);
        rtnList.add(node.val);
        dfs(node.right);
    }

}
