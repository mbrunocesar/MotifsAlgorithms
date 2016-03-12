package data;

import java.util.LinkedList;
import java.util.List;

public abstract class GenericNode {

	protected int nodeIndex;
	protected int nodeValue;
	
	protected List<GenericNode> connections;
	
	public GenericNode(int newIndex, int newValue) {
		nodeIndex = newIndex;
		nodeValue = newValue;
		connections = new LinkedList<GenericNode>();
	}
	
	public int getIndex() {
		return nodeIndex;
	}
	
	public int getValue() {
		return nodeValue;
	}
	
	public List<GenericNode> getConnections() {
		return connections;
	}
	
	public void addConnection(GenericNode node) {}
	
	public void removeConnection(GenericNode node) {}
	
	public void printConnectedNodes() {
		System.out.println();
	}
	

}
