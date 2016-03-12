package data.tree;

import data.GenericNode;

public class TreeNode extends GenericNode {

	TreeNode parent;
	
	public TreeNode(int newIndex, int newValue) {
		super(newIndex, newValue);
	}

	public TreeNode getParent() {
		return parent;
	}
	
	public void addConnection(TreeNode node) {
		connections.add(node);
		node.addParentConnection(this);	
	}
	
	public void addParentConnection(TreeNode node) {
		connections.add(node);
		parent = node;
	}
	
	public void removeConnection(TreeNode node) {
		connections.remove(node);
		node.removeParentConnection(this);
	}

	public void removeParentConnection(TreeNode node) {
		connections.remove(node);
	}
	
	public void printConnectedNodes() {
		for (GenericNode connectedNode : connections) {
			System.out.print(connectedNode.getValue()+ "  ");
		}
		System.out.println();
	}
	

}
