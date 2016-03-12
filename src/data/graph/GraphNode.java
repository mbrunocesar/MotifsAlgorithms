package data.graph;

import data.GenericNode;

public class GraphNode extends GenericNode {
	
	public GraphNode(int newIndex, int newValue) {
		super(newIndex, newValue);
	}
	
	public void addConnection(GraphNode node) {
		connections.add(node);
		node.addDirectionalConnection(this);
	}
	
	public void addDirectionalConnection(GraphNode node) {
		connections.add(node);
	}

	public void removeConnection(GraphNode node) {
		connections.remove(node);
		node.removeDirectionalConnection(this);
	}

	public void removeDirectionalConnection(GraphNode node) {
		connections.remove(node);
	}
	
	public void printConnectedNodes() {
		for (GenericNode connectedNode : connections) {
			System.out.print(connectedNode.getValue()+ "  ");
		}
		System.out.println();
	}
	

}
