package Day11_2D_DP;

public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        
        // DP table of size (m+1) x (n+1) initialized to 0
        int[][] dp = new int[m + 1][n + 1];
        
        // Build the table bottom-up
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // Characters match: add 1 to the result of the remaining strings
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    // Characters don't match: take the max by skipping one char from either string
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m][n];
    }
}
