package flo.sudoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class SudokuGraphNode implements Comparable<SudokuGraphNode> {
	private SudokuGraph graph;
	private int fieldNum;
	private int number = 0;
	private boolean given = false;
	private List<Integer> possibleNumbers = new ArrayList<>();
	private Set<SudokuGraphNode> oppositeNodes = new HashSet<>();

	public SudokuGraphNode(SudokuGraph graph, int fieldNum, List<Integer> possibleNumbers) {
		this.graph = graph;
		this.fieldNum = fieldNum;
		this.possibleNumbers.addAll(possibleNumbers);
	}

	@Override
	public int compareTo(SudokuGraphNode o) {
		return possibleNumbers.size() - o.possibleNumbers.size();
	}

	@Override
	public String toString() {
		String numberStr = "number: " + number + "\n";

		String possibleNumbersStr = "possible numbers: ";
		for (Integer pn : possibleNumbers) {
			possibleNumbersStr += pn + " ";
		}
		possibleNumbersStr += "\n";

		String dependentNodesStr = "dependent nodes: ";
		for (SudokuGraphNode oppositeNode : oppositeNodes) {
			dependentNodesStr += oppositeNode.fieldNum + " ";
		}
		dependentNodesStr += "\n";

		return "\n" + fieldNum + ":\n" + numberStr + possibleNumbersStr + dependentNodesStr;
	}

	public SudokuGraph getGraph() {
		return graph;
	}

	public void setGraph(SudokuGraph graph) {
		this.graph = graph;
	}

	public int getFieldNum() {
		return fieldNum;
	}

	public void setFieldNum(int fieldNum) {
		this.fieldNum = fieldNum;
	}

	public boolean isGiven() {
		return given;
	}

	public void setGiven(boolean given) {
		this.given = given;
	}

	public List<Integer> getPossibleNumbers() {
		return possibleNumbers;
	}

	public void setPossibleNumbers(List<Integer> possibleNumbers) {
		this.possibleNumbers = possibleNumbers;
	}

	public Set<SudokuGraphNode> getOppositeNodes() {
		return oppositeNodes;
	}

	public void setOppositeNodes(Set<SudokuGraphNode> oppositeNodes) {
		this.oppositeNodes = oppositeNodes;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
