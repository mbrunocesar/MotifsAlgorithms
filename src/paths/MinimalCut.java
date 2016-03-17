package paths;

import java.util.*;

import data.graph.*;

public class MinimalCut {

	public GraphNode[] betweenTwoGraphsByAllPaths(AllPathsCreator allPaths, int idNodeInitial, int idNodeFinal) {
		GraphNode[] result = null;

		LinkedList<GraphNode> minCut = new LinkedList<GraphNode>();

		int numNodes = allPaths.returnNumNodes();
		for (int i = 0; i < numNodes; i++) {
			List<Integer> listI = allPaths.pathBetweenTwoNodes(idNodeInitial, i);
			List<Integer> listF = allPaths.pathBetweenTwoNodes(idNodeFinal, i);
			
			if (listI != null && !listI.contains(new Integer(idNodeFinal)) && 
				listF != null && !listF.contains(new Integer(idNodeInitial))) {
				minCut.add(new GraphNode(i, i)); 
			}

		}

		int numSelectedNodes = minCut.size();

		result = new GraphNode[numSelectedNodes];
		for (int i = 0; i < numSelectedNodes; i++) {
			result[i] = minCut.remove();
		}
		
		return result;
	}

}