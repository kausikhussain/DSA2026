package Day10_1D_DP;

public class ClimbingStairs {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        
        // This is essentially the Fibonacci sequence
        int oneStepBefore = 2;
        int twoStepsBefore = 1;
        int current = 0;
        
        for (int i = 3; i <= n; i++) {
            current = oneStepBefore + twoStepsBefore;
            twoStepsBefore = oneStepBefore;
            oneStepBefore = current;
        }
        
        return current;
    }
}
