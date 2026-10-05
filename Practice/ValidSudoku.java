package Practice;

public class ValidSudoku {
    /**
     * LeetCode 36: Valid Sudoku
     * 
     * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated 
     * according to the following rules:
     * 1. Each row must contain the digits 1-9 without repetition.
     * 2. Each column must contain the digits 1-9 without repetition.
     * 3. Each of the nine 3 x 3 sub-boxes must contain the digits 1-9 without repetition.
     */
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9) {
            return false;
        }

        // Track seen digits (0-8 representing '1'-'9') for rows, columns, and 3x3 boxes
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];

                if (ch == '.') {
                    continue;
                }

                int digit = ch - '1';
                int boxIndex = (r / 3) * 3 + (c / 3);

                // If digit has already appeared in the current row, column, or 3x3 sub-box
                if (rows[r][digit] || cols[c][digit] || boxes[boxIndex][digit]) {
                    return false;
                }

                // Mark digit as seen in row, column, and box
                rows[r][digit] = true;
                cols[c][digit] = true;
                boxes[boxIndex][digit] = true;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        ValidSudoku solution = new ValidSudoku();

        char[][] board1 = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println("Test 1: " + solution.isValidSudoku(board1)); 
        // Expected: true

        char[][] board2 = {
            {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println("Test 2: " + solution.isValidSudoku(board2)); 
        // Expected: false (top-left 8 and row 4 col 0 8 conflict in col 0)
    }
}
