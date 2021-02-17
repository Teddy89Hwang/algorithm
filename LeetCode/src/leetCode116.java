import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 * LeetCode 116. Populating Next Right Pointers in Each Node
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 */

public class leetCode116 {

	// Definition for a Node.
	class Node {
	    public int val;
	    public Node left;
	    public Node right;
	    public Node next;

	    public Node() {}
	    
	    public Node(int _val) {
	        val = _val;
	    }

	    public Node(int _val, Node _left, Node _right, Node _next) {
	        val = _val;
	        left = _left;
	        right = _right;
	        next = _next;
	    }
	}
	
	class Solution {
	    public Node connect(Node root) {
	    	Node node = root;
	    	Node mark = new Node();
	        Node pre = mark;
	        while(node != null) {
	            if(node.left != null) {
	            	pre.next = node.left;
	                node.left.next = node.right;
	                pre = node.right;
	            }
	            if(node.next == null) {
	            	node = mark.next;
	            	mark.next = null;
	            	pre = mark;
	            } else {
	            	node = node.next;
	            }
	        }
	        return root;
	    }
	}
	 
	
	
	
	/* ==============================================================
	 *  for test 
 	 * ============================================================== */
	
	public static void main(String[] args) {
		int[] input = new int[] {1, 2, 3, 4, 5, 6, 7};
		Node root = makeTree(input);
		Solution sol = new leetCode116().new Solution();
		printSol(sol.connect(root));
	}
	
	private static void printSol(Node root) {
		Queue<Node> queue = new LinkedList<>();
		Node node;
		if(root != null) {
			if(root.left != null) queue.offer(root.left);
			System.out.print("[" + root.val);
		}
		while(!queue.isEmpty()) {
			node = queue.poll();
			if(node.left != null) queue.offer(node.left);
			while(node != null) {
				System.out.print("," + node.val);
				node = node.next;
			}
			System.out.print(",#");
		}
		System.out.println("]");
	}

	private static Node makeTree(int[] input) {
		leetCode116 clss = new leetCode116();
		int idx = 0;
		ArrayList<Node> list = new ArrayList<>();
		list.add(null);
		for(int i : input) {
			list.add(clss.new Node(i));
		}
		
		for(int i = 1; i < list.size() / 2; i++) {
			list.get(i).left = list.get(i * 2);
			list.get(i).right = list.get(i * 2 + 1);
		}
		
		return list.get(1);
	}
}
