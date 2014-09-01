package flo.sudoku;

import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.Ansi.Color.BLUE;
import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.RED;

import java.util.List;
import java.util.PriorityQueue;

public class SudokuSolver {

	private SudokuGraphManager sManager = new SudokuGraphManager();

	public int[][] solveSudoku(int[][] sudoku) {
		// create date structure
		SudokuGraph graph = sManager.convert(sudoku);

		// print sudoku exercise
		LOG(true, "Exercise");
		printSudoku(graph);

		// solve sudoku
		if (solveSudoku(graph)) {
			// print sudoku solution
			LOG(true, "Solution");
			printSudoku(graph);
		} else {
			LOG(true, "No Solution!");
		}

		return sManager.convert(graph);
	}

	private boolean solveSudoku(SudokuGraph graph) {
		PriorityQueue<SudokuGraphNode> priorityQueue = graph.getPriorityQueue();
		if (!priorityQueue.isEmpty()) {
			SudokuGraphNode node = priorityQueue.poll();
			for (int i = 0; i < node.getPossibleNumbers().size(); i++) {
				int number = node.getPossibleNumbers().get(i);
				List<SudokuGraphNode> changedNodes = sManager.setNumberAndUpdateOpposites(node, number);
				if (solveSudoku(graph)) {
					return true;
				} else {
					// reset
					sManager.resetChanges(node, changedNodes, number);
				}
			}
			return false;
		}
		return true;
	}

	private static void LOG(boolean newLine, Object obj) {
		System.out.print(obj + (newLine ? "\n" : ""));
	}

	public String sudokuToString(int[][] sudoku) {
		String str = "";
		int squareSize = (int) Math.sqrt(sudoku.length);
		for (int y = 0; y < sudoku.length; y++) {
			for (int x = 0; x < sudoku.length; x++) {
				int number = sudoku[y][x];
				str += number + " ";
				if (x % squareSize == squareSize - 1) {
					str += " ";
				}
				if ((x % sudoku.length == sudoku.length - 1 && y % squareSize == squareSize - 1)) {
					str += "\n";
				}
			}
			str += "\n";
		}
		return str;
	}

	private void printSudoku(SudokuGraph graph) {
		List<SudokuGraphNode> nodes = graph.getNodes();
		int sudokuSize = (int) Math.sqrt(nodes.size());
		int squareSize = (int) Math.sqrt(sudokuSize);
		for (int i = 0; i < nodes.size(); i++) {
			SudokuGraphNode node = nodes.get(i);
			LOG(false, ansi().fg(node.isGiven() ? GREEN : node.getNumber() == 0 ? RED : BLUE).a(node.getNumber() + " ").reset());
			if (i % squareSize == squareSize - 1) {
				LOG(false, " ");
			}
			if (i % (squareSize * sudokuSize) == squareSize * sudokuSize - 1) {
				LOG(true, "");
			}
			if (i % sudokuSize == sudokuSize - 1) {
				LOG(true, "");
			}
		}
	}

}
