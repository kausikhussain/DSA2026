package Practice;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * LeetCode 239: Sliding Window Maximum
 * Difficulty: Hard
 * 
 * You are given an array of integers nums, there is a sliding window of size k 
 * which is moving from the very left of the array to the very right. You can only 
 * see the k numbers in the window. Each time the sliding window moves right by one position.
 * 
 * Return the max sliding window.
 * 
 * Approach:
 * - Monotonic Decreasing Deque (storing array indices).
 * - The front of the deque always holds the index of the maximum element in the current window.
 * - For each element at index i:
 *   1. Out-of-bounds cleanup: Evict indices from the front that are older than (i - k + 1).
 *   2. Monotonic maintenance: Evict indices from the back whose values are smaller than nums[i],
 *      as they can never be the maximum in any subsequent window that includes nums[i].
 *   3. Push current index i to the back of the deque.
 *   4. Starting at index i >= k - 1, record nums[deque.peekFirst()] into the result array.
 * 
 * Complexity:
 * - Time: O(n), where n is the length of nums. Each element index is added to and removed from
 *         the deque at most once.
 * - Space: O(k), auxiliary space used by the deque storing at most k indices.
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // Step 1: Remove indices out of current window boundary [i - k + 1, i]
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // Step 2: Remove smaller elements from the back of deque to preserve monotonic decreasing order
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // Step 3: Add current index to the back
            deque.offerLast(i);

            // Step 4: Record maximum once we have traversed at least k elements
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        SlidingWindowMaximum solution = new SlidingWindowMaximum();

        // Test 1: Standard example
        // nums = [1,3,-1,-3,5,3,6,7], k = 3
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        System.out.println("Test 1: " + Arrays.toString(solution.maxSlidingWindow(nums1, k1)));
        // Expected: [3, 3, 5, 5, 6, 7]

        // Test 2: Single element array
        int[] nums2 = {1};
        int k2 = 1;
        System.out.println("Test 2: " + Arrays.toString(solution.maxSlidingWindow(nums2, k2)));
        // Expected: [1]

        // Test 3: k equals array length
        int[] nums3 = {9, 11};
        int k3 = 2;
        System.out.println("Test 3: " + Arrays.toString(solution.maxSlidingWindow(nums3, k3)));
        // Expected: [11]

        // Test 4: Strictly decreasing array
        int[] nums4 = {7, 6, 5, 4, 3};
        int k4 = 3;
        System.out.println("Test 4: " + Arrays.toString(solution.maxSlidingWindow(nums4, k4)));
        // Expected: [7, 6, 5]
    }
}
