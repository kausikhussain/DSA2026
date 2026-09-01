package Practice;

import java.util.Arrays;

public class CoinChange {
    /**
     * LeetCode 322: Coin Change
     * 
     * You are given an integer array coins representing coins of different denominations 
     * and an integer amount representing a total amount of money.
     * 
     * Return the fewest number of coins that you need to make up that amount. 
     * If that amount of money cannot be made up by any combination of the coins, return -1.
     * 
     * You may assume that you have an infinite number of each kind of coin.
     */
    public int coinChange(int[] coins, int amount) {
        if (amount < 0) return -1;
        if (amount == 0) return 0;

        // dp[i] represents the minimum number of coins needed to make amount i
        int[] dp = new int[amount + 1];

        // Fill array with a value greater than any possible answer (amount + 1)
        int max = amount + 1;
        Arrays.fill(dp, max);

        // Base case: 0 amount requires 0 coins
        dp[0] = 0;

        // Bottom-up DP: compute minimum coins for every sub-amount from 1 to amount
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }

        // If dp[amount] is still max, it means the amount could not be formed
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        CoinChange solution = new CoinChange();

        int[] coins1 = {1, 2, 5};
        int amount1 = 11;
        System.out.println("Test 1 [1, 2, 5] amount 11: " + solution.coinChange(coins1, amount1)); 
        // Expected: 3 (5 + 5 + 1)

        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("Test 2 [2] amount 3: " + solution.coinChange(coins2, amount2)); 
        // Expected: -1

        int[] coins3 = {1};
        int amount3 = 0;
        System.out.println("Test 3 [1] amount 0: " + solution.coinChange(coins3, amount3)); 
        // Expected: 0
    }
}
