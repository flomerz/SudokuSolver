package flo.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SudokuGraphProvider {

	public SudokuGraph getSudokuGraph(int sudokuSize) {
		SudokuGraph graph = new SudokuGraph(sudokuSize);
		createNodes(graph);
		createEdges(graph);
		return graph;
	}

	private List<SudokuGraphNode> createNodes(SudokuGraph graph) {
		List<SudokuGraphNode> nodes = new ArrayList<>();
		int sudokuSize = graph.getSudokuSize();
		Integer[] possibleNumbers = new Integer[sudokuSize];
		for (int i = 0; i < sudokuSize; i++) {
			possibleNumbers[i] = i + 1;
		}
		for (int fieldNum = 0; fieldNum < sudokuSize * sudokuSize; fieldNum++) {
			nodes.add(new SudokuGraphNode(graph, fieldNum, Arrays.asList(possibleNumbers.clone())));
		}
		graph.setNodes(nodes);
		return nodes;
	}

	private void createEdges(SudokuGraph graph) {
		int sudokuSize = graph.getSudokuSize();
		List<SudokuGraphNode> nodes = graph.getNodes();
		// horizontal edges
		for (int row = 0; row < sudokuSize; row++) {
			int rowOffset = row * sudokuSize;
			for (int i = rowOffset; i < sudokuSize + rowOffset; i++) {
				for (int ii = i + 1; ii < sudokuSize + rowOffset; ii++) {
					createEdge(nodes.get(i), nodes.get(ii));
				}
			}
		}
		// vertical edges
		for (int colum = 0; colum < sudokuSize; colum++) {
			int columOffset = colum;
			for (int i = columOffset; i < nodes.size(); i += sudokuSize) {
				for (int ii = i + sudokuSize; ii < nodes.size(); ii += sudokuSize) {
					createEdge(nodes.get(i), nodes.get(ii));
				}
			}
		}
		// square edges
		int squareWidth = (int) Math.sqrt(sudokuSize);
		int squareHeight = (int) Math.pow(squareWidth, 3);
		for (int squareColum = 0; squareColum < sudokuSize; squareColum += squareWidth) {
			for (int square = squareColum; square < nodes.size(); square += squareHeight) {
				List<SudokuGraphNode> subSquareSudokuGraphNodes = new ArrayList<>();
				for (int subSquareColum = square; subSquareColum < square + squareWidth; subSquareColum++) {
					for (int field = subSquareColum; field < subSquareColum + squareHeight; field += sudokuSize) {
						subSquareSudokuGraphNodes.add(nodes.get(field));
					}
				}
				for (SudokuGraphNode i : subSquareSudokuGraphNodes) {
					for (SudokuGraphNode ii : subSquareSudokuGraphNodes) {
						if (!i.equals(ii) && !i.getOppositeNodes().contains(ii)) {
							createEdge(i, ii);
						}
					}
				}
			}
		}
	}

	private void createEdge(SudokuGraphNode a, SudokuGraphNode b) {
		a.getOppositeNodes().add(b);
		b.getOppositeNodes().add(a);
	}

}
