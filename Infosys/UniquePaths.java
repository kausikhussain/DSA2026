package Infosys;

public class UniquePaths {
    /**
     * LeetCode 62: Unique Paths (Infosys)
     * 
     * There is a robot on an m x n grid. The robot is initially located at the top-left corner (0, 0).
     * The robot tries to move to the bottom-right corner (m - 1, n - 1). The robot can only move 
     * either down or right at any point in time.
     * 
     * Given the two integers m and n, return the number of possible unique paths that the robot 
     * can take to reach the bottom-right corner.
     */
    public int uniquePaths(int m, int n) {
        // We can solve this using 2D Dynamic Programming.
        // dp[i][j] represents the number of unique paths to reach cell (i, j).
        // Since we can only move down or right, dp[i][j] = dp[i-1][j] + dp[i][j-1].
        
        // Optimization: We only need the previous row's values to compute the current row's values,
        // so we can reduce space complexity to O(n) by using a 1D array.
        int[] dp = new int[n];
        
        // Base case: There is exactly 1 way to reach any cell in the first row (by only moving right)
        for (int j = 0; j < n; j++) {
            dp[j] = 1;
        }
        
        // Fill the DP table row by row
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // dp[j] (current row, cell j) = dp[j] (previous row, cell j) + dp[j-1] (current row, cell j-1)
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        
        return dp[n - 1];
    }

    public static void main(String[] args) {
        UniquePaths solution = new UniquePaths();
        
        System.out.println("Test 1 (m=3, n=7): " + solution.uniquePaths(3, 7)); // Expected: 28
        System.out.println("Test 2 (m=3, n=2): " + solution.uniquePaths(3, 2)); // Expected: 3
    }
}
