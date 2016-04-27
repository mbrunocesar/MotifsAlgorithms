package data.graph;

import java.util.LinkedList;
import java.util.List;

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
	
	static int stop;
	public GraphNode findNodeById(List<GraphNode> visitedNodes, int targetIndex) {
		if (visitedNodes == null) {
			GraphNode.stop = 0;
			visitedNodes = new LinkedList<GraphNode>();
		}
		
		if (this.nodeIndex == targetIndex) {
			return this;
		}
		GraphNode.stop++;
		visitedNodes.add(this);
		for (GenericNode neighboursConnections : connections) {
			if (!visitedNodes.contains(neighboursConnections)) {
				return ((GraphNode) neighboursConnections).findNodeById(visitedNodes, targetIndex);
			}
		}
		
		if (GraphNode.stop > 15) {
			System.out.println("Not stoping");
			System.exit(1);
		}
		
		return null;
	}
	
	public void printConnectedNodes() {
		for (GenericNode connectedNode : connections) {
			System.out.print(connectedNode.getValue()+ "  ");
		}
		System.out.println();
	}
	

}
