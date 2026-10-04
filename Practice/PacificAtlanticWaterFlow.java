package Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacificAtlanticWaterFlow {
    /**
     * LeetCode 417: Pacific Atlantic Water Flow
     * 
     * An m x n rectangular island borders the Pacific Ocean on the top and left,
     * and the Atlantic Ocean on the bottom and right.
     * 
     * Water flows from a cell to adjacent cells of equal or lower height.
     * Return all grid coordinates [r, c] where water can reach both oceans.
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return result;
        }

        int rows = heights.length;
        int cols = heights[0].length;

        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        // Multi-source DFS from ocean borders flowing UPHILL (height >= current)
        // Pacific: top row & left col
        // Atlantic: bottom row & right col
        for (int c = 0; c < cols; c++) {
            dfs(heights, pacific, 0, c, heights[0][c]);
            dfs(heights, atlantic, rows - 1, c, heights[rows - 1][c]);
        }

        for (int r = 0; r < rows; r++) {
            dfs(heights, pacific, r, 0, heights[r][0]);
            dfs(heights, atlantic, r, cols - 1, heights[r][cols - 1]);
        }

        // Find intersection of cells reachable by both oceans
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
        // Out of bounds check, already visited check, or downhill flow violation
        if (r < 0 || r >= heights.length || c < 0 || c >= heights[0].length 
                || ocean[r][c] || heights[r][c] < prevHeight) {
            return;
        }

        ocean[r][c] = true;

        // Explore 4 adjacent neighbors
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
        System.out.println("Test 1: " + solution.pacificAtlantic(heights1));
        // Expected: [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]]

        int[][] heights2 = {{1}};
        System.out.println("Test 2: " + solution.pacificAtlantic(heights2));
        // Expected: [[0, 0]]
    }
}
