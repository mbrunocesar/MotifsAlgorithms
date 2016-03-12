package data.graph;

import java.util.List;

public class TreeLikeSubGraph {

	GraphNode parent;
	GraphNode topGraph;
	
	boolean[] visitedColor;
	List<Integer> visitedColorList;
	
	public TreeLikeSubGraph(GraphNode theTopGraph, GraphNode theParent) {
		topGraph = theTopGraph;
		parent = theParent;
		
		visitColor(topGraph.getValue());
	}
	
	public void visitColor(int colorId) {
		visitedColor[colorId] = true;
		visitedColorList.add(colorId);
	}
	
	public boolean hasVisitedColor(int colorId) {
		return visitedColor[colorId];
	}
	
	/**
	 * if return = 1 - Kill any side of the structure
	 * 
	 * @param comparedVisitedColorList
	 * @return
	 */
	public int compareStructures(List<Integer> comparedVisitedColorList) {
		int returned = 4;
		
		int comparisonCounter = 0;
		for (Integer visited : comparedVisitedColorList) {
			if (visitedColor[visited]) {
				comparisonCounter++;
			}
		}
		
		if (comparedVisitedColorList.size() == visitedColorList.size()) {
			if (comparedVisitedColorList.size() == comparisonCounter) {
				returned = 1; 
			} else {
				returned = 5;
			}
		} else {
			if (comparedVisitedColorList.size() == comparisonCounter) {
				returned = 3;
			} else if (visitedColorList.size() == comparisonCounter) {
				returned = 2;
			}
		}
		
		return returned;
	}
	
	
}
