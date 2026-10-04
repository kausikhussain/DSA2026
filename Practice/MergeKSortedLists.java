package Practice;

import java.util.PriorityQueue;

public class MergeKSortedLists {

    // Definition for singly-linked list.
    public static class ListNode {
        public int val;
        public ListNode next;
        public ListNode() {}
        public ListNode(int val) { this.val = val; }
        public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * LeetCode 23: Merge k Sorted Lists
     * 
     * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
     * Merge all the linked-lists into one sorted linked-list and return it.
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Min-Heap ordered by node value
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

        // Step 1: Add head of each non-empty list into the heap
        for (ListNode listHead : lists) {
            if (listHead != null) {
                minHeap.offer(listHead);
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        // Step 2: Pop smallest element, advance its list pointer, and push next node into heap
        while (!minHeap.isEmpty()) {
            ListNode smallest = minHeap.poll();
            tail.next = smallest;
            tail = tail.next;

            if (smallest.next != null) {
                minHeap.offer(smallest.next);
            }
        }

        return dummy.next;
    }

    // Helper method to create linked list from array
    public static ListNode buildList(int[] values) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int v : values) {
            curr.next = new ListNode(v);
            curr = curr.next;
        }
        return dummy.next;
    }

    // Helper method to print linked list
    public static String printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode curr = head;
        while (curr != null) {
            sb.append(curr.val);
            if (curr.next != null) sb.append(" -> ");
            curr = curr.next;
        }
        return sb.length() == 0 ? "[]" : sb.toString();
    }

    public static void main(String[] args) {
        MergeKSortedLists solution = new MergeKSortedLists();

        // Test 1: [[1, 4, 5], [1, 3, 4], [2, 6]]
        ListNode[] lists1 = new ListNode[] {
            buildList(new int[]{1, 4, 5}),
            buildList(new int[]{1, 3, 4}),
            buildList(new int[]{2, 6})
        };
        ListNode merged1 = solution.mergeKLists(lists1);
        System.out.println("Test 1: " + printList(merged1));
        // Expected: 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6

        // Test 2: []
        System.out.println("Test 2: " + printList(solution.mergeKLists(new ListNode[]{})));
        // Expected: []
    }
}
