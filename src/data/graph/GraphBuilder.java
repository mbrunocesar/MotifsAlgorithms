package data.graph;

import data.NodeBuilder;

public class GraphBuilder extends NodeBuilder {

	boolean directedGraph;

	public GraphBuilder(int[] graphColors) {
		graphBuilderInit(graphColors);
		directedGraph = false;
	}

	public GraphBuilder(int[] graphColors, boolean directed) {
		graphBuilderInit(graphColors);
		directedGraph = directed;
	}

	private void graphBuilderInit(int[] graphColors) {
		graphSet = new GraphNode[graphColors.length];

		for (int i=0; i<graphColors.length; i++) {
			graphSet[i] = new GraphNode(i, graphColors[i]);
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
