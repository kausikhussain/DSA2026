package Practice;

import java.util.ArrayDeque;
import java.util.Queue;

public class RottingOranges {
    /**
     * LeetCode 994: Rotting Oranges
     * 
     * You are given an m x n grid where each cell can have one of three values:
     * - 0 representing an empty cell,
     * - 1 representing a fresh orange, or
     * - 2 representing a rotten orange.
     * 
     * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange 
     * becomes rotten.
     * 
     * Return the minimum number of minutes that must elapse until no cell has a fresh orange. 
     * If this is impossible, return -1.
     */
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new ArrayDeque<>();
        int freshCount = 0;

        // Step 1: Initialize queue with all initially rotten oranges, and count fresh oranges
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    freshCount++;
                }
            }
        }

        // If there are no fresh oranges from the start, 0 minutes needed
        if (freshCount == 0) return 0;

        int minutes = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Step 2: Multi-source BFS layer by layer (each layer represents 1 elapsed minute)
        while (!queue.isEmpty() && freshCount > 0) {
            int size = queue.size();
            minutes++;

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];

                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    // Check bounds and if neighbor is a fresh orange
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2; // Mark as rotten
                        freshCount--;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }

        // If fresh oranges still remain, they could not be reached
        return freshCount == 0 ? minutes : -1;
    }

    public static void main(String[] args) {
        RottingOranges solution = new RottingOranges();

        int[][] grid1 = {
            {2, 1, 1},
            {1, 1, 0},
            {0, 1, 1}
        };
        System.out.println("Test 1: " + solution.orangesRotting(grid1)); 
        // Expected: 4

        int[][] grid2 = {
            {2, 1, 1},
            {0, 1, 1},
            {1, 0, 1}
        };
        System.out.println("Test 2: " + solution.orangesRotting(grid2)); 
        // Expected: -1 (bottom-left orange never rots)

        int[][] grid3 = {
            {0, 2}
        };
        System.out.println("Test 3: " + solution.orangesRotting(grid3)); 
        // Expected: 0 (no fresh oranges)
    }
}
