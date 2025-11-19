package com.sudoku.solver.service;

import java.util.Random;

public class PuzzleGenerator {

    private final SudokuSolver solver = new SudokuSolver();
    private final Random random = new Random();

    public char[][] generate(String level) {

        int clues;

        switch (level.toLowerCase()) {
            case "easy": clues = 40; break;
            case "medium": clues = 30; break;
            case "hard": clues = 22; break;
            default: clues = 35;
        }

        char[][] board = new char[9][9];

        solver.solveSudoku(board = emptyBoard());

        removeCells(board, 81 - clues);

        return board;
    }

    private char[][] emptyBoard() {
        char[][] b = new char[9][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                b[i][j] = '.';
        return b;
    }

    private void removeCells(char[][] board, int remove) {
        while (remove > 0) {
            int r = random.nextInt(9);
            int c = random.nextInt(9);

            if (board[r][c] != '.') {
                board[r][c] = '.';
                remove--;
            }
        }
    }
}
