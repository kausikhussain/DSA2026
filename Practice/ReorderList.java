package Practice;

public class ReorderList {

    // Definition for singly-linked list.
    public static class ListNode {
        public int val;
        public ListNode next;
        public ListNode() {}
        public ListNode(int val) { this.val = val; }
        public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * LeetCode 143: Reorder List
     * 
     * You are given the head of a singly linked-list:
     * L0 -> L1 -> ... -> Ln-1 -> Ln
     * 
     * Reorder the list to be on the following form:
     * L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 -> ...
     * 
     * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        // Step 1: Find the middle of the linked list using slow and fast pointers
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Split the list and reverse the second half
        ListNode secondHalfHead = slow.next;
        slow.next = null; // Disconnect the two halves

        ListNode prev = null;
        ListNode curr = secondHalfHead;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        // prev is now the head of the reversed second half

        // Step 3: Interleave / merge the two halves alternately
        ListNode first = head;
        ListNode second = prev;

        while (second != null) {
            ListNode firstNext = first.next;
            ListNode secondNext = second.next;

            first.next = second;
            second.next = firstNext;

            first = firstNext;
            second = secondNext;
        }
    }

    // Helper method to create a linked list from array
    public static ListNode buildList(int[] values) {
        if (values == null || values.length == 0) return null;
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
        ReorderList solution = new ReorderList();

        // Test 1: [1, 2, 3, 4] -> [1, 4, 2, 3]
        ListNode list1 = buildList(new int[]{1, 2, 3, 4});
        solution.reorderList(list1);
        System.out.println("Test 1: " + printList(list1));
        // Expected: 1 -> 4 -> 2 -> 3

        // Test 2: [1, 2, 3, 4, 5] -> [1, 5, 2, 4, 3]
        ListNode list2 = buildList(new int[]{1, 2, 3, 4, 5});
        solution.reorderList(list2);
        System.out.println("Test 2: " + printList(list2));
        // Expected: 1 -> 5 -> 2 -> 4 -> 3
    }
}
