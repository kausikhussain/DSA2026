package Practice;

public class SearchBST {
    
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
     * LeetCode 700: Search in a Binary Search Tree
     * 
     * You are given the root of a binary search tree (BST) and an integer val.
     * Find the node in the BST that the node's value equals val and return the subtree rooted with that node. 
     * If such a node does not exist, return null.
     */
    public TreeNode searchBST(TreeNode root, int val) {
        // Iterative Approach: Extremely space-efficient (O(1) auxiliary space)
        TreeNode curr = root;
        while (curr != null) {
            if (curr.val == val) {
                return curr; // Found the target node
            } else if (val < curr.val) {
                curr = curr.left; // Go left since target value is smaller
            } else {
                curr = curr.right; // Go right since target value is larger
            }
        }
        return null; // Target value not found in BST
    }

    public static void main(String[] args) {
        SearchBST solution = new SearchBST();

        // Constructing BST:
        //      4
        //     / \
        //    2   7
        //   / \
        //  1   3
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        root.right = new TreeNode(7);

        TreeNode result = solution.searchBST(root, 2);
        if (result != null) {
            System.out.println("Node found: Root of returned subtree is " + result.val); // Expected: 2
            System.out.println("Left child: " + (result.left != null ? result.left.val : "null")); // Expected: 1
            System.out.println("Right child: " + (result.right != null ? result.right.val : "null")); // Expected: 3
        } else {
            System.out.println("Node not found.");
        }
    }
}
