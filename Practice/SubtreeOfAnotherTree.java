package Practice;

public class SubtreeOfAnotherTree {

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
     * LeetCode 572: Subtree of Another Tree
     * 
     * Given the roots of two binary trees root and subRoot, return true if there is a subtree 
     * of root with the same structure and node values of subRoot and false otherwise.
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) return true; // Empty tree is always a subtree
        if (root == null) return false;   // Non-empty subRoot cannot be a subtree of null

        // Check if trees rooted at current nodes are identical
        if (isSameTree(root, subRoot)) {
            return true;
        }

        // Recursively check left and right subtrees
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        SubtreeOfAnotherTree solution = new SubtreeOfAnotherTree();

        // Construct root: [3, 4, 5, 1, 2]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4, new TreeNode(1), new TreeNode(2));
        root.right = new TreeNode(5);

        // Construct subRoot: [4, 1, 2]
        TreeNode subRoot = new TreeNode(4, new TreeNode(1), new TreeNode(2));

        System.out.println("Test 1: " + solution.isSubtree(root, subRoot)); 
        // Expected: true

        // Construct root with extra child: [3, 4, 5, 1, 2, null, null, null, null, 0]
        root.left.right.left = new TreeNode(0);
        System.out.println("Test 2: " + solution.isSubtree(root, subRoot)); 
        // Expected: false
    }
}
