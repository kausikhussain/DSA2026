package Practice;

public class BuyAndSellStock {
    /**
     * Practice Question: Best Time to Buy and Sell Stock
     * 
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     * You want to maximize your profit by choosing a single day to buy one stock 
     * and choosing a different day in the future to sell that stock.
     * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        
        for (int i = 0; i < prices.length; i++) {
            // Update the minimum price seen so far
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } 
            // Calculate the potential profit if we sold today, and update maxProfit if it's higher
            else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        
        return maxProfit;
    }

    public static void main(String[] args) {
        BuyAndSellStock solution = new BuyAndSellStock();
        
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("Test 1 [7,1,5,3,6,4]: " + solution.maxProfit(prices1)); // Expected: 5 (buy at 1, sell at 6)
        
        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println("Test 2 [7,6,4,3,1]: " + solution.maxProfit(prices2)); // Expected: 0 (no profit possible)
    }
}
