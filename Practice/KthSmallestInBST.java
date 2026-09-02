package Practice;

import java.util.ArrayDeque;
import java.util.Deque;

public class KthSmallestInBST {

    // Definition for a binary tree node.
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode() {}
        public TreeNode(int val) { this.val = val; }
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * LeetCode 230: Kth Smallest Element in a BST
     * 
     * Given the root of a binary search tree, and an integer k, 
     * return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
     */
    public int kthSmallest(TreeNode root, int k) {
        // In-order traversal of a BST yields elements in strictly ascending order.
        // We use an iterative in-order traversal using a stack to stop early at the k-th node.
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            // Traverse down to the leftmost node
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // Pop the top node (smallest remaining element)
            curr = stack.pop();
            k--;

            // If k reaches 0, we found the kth smallest element
            if (k == 0) {
                return curr.val;
            }

            // Visit right subtree
            curr = curr.right;
        }

        return -1; // Should not be reached based on problem constraints
    }

    public static void main(String[] args) {
        KthSmallestInBST solution = new KthSmallestInBST();

        // Construct BST:
        //       3
        //      / \
        //     1   4
        //      \
        //       2
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1, null, new TreeNode(2));
        root1.right = new TreeNode(4);

        System.out.println("Test 1 (k=1): " + solution.kthSmallest(root1, 1)); // Expected: 1
        System.out.println("Test 1 (k=2): " + solution.kthSmallest(root1, 2)); // Expected: 2
        System.out.println("Test 1 (k=3): " + solution.kthSmallest(root1, 3)); // Expected: 3
    }
}
