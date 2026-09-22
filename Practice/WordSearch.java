package Practice;

public class WordSearch {
    /**
     * LeetCode 79: Word Search
     * 
     * Given an m x n grid of characters board and a string word, return true if word 
     * exists in the grid.
     * 
     * The word can be constructed from letters of sequentially adjacent cells, where adjacent 
     * cells are horizontally or vertically neighboring. The same letter cell may not be used 
     * more than once.
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.isEmpty()) {
            return false;
        }

        int rows = board.length;
        int cols = board[0].length;

        // Try starting search from every cell that matches word[0]
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == word.charAt(0)) {
                    if (dfs(board, word, r, c, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, int r, int c, int index) {
        // Base case: matched all characters in word
        if (index == word.length()) {
            return true;
        }

        // Boundary checks and character match check
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word.charAt(index)) {
            return false;
        }

        // Mark current cell as visited in-place using a sentinel character
        char original = board[r][c];
        board[r][c] = '#';

        // Explore all 4 adjacent directions (up, down, left, right)
        boolean found = dfs(board, word, r + 1, c, index + 1)
                     || dfs(board, word, r - 1, c, index + 1)
                     || dfs(board, word, r, c + 1, index + 1)
                     || dfs(board, word, r, c - 1, index + 1);

        // Restore original character (backtrack)
        board[r][c] = original;

        return found;
    }

    public static void main(String[] args) {
        WordSearch solution = new WordSearch();

        char[][] board1 = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };

        System.out.println("Test 1 (\"ABCCED\"): " + solution.exist(board1, "ABCCED")); // Expected: true
        System.out.println("Test 2 (\"SEE\"): " + solution.exist(board1, "SEE"));       // Expected: true
        System.out.println("Test 3 (\"ABCB\"): " + solution.exist(board1, "ABCB"));     // Expected: false
    }
}
