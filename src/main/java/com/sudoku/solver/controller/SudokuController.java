package com.sudoku.solver.controller;

import com.sudoku.solver.service.PuzzleGenerator;
import com.sudoku.solver.service.SudokuSolver;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class SudokuController {

    SudokuSolver solver = new SudokuSolver();
    PuzzleGenerator generator = new PuzzleGenerator();

    @PostMapping("/solve")
    public char[][] solve(@RequestBody char[][] board) {
        long start = System.nanoTime();
        solver.solveSudoku(board);
        long end = System.nanoTime();

        System.out.println("Solved in ms = " + (end - start)/1_000_000);
        return board;
    }

    @GetMapping("/generate/{difficulty}")
    public char[][] generatePuzzle(@PathVariable String difficulty) {
        return generator.generate(difficulty);
    }
}
