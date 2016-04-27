package data;

import java.util.LinkedList;
import java.util.List;

public abstract class NodeBuilder  {

	protected GenericNode[] graphSet;
	protected List<Integer> colorList = new LinkedList<Integer>();

	public void addConnections(int initialNode, int finalNode) {
		graphSet[initialNode].addConnection(graphSet[finalNode]);
	}

	public GenericNode getNodeFromIndex(int index) {
		return graphSet[index];
	}

	public GenericNode[] getNodeSet() {
		return graphSet;
	}

	public int getNodeSetSize() {
		return graphSet.length;
	}

	// Suppose a connected graph 
	public GenericNode getStartNode() {
		return graphSet[0];
	}


	public void addColor(int colorNumber) {
		if (!colorList.contains(colorNumber)) {
			colorList.add(colorNumber);
		}
	}
	
	public int getNumColors() {
		return colorList.size();
	}
	
	public List<Integer> getUsedColors() {
		return colorList;
	}
 

	public void printBuiltedStructure() {
		for (GenericNode node : graphSet) {
			System.out.println("["+node.getIndex()+" "+node.getValue()+"]");
			
			System.out.print("{");
			for (GenericNode subNodes : node.getConnections()) {
				System.out.print("["+subNodes.getIndex()+" "+subNodes.getValue()+"]");
			}
			System.out.println("}");
		}
	}
	
}
