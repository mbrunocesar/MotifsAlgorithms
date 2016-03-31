package data.graph;

import data.NodeBuilder;

public class GraphBuilder extends NodeBuilder {

	boolean directedGraph;

	public GraphBuilder(int[] colorSet) {
		graphBuilderInit(colorSet);
		directedGraph = false;
	}

	public GraphBuilder(int[] colorSet, boolean directed) {
		graphBuilderInit(colorSet);
		directedGraph = directed;
	}

	private void graphBuilderInit(int[] colorSet) {
		graphSet = new GraphNode[colorSet.length];

		for (int colorIndex = 0; colorIndex < colorSet.length; colorIndex++) {
			int color = colorSet[colorIndex];
			graphSet[colorIndex] = new GraphNode(colorIndex, color);
			addColor(color);
		}
	}

	/*
	 * @override NodeBuilder
	 */
	public void addConnections(int initialNode, int finalNode) {
		GraphNode fromNode = (GraphNode) graphSet[initialNode];
		GraphNode toNode = (GraphNode) graphSet[finalNode];
		if (directedGraph) {
			fromNode.addDirectionalConnection(toNode);
		} else {
			fromNode.addConnection(toNode);
		}
	}

}
