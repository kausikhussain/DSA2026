package Practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * LeetCode 105: Construct Binary Tree from Preorder and Inorder Traversal
 * Difficulty: Medium
 * 
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal 
 * of a binary tree and inorder is the inorder traversal of the same tree, construct 
 * and return the binary tree.
 * 
 * Approach:
 * - Divide and Conquer with Hash Map Optimization.
 * - In preorder traversal (Root -> Left -> Right), the first element is always the root.
 * - In inorder traversal (Left -> Root -> Right), elements to the left of the root value 
 *   belong to the left subtree, and elements to the right belong to the right subtree.
 * - Use a HashMap to store (inorder value -> index) for O(1) partition lookups.
 * - Recursively construct left and right subtrees by slicing the inorder boundaries.
 * 
 * Complexity:
 * - Time: O(n), each node is processed once and index lookups are O(1).
 * - Space: O(n) for the hash map and recursive call stack.
 */
public class ConstructBinaryTreePreorderInorder {

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

    private int preorderIndex;
    private Map<Integer, Integer> inorderMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) {
            return null;
        }

        this.preorderIndex = 0;
        this.inorderMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return build(preorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }

        int rootVal = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootVal);

        int rootIndexInorder = inorderMap.get(rootVal);

        // Crucial: Left subtree must be constructed before right subtree because
        // preorder traversal follows Root -> Left -> Right order.
        root.left = build(preorder, inStart, rootIndexInorder - 1);
        root.right = build(preorder, rootIndexInorder + 1, inEnd);

        return root;
    }

    // Helper method to serialize tree to level-order list for display
    public static List<Integer> levelOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                result.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ConstructBinaryTreePreorderInorder solution = new ConstructBinaryTreePreorderInorder();

        // Test 1: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
        //         3
        //        / \
        //       9  20
        //         /  \
        //        15   7
        int[] preorder1 = {3, 9, 20, 15, 7};
        int[] inorder1 = {9, 3, 15, 20, 7};
        TreeNode root1 = solution.buildTree(preorder1, inorder1);
        System.out.println("Test 1 Level-order: " + levelOrder(root1));
        // Expected: [3, 9, 20, 15, 7]

        // Test 2: Single node
        int[] preorder2 = {-1};
        int[] inorder2 = {-1};
        TreeNode root2 = solution.buildTree(preorder2, inorder2);
        System.out.println("Test 2 Level-order: " + levelOrder(root2));
        // Expected: [-1]

        // Test 3: Left-skewed tree
        int[] preorder3 = {1, 2, 3};
        int[] inorder3 = {3, 2, 1};
        TreeNode root3 = solution.buildTree(preorder3, inorder3);
        System.out.println("Test 3 Level-order: " + levelOrder(root3));
        // Expected: [1, 2, 3]
    }
}
