package flo.sudoku;

import java.util.List;
import java.util.PriorityQueue;

class SudokuGraph {
	private int sudokuSize;
	private List<SudokuGraphNode> nodes;
	private PriorityQueue<SudokuGraphNode> priorityQueue;

	public SudokuGraph(int sudokuSize) {
		this.sudokuSize = sudokuSize;
	}

	public int getSudokuSize() {
		return sudokuSize;
	}

	public void setSudokuSize(int sudokuSize) {
		this.sudokuSize = sudokuSize;
	}

	public List<SudokuGraphNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<SudokuGraphNode> nodes) {
		this.nodes = nodes;
	}

	public PriorityQueue<SudokuGraphNode> getPriorityQueue() {
		return priorityQueue;
	}

	public void setPriorityQueue(PriorityQueue<SudokuGraphNode> prioNodes) {
		this.priorityQueue = prioNodes;
	}
}
