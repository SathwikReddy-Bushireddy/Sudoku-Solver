package com.sudoku.solver.service;

public class SudokuSolver {

    public void solveSudoku(char[][] board) {
        helper(board, 0, 0);
    }

    private boolean helper(char[][] board, int r, int c) {
        if (r == board.length) return true;

        int nr = 0, nc = 0;

        if (c != board.length - 1) {
            nr = r;
            nc = c + 1;
        } else {
            nr = r + 1;
            nc = 0;
        }

        if (board[r][c] != '.') {
            return helper(board, nr, nc);
        } else {
            for (int i = 1; i <= 9; i++) {
                if (isSafe(board, r, c, i)) {
                    board[r][c] = (char) (i + '0');

                    if (helper(board, nr, nc)) {
                        return true;
                    }

                    board[r][c] = '.';
                }
            }
        }
        return false;
    }

    private boolean isSafe(char[][] board, int r, int c, int num) {

        for (int i = 0; i < 9; i++) {
            if (board[i][c] == (char)(num + '0')) return false;
            if (board[r][i] == (char)(num + '0')) return false;
        }

        int nr = (r / 3) * 3;
        int nc = (c / 3) * 3;

        for (int i = nr; i < nr + 3; i++) {
            for (int j = nc; j < nc + 3; j++) {
                if (board[i][j] == (char)(num + '0')) return false;
            }
        }

        return true;
    }
}
