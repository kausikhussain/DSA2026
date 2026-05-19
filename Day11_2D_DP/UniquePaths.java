package Day11_2D_DP;

public class UniquePaths {
    public int uniquePaths(int m, int n) {
        // Create a 2D DP array
        int[][] dp = new int[m][n];
        
        // Base cases: the top row and left column only have 1 unique path (going straight)
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        
        // Fill the rest of the DP table
        // The number of paths to any cell is the sum of paths to the cell above and the cell to the left
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        
        // The bottom-right cell contains the total unique paths
        return dp[m-1][n-1];
    }
}
