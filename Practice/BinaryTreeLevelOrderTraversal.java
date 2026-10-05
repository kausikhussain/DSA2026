package Practice;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {

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
     * LeetCode 102: Binary Tree Level Order Traversal
     * 
     * Given the root of a binary tree, return the level order traversal of its nodes' values.
     * (i.e., from left to right, level by level).
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(currentLevel);
        }

        return result;
    }

    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal solution = new BinaryTreeLevelOrderTraversal();

        // Construct tree: [3, 9, 20, null, null, 15, 7]
        //       3
        //      / \
        //     9  20
        //       /  \
        //      15   7
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20, new TreeNode(15), new TreeNode(7));

        System.out.println("Test 1: " + solution.levelOrder(root1));
        // Expected: [[3], [9, 20], [15, 7]]

        // Test 2: [1]
        TreeNode root2 = new TreeNode(1);
        System.out.println("Test 2: " + solution.levelOrder(root2));
        // Expected: [[1]]

        // Test 3: []
        System.out.println("Test 3: " + solution.levelOrder(null));
        // Expected: []
    }
}
