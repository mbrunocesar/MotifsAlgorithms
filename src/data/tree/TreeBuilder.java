package data.tree;

import data.NodeBuilder;

public class TreeBuilder extends NodeBuilder {

	public TreeBuilder(int[] graphColors) {
		graphBuilderInit(graphColors);
	}

	private void graphBuilderInit(int[] graphColors) {
		graphSet = new TreeNode[graphColors.length];

		for (int i=0; i<graphColors.length; i++) {
			graphSet[i] = new TreeNode(i, graphColors[i]);
		}
	}

	/*
	 * @override NodeBuilder
	 */
	public void addConnections(int initialNode, int finalNode) {
		TreeNode fromNode = (TreeNode) graphSet[initialNode];
		TreeNode toNode = (TreeNode) graphSet[finalNode];
		fromNode.addConnection(toNode);
	}

}
