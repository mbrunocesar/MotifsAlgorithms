package data;

public abstract class NodeBuilder  {

	protected GenericNode[] graphSet;

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
