/*
 * LeetCode 328. Odd Even Linked List
 * https://leetcode.com/problems/odd-even-linked-list/
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

public class leetCode328 {

	public static class ListNode {
		int val;
		ListNode next;
		ListNode() {}
		ListNode(int val) { this.val = val; }
		ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
	
    public static ListNode oddEvenList(ListNode head) {
    	int count = 1;
    	if(head == null) return head;
    	ListNode odd = new ListNode();
    	ListNode even = new ListNode();
    	ListNode evenFirst = head.next;
    	ListNode curr = head;
    	while(curr != null) {
    		if(count++ % 2 == 0) {
    			even.next = curr;
    			even = curr;
    		} else {
    			odd.next = curr;
    			odd = curr;
    		}
    		curr = curr.next;
    	}
    	odd.next = evenFirst;
    	even.next = null;
		return head;
    }
	
	public static void main(String[] args) {
		int[] input1 = new int[] {};
		System.out.println(print(oddEvenList(transListNode(input1))));
	}

	private static String print(ListNode node) {
		StringBuilder sb = new StringBuilder();
		while(node != null) {
			sb.append(node.val);
			if(node.next != null) sb.append("->");
			else sb.append("->NULL");
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
