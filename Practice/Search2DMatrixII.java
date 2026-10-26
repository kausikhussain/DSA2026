package Practice;

/**
 * LeetCode 240: Search a 2D Matrix II
 * Difficulty: Medium
 * 
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix. 
 * This matrix has the following properties:
 * - Integers in each row are sorted in ascending from left to right.
 * - Integers in each column are sorted in ascending from top to bottom.
 * 
 * Approach:
 * - Staircase Search (Pruning from Top-Right Corner).
 * - Start at the top-right corner: (row = 0, col = n - 1).
 * - At any cell (row, col):
 *   - If matrix[row][col] == target, target is found -> return true.
 *   - If matrix[row][col] > target, all elements below it in the same column are also
 *     greater than target. We can safely discard this column by moving left (col--).
 *   - If matrix[row][col] < target, all elements to its left in the same row are also
 *     smaller than target. We can safely discard this row by moving down (row++).
 * - Terminate when row reaches m or col reaches -1 without finding target.
 * 
 * Complexity:
 * - Time: O(m + n), in the worst case we traverse at most m rows and n columns.
 * - Space: O(1), constant extra memory.
 */
public class Search2DMatrixII {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        // Start search at top-right corner
        int row = 0;
        int col = n - 1;

        while (row < m && col >= 0) {
            int current = matrix[row][col];

            if (current == target) {
                return true;
            } else if (current > target) {
                col--; // Target must be in a column to the left
            } else {
                row++; // Target must be in a row below
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Search2DMatrixII solution = new Search2DMatrixII();

        int[][] matrix = {
            { 1,  4,  7, 11, 15},
            { 2,  5,  8, 12, 19},
            { 3,  6,  9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        };

        // Test 1: Element present in matrix (5)
        System.out.println("Test 1 (Target = 5): " + solution.searchMatrix(matrix, 5)); // Expected: true

        // Test 2: Element not present in matrix (20)
        System.out.println("Test 2 (Target = 20): " + solution.searchMatrix(matrix, 20)); // Expected: false

        // Test 3: Smallest element (1)
        System.out.println("Test 3 (Target = 1): " + solution.searchMatrix(matrix, 1)); // Expected: true

        // Test 4: Largest element (30)
        System.out.println("Test 4 (Target = 30): " + solution.searchMatrix(matrix, 30)); // Expected: true

        // Test 5: 1x1 matrix
        int[][] single = {{5}};
        System.out.println("Test 5 (1x1 Target = 5): " + solution.searchMatrix(single, 5)); // Expected: true
        System.out.println("Test 6 (1x1 Target = 2): " + solution.searchMatrix(single, 2)); // Expected: false
    }
}
