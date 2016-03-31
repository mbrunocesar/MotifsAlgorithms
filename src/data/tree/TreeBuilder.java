package data.tree;

import data.NodeBuilder;

public class TreeBuilder extends NodeBuilder {

	public TreeBuilder(int[] colorSet) {
		graphBuilderInit(colorSet);
	}

	private void graphBuilderInit(int[] colorSet) {
		graphSet = new TreeNode[colorSet.length];

		for (int colorIndex = 0; colorIndex < colorSet.length; colorIndex++) {
			int color = colorSet[colorIndex];
			graphSet[colorIndex] = new TreeNode(colorIndex, color);
			addColor(color);
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
