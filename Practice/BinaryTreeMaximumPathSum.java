package Practice;

/**
 * LeetCode 124: Binary Tree Maximum Path Sum
 * Difficulty: Hard
 * 
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes 
 * in the sequence has an edge connecting them. A node can only appear in the sequence 
 * at most once. Note that the path does not need to pass through the root.
 * 
 * The path sum is the sum of the node's values in the path.
 * 
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 * 
 * Approach:
 * - Post-Order DFS with Bottom-Up Gain Computation.
 * - At each node:
 *   1. Recursively compute the maximum gain obtainable from its left and right subtrees.
 *   2. If a subtree gain is negative, ignore it by clamping to 0 (Math.max(0, gain)), 
 *      as negative branches would only decrease our total path sum.
 *   3. The maximum path sum that peaks at the current node is:
 *      node.val + leftGain + rightGain.
 *   4. Update the global maximum path sum with this value.
 *   5. Return (node.val + Math.max(leftGain, rightGain)) to the parent node, 
 *      representing the maximum path sum starting at current node and extending downwards 
 *      into one branch.
 * 
 * Complexity:
 * - Time: O(n), where n is the number of nodes in the binary tree. Each node is visited once.
 * - Space: O(h), where h is the tree height, representing recursive call stack space 
 *   (O(log n) for balanced tree, O(n) worst-case for skewed tree).
 */
public class BinaryTreeMaximumPathSum {

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

    private int maxSum;

    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxGain(root);
        return maxSum;
    }

    private int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Clamp negative gains to 0 to effectively discard undesirable subpaths
        int leftGain = Math.max(0, maxGain(node.left));
        int rightGain = Math.max(0, maxGain(node.right));

        // Path sum with current node as the apex/peak
        int currentApexPathSum = node.val + leftGain + rightGain;

        // Update the running global maximum path sum
        maxSum = Math.max(maxSum, currentApexPathSum);

        // Return maximum path extending down one branch to parent
        return node.val + Math.max(leftGain, rightGain);
    }

    public static void main(String[] args) {
        BinaryTreeMaximumPathSum solution = new BinaryTreeMaximumPathSum();

        // Test 1: [1, 2, 3]
        //     1
        //    / \
        //   2   3
        // Optimal path: 2 -> 1 -> 3, sum = 6
        TreeNode root1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println("Test 1: " + solution.maxPathSum(root1)); // Expected: 6

        // Test 2: [-10, 9, 20, null, null, 15, 7]
        //       -10
        //       /  \
        //      9   20
        //         /  \
        //        15   7
        // Optimal path: 15 -> 20 -> 7, sum = 42
        TreeNode root2 = new TreeNode(-10,
            new TreeNode(9),
            new TreeNode(20, new TreeNode(15), new TreeNode(7))
        );
        System.out.println("Test 2: " + solution.maxPathSum(root2)); // Expected: 42

        // Test 3: All negative values [-3]
        TreeNode root3 = new TreeNode(-3);
        System.out.println("Test 3: " + solution.maxPathSum(root3)); // Expected: -3

        // Test 4: Mixed values [2, -1]
        TreeNode root4 = new TreeNode(2, new TreeNode(-1), null);
        System.out.println("Test 4: " + solution.maxPathSum(root4)); // Expected: 2
    }
}
