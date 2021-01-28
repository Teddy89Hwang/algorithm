/*
 * LeetCode 2. Add Two Numbers
 * https://leetcode.com/problems/add-two-numbers/
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

public class leetCode2 {

	public static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int l1Next, l2Next, up = 0;
        ListNode head = new ListNode();
        ListNode curr = head;
        
        while(l1 != null || l2 != null) {
            l1Next = l1 == null? 0 : l1.val;
            l2Next = l2 == null? 0 : l2.val;
            curr.next = new ListNode((l1Next + l2Next + up) % 10);
            up = (l1Next + l2Next + up) / 10;
            l1 = l1 == null? l1 : l1.next;
            l2 = l2 == null? l2 : l2.next;
            curr = curr.next;
        }
        if(up != 0) curr.next = new ListNode(up);
        
        return head.next;
    }
	
	
	
	public static void main(String[] args) {
		int[] input1 = new int[] {9,9,9,9,9,9,9};
		int[] input2 = new int[] {9,9,9,9};
		System.out.println(print(addTwoNumbers(transListNode(input1), transListNode(input2))));
	}
	
	private static String print(ListNode node) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		while(node != null) {
			sb.append(node.val);
			if(node.next == null) sb.append("]");
			else sb.append(",");
			node = node.next;
		}
		return sb.toString();
	}

	private static ListNode transListNode(int[] intArr) {
		ListNode temp = null;
		for(int i = intArr.length - 1; i >= 0; i--) {
			temp = new ListNode(intArr[i], temp);
		}
		return temp;
	}
}
