package data.network;

import java.util.LinkedList;
import java.util.List;

import data.GenericEdge;

public class Flow {
	
	LinkedList<GenericEdge>[] adj;
	int[] flow;

	@SuppressWarnings("unchecked")
	public Flow(int numVertex, int numEdges) {
		adj = new LinkedList[numVertex];
		for (int i=0; i<numVertex; i++) {
			adj[i] = new LinkedList<GenericEdge>();
		}
		flow = new int[2*numEdges];
		GenericEdge.clearEdges();
	}
	
	public void addVertex(int vertex) {
		adj[vertex] = new LinkedList<GenericEdge>();
	}
	
	public List<GenericEdge> getEdges(int index) {
		return adj[index];
	}
	
	public void addEdge(int from, int to, int capacity) {
		GenericEdge edge = new GenericEdge(from, to, capacity);
		GenericEdge redge = new GenericEdge(to, from, 0);
		
		edge.addReturnEdge(redge);
		redge.addReturnEdge(edge);
		
		adj[from].add(edge);
		adj[to].add(redge);

		flow[edge.getId()] = 0;
		flow[redge.getId()] = 0;
	}
	
	public List<GenericEdge> findPath(int from, int to, List<GenericEdge> path) {
		List<GenericEdge> result = null;
		
		if (from == to) {
			result = path;
		} else {
			List<GenericEdge> sourceEdges = getEdges(from);
			for (GenericEdge edge : sourceEdges) {
				int residual = edge.getCapacity() - flow[edge.getId()];

				if (residual > 0 && !path.contains(edge)) {
					List<GenericEdge> newPath = new LinkedList<GenericEdge>();
					for (GenericEdge copy : path) {
						newPath.add(copy);
					}
					newPath.add(edge);
					List<GenericEdge> pathAhead = findPath(edge.getTo(), to, newPath);
					if (isValidPath(pathAhead)) {
						result = pathAhead;
					}
				}
			}
			
		}
		
		return result;
	}
	
	public int maxFlow(int from, int to) {
		List<GenericEdge> path = findPath(from, to, newPath());
		
		while (isValidPath(path)) {
			printPath(path);
			int flowSize = 1;
			
			for (GenericEdge edge : path) {
				flow[edge.getId()] += flowSize;
				flow[edge.getReturnEdge().getId()] -= flowSize;
			}
			path = findPath(from, to, newPath());
		}
		
		int maxFlow = 0;
		List<GenericEdge> fluxFrom = getEdges(from);
		
		printPath(fluxFrom);
		for (GenericEdge edge : fluxFrom) {
			//System.out.println(edge.getId() + " - " +flow[edge.getId()]);
			maxFlow += flow[edge.getId()];
		}
		
		return maxFlow;
	}
	
	private LinkedList<GenericEdge> newPath(){
		return new LinkedList<GenericEdge>();
	}
	
	private void printPath(List<GenericEdge> path) {
		for (GenericEdge edge : path) {
			System.out.print(edge+" ");
		}
		System.out.println();
	}
	
	private boolean isValidPath(List<GenericEdge> path) {
		return path != null && path.size() > 0;
	}
	

}
