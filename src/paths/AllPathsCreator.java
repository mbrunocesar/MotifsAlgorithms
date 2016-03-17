package paths;

import helpers.visits.VisitableController;

import java.util.LinkedList;
import java.util.List;

import data.GenericNode;
import data.NodeBuilder;

public class AllPathsCreator extends VisitableController {

	SimplePath[] paths;
	int[][] dist;
	GenericNode[][] next;


	public AllPathsCreator() {
		visitedNodes = 0;
		visitedLinks = 0;
	}

	public void calculateDistances(NodeBuilder targetGraph, int graphSize) {
		paths = new SimplePath[graphSize];

		dist = new int[graphSize][graphSize];
		next = new GenericNode[graphSize][graphSize];

		for (int[] line : dist) {
			for (int i = 0; i < line.length; i++) {
				line[i] = 9999;
			}
		}

		for (int i = 0; i<graphSize; i++) {
			dist[i][i] = 0;
		}

		GenericNode[] graph = targetGraph.getNodeSet();

		int u, v;
		for (GenericNode node : graph) {
			visitedNodes++;
			u = node.getIndex();

			List<GenericNode> connections = node.getConnections();
			for (GenericNode pointer : connections) {
				visitedLinks++;
				visitedNodes++;
				v = pointer.getIndex();
				dist[u][v] = 1;
				next[u][v] = pointer;
			}
		}

		for (int k=0; k<graphSize; k++) {
			for (int i=0; i<graphSize; i++) {
				for (int j=0; j<graphSize; j++) {

					if (dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
						next[i][j] = next[i][k];
					}

				}
			}
		}
	}

	public List<Integer> pathBetweenTwoNodes(int initialId, int finalId) {
		List<Integer> path = null;
		int u = initialId;
		int v = finalId;

		if (next[u][v] != null) {
			visitedNodes++;
			path = new LinkedList<Integer>();
			path.add(u);
			while (u != v) {
				visitedNodes++;
				u = next[u][v].getIndex();
				path.add(u);
			}
		}

		return path;
	}

	public int[][] getDistancesMatrix() {
		return dist; 
	}

	public int returnNumNodes() {
		return paths.length;
	}

}
