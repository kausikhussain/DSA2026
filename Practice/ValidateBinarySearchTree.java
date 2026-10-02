package Practice;

public class ValidateBinarySearchTree {

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
     * LeetCode 98: Validate Binary Search Tree
     * 
     * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
     */
    public boolean isValidBST(TreeNode root) {
        // Use Long.MIN_VALUE and Long.MAX_VALUE to safely handle edge cases 
        // where tree node values equal Integer.MIN_VALUE or Integer.MAX_VALUE.
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long minBound, long maxBound) {
        if (node == null) {
            return true;
        }

        // Each node value must be strictly within (minBound, maxBound)
        if (node.val <= minBound || node.val >= maxBound) {
            return false;
        }

        // Recursively validate left and right subtrees with updated bounds
        return validate(node.left, minBound, node.val) 
            && validate(node.right, node.val, maxBound);
    }

    public static void main(String[] args) {
        ValidateBinarySearchTree solution = new ValidateBinarySearchTree();

        // Test 1: [2, 1, 3] -> Valid BST
        //     2
        //    / \
        //   1   3
        TreeNode root1 = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        System.out.println("Test 1 [2, 1, 3]: " + solution.isValidBST(root1)); 
        // Expected: true

        // Test 2: [5, 1, 4, null, null, 3, 6] -> Invalid BST (3 is in right subtree of 5 but 3 < 5)
        //     5
        //    / \
        //   1   4
        //      / \
        //     3   6
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4, new TreeNode(3), new TreeNode(6));
        System.out.println("Test 2 [5, 1, 4, null, null, 3, 6]: " + solution.isValidBST(root2)); 
        // Expected: false
    }
}
