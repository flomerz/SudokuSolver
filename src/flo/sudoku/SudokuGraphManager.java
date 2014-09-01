package flo.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class SudokuGraphManager {

	private SudokuGraphProvider provider = new SudokuGraphProvider();

	public SudokuGraph convert(int[][] sudoku) {
		SudokuGraph graph = provider.getSudokuGraph(sudoku.length);
		setSudokuExerciseNumbers(graph, sudoku);
		return graph;
	}

	public int[][] convert(SudokuGraph graph) {
		int sudokuSize = graph.getSudokuSize();
		int[][] sudoku = new int[sudokuSize][sudokuSize];
		for (int y = 0; y < sudoku.length; y++) {
			for (int x = 0; x < sudoku.length; x++) {
				sudoku[y][x] = graph.getNodes().get(y * sudoku.length + x).getNumber();
			}
		}
		return sudoku;
	}

	public List<SudokuGraphNode> setNumberAndUpdateOpposites(SudokuGraphNode node, int number) {
		List<SudokuGraphNode> changedNodes = new ArrayList<>();
		PriorityQueue<SudokuGraphNode> prioritiyQueue = node.getGraph().getPriorityQueue();

		node.setNumber(number);
		for (SudokuGraphNode oppositeNode : node.getOppositeNodes()) {
			if (oppositeNode.getPossibleNumbers().remove(Integer.valueOf(number))) {
				if (oppositeNode.getNumber() == 0 && prioritiyQueue != null) {
					prioritiyQueue.remove(oppositeNode);
					prioritiyQueue.add(oppositeNode);
				}
				changedNodes.add(oppositeNode);
			}
		}

		return changedNodes;
	}

	public void resetChanges(SudokuGraphNode node, List<SudokuGraphNode> changedNodes, int number) {
		for (SudokuGraphNode cn : changedNodes) {
			cn.getPossibleNumbers().add(number);
		}
		resetPriorityQueue(node.getGraph());
		node.setNumber(0);
	}

	private void resetPriorityQueue(SudokuGraph graph) {
		PriorityQueue<SudokuGraphNode> priorityQueue = new PriorityQueue<>();
		for (SudokuGraphNode n : graph.getNodes()) {
			if (n.getNumber() == 0) {
				priorityQueue.add(n);
			}
		}
		graph.setPriorityQueue(priorityQueue);
	}

	private void setSudokuExerciseNumbers(SudokuGraph graph, int[][] sudoku) {
		for (int y = 0; y < sudoku.length; y++) {
			for (int x = 0; x < sudoku.length; x++) {
				int number = sudoku[y][x];
				if (number != 0) {
					SudokuGraphNode node = graph.getNodes().get(y * sudoku.length + x);
					setNumberAndUpdateOpposites(node, number);
					node.setGiven(true);
				}
			}
		}
		resetPriorityQueue(graph);
	}

}
