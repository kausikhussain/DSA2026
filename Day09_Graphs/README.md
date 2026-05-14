# Day 9: Graphs and Matrix DFS

## Theory
Graphs can be represented in multiple ways, but for coding interviews, a 2D matrix (grid) is one of the most common representations. Each cell is a "node", and its adjacent cells (up, down, left, right) are its "neighbors".

### Depth-First Search (DFS) on a Grid
To traverse a connected component (like an "island") on a grid, we usually use DFS.
The standard blueprint:
1. Iterate through every cell in the grid.
2. If you find a starting point (e.g., land '1'), increment your counter/start tracking area.
3. Call the `dfs()` function to explore the entire component.
4. **CRITICAL:** Inside `dfs()`, immediately check boundaries! If the row/col is out of bounds, `return`.
5. **CRITICAL:** Mark the current cell as "visited" (e.g., change '1' to '0'). This prevents infinite loops!
6. Recursively call `dfs()` on the 4 neighboring cells.
