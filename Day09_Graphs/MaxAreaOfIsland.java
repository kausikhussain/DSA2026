package Day09_Graphs;

public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int maxArea = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    // Start a DFS and get the area of the current island
                    int currentArea = dfs(grid, i, j);
                    maxArea = Math.max(maxArea, currentArea);
                }
            }
        }
        
        return maxArea;
    }
    
    private int dfs(int[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Base case: check bounds and if it's water
        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] == 0) {
            return 0;
        }
        
        // Mark as visited to avoid infinite loops
        grid[r][c] = 0;
        
        // Area is 1 (current cell) + area of 4 adjacent cells
        int area = 1;
        area += dfs(grid, r - 1, c);
        area += dfs(grid, r + 1, c);
        area += dfs(grid, r, c - 1);
        area += dfs(grid, r, c + 1);
        
        return area;
    }
}
