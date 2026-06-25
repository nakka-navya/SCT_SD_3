public class SudokuSolver {

    private static final int SIZE = 9;

    // Solve the Sudoku puzzle using backtracking
    public static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {

                // Find an empty cell
                if (board[row][col] == 0) {

                    // Try numbers 1 through 9
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;

                            // Recursively solve the rest of the puzzle
                            if (solveSudoku(board)) {
                                return true;
                            }

                            // Backtrack if solution is not found
                            board[row][col] = 0;
                        }
                    }

                    // No valid number found
                    return false;
                }
            }
        }

        // Puzzle solved
        return true;
    }

    // Check if placing num at board[row][col] is valid
    public static boolean isValid(int[][] board, int row, int col, int num) {

        // Check row
        for (int c = 0; c < SIZE; c++) {
            if (board[row][c] == num) {
                return false;
            }
        }

        // Check column
        for (int r = 0; r < SIZE; r++) {
            if (board[r][col] == num) {
                return false;
            }
        }

        // Check 3x3 subgrid
        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (board[r][c] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // Print the Sudoku board
    public static void printBoard(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int[][] puzzle = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        System.out.println("Original Sudoku Puzzle:");
        printBoard(puzzle);

        if (solveSudoku(puzzle)) {
            System.out.println("\nSolved Sudoku Puzzle:");
            printBoard(puzzle);
        } else {
            System.out.println("No solution exists.");
        }
    }
}