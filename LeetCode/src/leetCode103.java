import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * LeetCode 103. Binary Tree Zigzag Level Order Traversal
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 */

public class leetCode103 {

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
		zigzagLevelOrder(root);
	}
	
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    	List<List<Integer>> rtnList = new ArrayList<>();
    	List<Integer> tempList;
    	
    	Stack<TreeNode>[] stack = new Stack[2];
    	stack[0] = new Stack<>();
    	stack[1] = new Stack<>();
    	
    	stack[0].push(root);
    	int flag = 0;
    	TreeNode temp;
    	while(root != null && (stack[0].size() != 0 || stack[1].size() != 0)) {
    		tempList = new ArrayList<>();
    		while(!stack[flag].isEmpty()) {
    			temp = stack[flag].pop();
    			if(flag == 1) {
    				if(temp.right != null) stack[flag^1].push(temp.right);
    				if(temp.left != null) stack[flag^1].push(temp.left);
    				tempList.add(temp.val);
    			} else {
    				if(temp.left != null) stack[flag^1].push(temp.left);
    				if(temp.right != null) stack[flag^1].push(temp.right);
    				tempList.add(temp.val);
    			}
    		}
    		flag ^= 1;
    		rtnList.add(tempList);
    	}
    	return rtnList;
    }
    

}
