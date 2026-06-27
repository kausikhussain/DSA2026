package Practice;

public class ClimbingStairs {
    /**
     * Practice Question: Climbing Stairs
     * 
     * You are climbing a staircase. It takes n steps to reach the top.
     * Each time you can either climb 1 or 2 steps. 
     * In how many distinct ways can you climb to the top?
     */
    public int climbStairs(int n) {
        // Base cases: 1 step = 1 way, 2 steps = 2 ways
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        // Approach: Dynamic Programming (Fibonacci Sequence)
        // To reach step N, you must have come from either step N-1 (took a 1-step) 
        // or step N-2 (took a 2-step). 
        // Therefore, Ways(N) = Ways(N-1) + Ways(N-2).
        
        int prev2 = 1; // Ways to reach step 1
        int prev1 = 2; // Ways to reach step 2
        
        for (int i = 3; i <= n; i++) {
            int current = prev1 + prev2;
            // Shift our variables forward for the next iteration
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }

    public static void main(String[] args) {
        ClimbingStairs solution = new ClimbingStairs();
        
        System.out.println("Ways to climb 2 stairs: " + solution.climbStairs(2)); // Expected: 2
        System.out.println("Ways to climb 3 stairs: " + solution.climbStairs(3)); // Expected: 3
        System.out.println("Ways to climb 5 stairs: " + solution.climbStairs(5)); // Expected: 8
    }
}
