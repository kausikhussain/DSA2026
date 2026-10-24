package Practice;

/**
 * LeetCode 543: Diameter of Binary Tree
 * Difficulty: Easy / Medium
 * 
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * 
 * The diameter of a binary tree is the length of the longest path between any 
 * two nodes in a tree. This path may or may not pass through the root.
 * 
 * The length of a path between two nodes is represented by the number of edges between them.
 * 
 * Approach:
 * - Bottom-up Post-Order DFS.
 * - At every node, recursively determine the height of its left and right subtrees.
 * - The longest path that uses the current node as its peak is (leftHeight + rightHeight).
 * - Update the running maximum diameter with this value.
 * - Return (1 + max(leftHeight, rightHeight)) to allow parent nodes to compute their paths.
 * 
 * Complexity:
 * - Time: O(n), where n is the number of nodes (each node is visited once).
 * - Space: O(h), where h is tree height, representing call stack frames (O(log n) balanced, O(n) worst-case).
 */
public class DiameterOfBinaryTree {

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

    private int maxDiameter;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;
        calculateHeight(root);
        return maxDiameter;
    }

    private int calculateHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);

        // Path length passing through this node (in edges) is leftHeight + rightHeight
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);

        // Return height of current node to its parent
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {
        DiameterOfBinaryTree solution = new DiameterOfBinaryTree();

        // Test 1: [1, 2, 3, 4, 5]
        //         1
        //        / \
        //       2   3
        //      / \
        //     4   5
        // Longest path: [4, 2, 1, 3] or [5, 2, 1, 3] -> 3 edges
        TreeNode root1 = new TreeNode(1,
            new TreeNode(2, new TreeNode(4), new TreeNode(5)),
            new TreeNode(3)
        );
        System.out.println("Test 1: " + solution.diameterOfBinaryTree(root1)); // Expected: 3

        // Test 2: [1, 2]
        //     1
        //    /
        //   2
        // Longest path: [2, 1] -> 1 edge
        TreeNode root2 = new TreeNode(1, new TreeNode(2), null);
        System.out.println("Test 2: " + solution.diameterOfBinaryTree(root2)); // Expected: 1

        // Test 3: Single node [1]
        TreeNode root3 = new TreeNode(1);
        System.out.println("Test 3: " + solution.diameterOfBinaryTree(root3)); // Expected: 0
    }
}
