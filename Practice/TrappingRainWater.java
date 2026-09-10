package Practice;

public class TrappingRainWater {
    /**
     * LeetCode 42: Trapping Rain Water
     * 
     * Given n non-negative integers representing an elevation map where the width 
     * of each bar is 1, compute how much water it can trap after raining.
     */
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0; // Need at least 3 bars to form a container
        }

        // Two Pointers approach: O(N) time and O(1) space
        int left = 0;
        int right = height.length - 1;

        int leftMax = 0;
        int rightMax = 0;
        int totalWater = 0;

        while (left < right) {
            if (height[left] <= height[right]) {
                // If current left bar is higher than leftMax, update leftMax
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    // Water trapped at left position depends on leftMax
                    totalWater += leftMax - height[left];
                }
                left++;
            } else {
                // If current right bar is higher than rightMax, update rightMax
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    // Water trapped at right position depends on rightMax
                    totalWater += rightMax - height[right];
                }
                right--;
            }
        }

        return totalWater;
    }

    public static void main(String[] args) {
        TrappingRainWater solution = new TrappingRainWater();

        int[] height1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Test 1: " + solution.trap(height1)); 
        // Expected: 6

        int[] height2 = {4, 2, 0, 3, 2, 5};
        System.out.println("Test 2: " + solution.trap(height2)); 
        // Expected: 9

        int[] height3 = {1, 2};
        System.out.println("Test 3: " + solution.trap(height3)); 
        // Expected: 0
    }
}
