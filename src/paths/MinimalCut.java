package paths;

import java.util.*;

import data.graph.*;

public class MinimalCut {

	public GraphNode[] betweenTwoNodesByAllPaths(AllPathsCreator allPaths, int idNodeInitial, int idNodeFinal) {
		GraphNode[] result = new GraphNode[0];

		LinkedList<GraphNode> minCut = new LinkedList<GraphNode>();

		System.out.println(allPaths.pathBetweenTwoNodes(idNodeInitial, idNodeFinal));

		if (allPaths.isNotAdjacent(idNodeInitial, idNodeFinal)) {
			int numNodes = allPaths.returnNumNodes();

			for (int i = 0; i < numNodes; i++) {
				List<Integer> listI = allPaths.pathBetweenTwoNodes(idNodeInitial, i);
				List<Integer> listF = allPaths.pathBetweenTwoNodes(idNodeFinal, i);

				if (listDoNotContains(listI, idNodeFinal) && 
						listDoNotContains(listF, idNodeInitial)) {

					boolean shouldIgnore = false;
					for (GraphNode ignoredNodes : minCut) {
						if (listContains(listI, ignoredNodes.getIndex()) ||
								listContains(listF, ignoredNodes.getIndex())) {
							shouldIgnore = true;
							break;
						}
					}

					if (!shouldIgnore) {
						minCut.add(new GraphNode(i, i));
					}
				}

			}


			LinkedList<GraphNode> realMinCut = new LinkedList<GraphNode>();
			// Re-filter then
			if (minCut.size() > 1) {
				for (GraphNode cutNodes : minCut) {
					List<Integer> listI = allPaths.pathBetweenTwoNodes(idNodeInitial, cutNodes.getIndex());
					List<Integer> listF = allPaths.pathBetweenTwoNodes(idNodeFinal, cutNodes.getIndex());


					boolean shouldIgnore = false;
					for (GraphNode ignoredNodes : minCut) {
						if (cutNodes.getIndex() != ignoredNodes.getIndex()) {
							if (listContains(listI, ignoredNodes.getIndex()) ||
									listContains(listF, ignoredNodes.getIndex())) {
								shouldIgnore = true;
								break;
							}
						}
					}

					if (!shouldIgnore) {
						realMinCut.add(cutNodes);
					}
				}
				minCut = realMinCut;
			} 


			int numSelectedNodes = minCut.size();

			result = new GraphNode[numSelectedNodes];
			for (int i = 0; i < numSelectedNodes; i++) {
				result[i] = minCut.remove();
			}
		}

		return result;
	}

	private boolean listDoNotContains(List<Integer> list, int idNode) {
		return list != null && !list.contains(new Integer(idNode));
	}

	private boolean listContains(List<Integer> list, int idNode) {
		return list != null && list.contains(new Integer(idNode));
	}

}