package Practice;

public class JumpGameII {
    /**
     * LeetCode 45: Jump Game II
     * 
     * You are given a 0-indexed array of integers nums of length n. 
     * You are initially positioned at nums[0].
     * 
     * Each element nums[i] represents the maximum length of a forward jump from index i.
     * Return the minimum number of jumps to reach nums[n - 1].
     */
    public int jump(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        int jumps = 0;
        int currentEnd = 0; // Boundary of the current jump reach
        int farthest = 0;   // Furthest index reachable with one additional jump

        // We only iterate up to nums.length - 2 because once we reach or pass
        // nums.length - 1, we don't need to jump again.
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            // When we reach the boundary of the current jump, we MUST take another jump
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;

                // Early exit if the end is already reachable
                if (currentEnd >= nums.length - 1) {
                    break;
                }
            }
        }

        return jumps;
    }

    public static void main(String[] args) {
        JumpGameII solution = new JumpGameII();

        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println("Test 1: " + solution.jump(nums1)); 
        // Expected: 2 (Jump 1 step from index 0 to 1, then 3 steps to the last index)

        int[] nums2 = {2, 3, 0, 1, 4};
        System.out.println("Test 2: " + solution.jump(nums2)); 
        // Expected: 2

        int[] nums3 = {0};
        System.out.println("Test 3: " + solution.jump(nums3)); 
        // Expected: 0
    }
}
