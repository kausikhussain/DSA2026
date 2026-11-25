package Practice;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * LeetCode 973: K Closest Points to Origin
 * Difficulty: Medium
 * 
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane 
 * and an integer k, return the k closest points to the origin (0, 0).
 * 
 * The distance between two points on the X-Y plane is the Euclidean distance:
 * sqrt((x1 - x2)^2 + (y1 - y2)^2).
 * 
 * You may return the answer in any order. The answer is guaranteed to be unique 
 * (except for the order that it is in).
 * 
 * Approach:
 * - Max-Heap of size K (PriorityQueue).
 * - Since sqrt is monotonic, comparing Euclidean distance is equivalent to comparing
 *   squared distance: dist = x^2 + y^2 (avoiding floating point precision loss).
 * - A Max-Heap keeps the largest distance among the K candidates at its root.
 * - Iterate through all points:
 *   - Offer each point to the heap.
 *   - If heap size exceeds k, evict the point with the largest distance from root.
 * - Remaining k elements in the heap are guaranteed to be the k closest points.
 * 
 * Complexity:
 * - Time: O(n log k), where n is the number of points and k is the heap capacity.
 * - Space: O(k) auxiliary space used by the heap.
 */
public class KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int k) {
        if (points == null || points.length == 0 || k <= 0) {
            return new int[0][0];
        }

        // Max-heap: largest squared distance sits at the head
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(
                (b[0] * b[0] + b[1] * b[1]),
                (a[0] * a[0] + a[1] * a[1])
            )
        );

        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // Evict farthest point
            }
        }

        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }

        return result;
    }

    public static void main(String[] args) {
        KClosestPointsToOrigin solution = new KClosestPointsToOrigin();

        // Test 1: points = [[1,3],[-2,2]], k = 1
        // d([1, 3]) = 1^2 + 3^2 = 10
        // d([-2, 2]) = (-2)^2 + 2^2 = 8
        // Closest: [-2, 2]
        int[][] points1 = {{1, 3}, {-2, 2}};
        int k1 = 1;
        System.out.println("Test 1: " + Arrays.deepToString(solution.kClosest(points1, k1)));
        // Expected: [[-2, 2]]

        // Test 2: points = [[3,3],[5,-1],[-2,4]], k = 2
        // d([3, 3]) = 18
        // d([5, -1]) = 26
        // d([-2, 4]) = 20
        // Closest 2: [3, 3] and [-2, 4]
        int[][] points2 = {{3, 3}, {5, -1}, {-2, 4}};
        int k2 = 2;
        System.out.println("Test 2: " + Arrays.deepToString(solution.kClosest(points2, k2)));
        // Expected: [[-2, 4], [3, 3]] (in any order)

        // Test 3: k equals points length
        int[][] points3 = {{0, 1}, {1, 0}};
        int k3 = 2;
        System.out.println("Test 3: " + Arrays.deepToString(solution.kClosest(points3, k3)));
        // Expected: [[1, 0], [0, 1]] (in any order)
    }
}
