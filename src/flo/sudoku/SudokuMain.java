package flo.sudoku;

import org.fusesource.jansi.AnsiConsole;

public class SudokuMain {
	public static void main(String[] args) {
		System.setProperty("jansi.passthrough", "true");
		AnsiConsole.systemInstall();

		SudokuSolver sudokuSolver = new SudokuSolver();
		int[][] sudoku = new int[][] {
				{ 0, 0, 0, 0, 0, 1, 0, 6, 0 },
				{ 0, 0, 2, 0, 6, 0, 0, 9, 7 },
				{ 0, 0, 0, 3, 0, 0, 0, 4, 0 },
				{ 0, 0, 0, 0, 4, 0, 0, 0, 3 },
				{ 5, 0, 6, 0, 0, 0, 7, 0, 8 },
				{ 9, 0, 0, 0, 1, 0, 0, 0, 0 },
				{ 0, 1, 0, 0, 0, 7, 0, 0, 0 },
				{ 7, 9, 0, 0, 8, 0, 6, 0, 0 },
				{ 0, 3, 0, 5, 0, 0, 0, 0, 0 }
		};

		int[][] sudoku2 = new int[][] {
				{ 9, 4, 0, 0, 1, 5, 0, 7, 0 },
				{ 0, 0, 0, 0, 0, 6, 5, 0, 4 },
				{ 0, 0, 0, 0, 0, 9, 8, 0, 0 },
				{ 0, 8, 0, 0, 0, 0, 0, 0, 0 },
				{ 5, 0, 7, 0, 0, 0, 6, 0, 2 },
				{ 0, 0, 0, 0, 0, 0, 0, 4, 0 },
				{ 0, 0, 4, 9, 0, 0, 0, 0, 0 },
				{ 8, 0, 2, 5, 0, 0, 0, 0, 0 },
				{ 0, 7, 0, 6, 8, 0, 0, 1, 9 }
		};

		long stopWatch = System.currentTimeMillis();
		sudokuSolver.solveSudoku(sudoku);
		// for (int i = 0; i < 10000; i++) {
		// LOG(true, "\n\n\nIUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU " + i);
		// }
		System.out.println("took " + (System.currentTimeMillis() - stopWatch) + "ms.");
		sudokuSolver.solveSudoku(sudoku2);
		sudokuSolver.sudokuToString(sudoku2);
	}
}
