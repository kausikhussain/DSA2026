package Practice;

public class LowestCommonAncestorBST {

    // Definition for a binary tree node.
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int val) { this.val = val; }
    }

    /**
     * LeetCode 235: Lowest Common Ancestor of a Binary Search Tree
     * 
     * Given a binary search tree (BST), find the lowest common ancestor (LCA) node 
     * of two given nodes in the BST.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;

        while (curr != null) {
            // If both p and q are smaller than current node, LCA must be in the left subtree
            if (p.val < curr.val && q.val < curr.val) {
                curr = curr.left;
            }
            // If both p and q are greater than current node, LCA must be in the right subtree
            else if (p.val > curr.val && q.val > curr.val) {
                curr = curr.right;
            }
            // Split point found: one is on the left, one is on the right,
            // or current node is equal to either p or q. Hence, curr is the LCA.
            else {
                return curr;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        LowestCommonAncestorBST solution = new LowestCommonAncestorBST();

        // Construct BST:
        //        6
        //      /   \
        //     2     8
        //    / \   / \
        //   0   4 7   9
        //      / \
        //     3   5
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        TreeNode p1 = root.left;       // Node 2
        TreeNode q1 = root.right;      // Node 8
        TreeNode lca1 = solution.lowestCommonAncestor(root, p1, q1);
        System.out.println("Test 1 (LCA of 2 and 8): " + (lca1 != null ? lca1.val : "null")); // Expected: 6

        TreeNode p2 = root.left;             // Node 2
        TreeNode q2 = root.left.right;       // Node 4
        TreeNode lca2 = solution.lowestCommonAncestor(root, p2, q2);
        System.out.println("Test 2 (LCA of 2 and 4): " + (lca2 != null ? lca2.val : "null")); // Expected: 2
    }
}
