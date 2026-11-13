package Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PacificAtlanticWaterFlow {
    /**
     * LeetCode 417: Pacific Atlantic Water Flow
     * Difficulty: Medium
     * 
     * An m x n rectangular island borders the Pacific Ocean on the top and left,
     * and the Atlantic Ocean on the bottom and right.
     * 
     * Water flows from a cell to adjacent cells of equal or lower height.
     * Return all grid coordinates [r, c] where water can reach both oceans.
     * 
     * Approach (Multi-Source BFS):
     * - Water flowing downhill into the ocean is equivalent to ocean water flowing uphill
     *   (neighbor height >= current height) from the borders into the island.
     * - Queue 1 (Pacific): Initialized with all cells on the top row and left column.
     * - Queue 2 (Atlantic): Initialized with all cells on the bottom row and right column.
     * - Perform BFS for Pacific and Atlantic separately, marking reachable cells.
     * - Collect all coordinates [r, c] that are reachable by both oceans.
     * 
     * Complexity:
     * - Time: O(m * n), each cell is enqueued at most once per ocean.
     * - Space: O(m * n) for visited matrices and BFS queues.
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return new ArrayList<>();
        }

        int m = heights.length;
        int n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        Queue<int[]> pacificQueue = new LinkedList<>();
        Queue<int[]> atlanticQueue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            addCell(i, 0, pacific, pacificQueue);
            addCell(i, n - 1, atlantic, atlanticQueue);
        }
        for (int j = 0; j < n; j++) {
            addCell(0, j, pacific, pacificQueue);
            addCell(m - 1, j, atlantic, atlanticQueue);
        }

        bfs(heights, pacificQueue, pacific);
        bfs(heights, atlanticQueue, atlantic);

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }

    private void addCell(int r, int c, boolean[][] visited, Queue<int[]> queue) {
        if (!visited[r][c]) {
            visited[r][c] = true;
            queue.offer(new int[]{r, c});
        }
    }

    private void bfs(int[][] heights, Queue<int[]> queue, boolean[][] visited) {
        int m = heights.length;
        int n = heights[0].length;
        int[][] directions = {
            {-1, 0},  
            {1, 0},   
            {0, -1},  
            {0, 1}   
        };

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }
                if (visited[nr][nc]) {
                    continue;
                }
                if (heights[nr][nc] >= heights[r][c]) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        } 
    }

    /**
     * Alternative Approach (Multi-Source DFS) for comparison.
     */
    public List<List<Integer>> pacificAtlanticDFS(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return result;
        }

        int rows = heights.length;
        int cols = heights[0].length;

        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        for (int c = 0; c < cols; c++) {
            dfs(heights, pacific, 0, c, heights[0][c]);
            dfs(heights, atlantic, rows - 1, c, heights[rows - 1][c]);
        }

        for (int r = 0; r < rows; r++) {
            dfs(heights, pacific, r, 0, heights[r][0]);
            dfs(heights, atlantic, r, cols - 1, heights[r][cols - 1]);
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (pacific[r][c] && atlantic[r][c]) {
                    result.add(Arrays.asList(r, c));
                }
            }
        }

        return result;
    }

    private void dfs(int[][] heights, boolean[][] ocean, int r, int c, int prevHeight) {
        if (r < 0 || r >= heights.length || c < 0 || c >= heights[0].length 
                || ocean[r][c] || heights[r][c] < prevHeight) {
            return;
        }

        ocean[r][c] = true;

        dfs(heights, ocean, r + 1, c, heights[r][c]);
        dfs(heights, ocean, r - 1, c, heights[r][c]);
        dfs(heights, ocean, r, c + 1, heights[r][c]);
        dfs(heights, ocean, r, c - 1, heights[r][c]);
    }

    public static void main(String[] args) {
        PacificAtlanticWaterFlow solution = new PacificAtlanticWaterFlow();

        int[][] heights1 = {
            {1, 2, 2, 3, 5},
            {3, 2, 3, 4, 4},
            {2, 4, 5, 3, 1},
            {6, 7, 1, 4, 5},
            {5, 1, 1, 2, 4}
        };
        System.out.println("Test 1 (BFS): " + solution.pacificAtlantic(heights1));
        System.out.println("Test 1 (DFS): " + solution.pacificAtlanticDFS(heights1));
        // Expected: [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]]

        int[][] heights2 = {{1}};
        System.out.println("Test 2 (BFS): " + solution.pacificAtlantic(heights2));
        System.out.println("Test 2 (DFS): " + solution.pacificAtlanticDFS(heights2));
        // Expected: [[0, 0]]
    }
}
