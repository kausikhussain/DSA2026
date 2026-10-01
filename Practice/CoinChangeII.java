package Practice;

public class CoinChangeII {
    /**
     * LeetCode 518: Coin Change II
     * 
     * You are given an integer array coins representing coins of different denominations 
     * and an integer amount representing a total amount of money.
     * 
     * Return the number of combinations that make up that amount. 
     * If that amount of money cannot be made up by any combination of the coins, return 0.
     * 
     * You may assume that you have an infinite number of each kind of coin.
     */
    public int change(int amount, int[] coins) {
        if (coins == null) return 0;

        // dp[j] stores the number of unique combinations to make amount j
        int[] dp = new int[amount + 1];

        // Base case: There is exactly 1 way to make amount 0 (choose no coins)
        dp[0] = 1;

        // CRUCIAL: Iterate through coins in the OUTER loop to count COMBINATIONS (order doesn't matter).
        // If amount was in the outer loop, it would count PERMUTATIONS instead.
        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] += dp[j - coin];
            }
        }

        return dp[amount];
    }

    public static void main(String[] args) {
        CoinChangeII solution = new CoinChangeII();

        int[] coins1 = {1, 2, 5};
        int amount1 = 5;
        System.out.println("Test 1 (amount 5, coins [1, 2, 5]): " + solution.change(amount1, coins1));
        // Expected: 4 (5=5, 5=2+2+1, 5=2+1+1+1, 5=1+1+1+1+1)

        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("Test 2 (amount 3, coins [2]): " + solution.change(amount2, coins2));
        // Expected: 0

        int[] coins3 = {10};
        int amount3 = 10;
        System.out.println("Test 3 (amount 10, coins [10]): " + solution.change(amount3, coins3));
        // Expected: 1
    }
}
