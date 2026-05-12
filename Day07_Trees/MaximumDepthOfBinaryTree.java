package Day07_Trees;

public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        // Recursively find the depth of the left and right subtrees
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        
        // The max depth is 1 (for the root) plus the maximum of the subtree depths
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
