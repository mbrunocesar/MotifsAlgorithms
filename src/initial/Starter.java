package initial;

import data.GenericNode;
import data.graph.GraphBuilder;
import data.motif.Motif;
import data.tree.TreeBuilder;
import heuristics.MaxMotifSearch;

public class Starter {

	// Run Test
	public static void main(String[] args) {
		new Starter();
	}
	
	public Starter() {
		// TEST MOTIF - First sub-Motif is maximum
		int[] motifData = {1, 3, 4, 6, 9, 10, 11, 12};

		// TEST MOTIF - both sub-Motif are maximum - get first
		// int[] motifData = {1, 3, 4, 6, 10, 11, 12};

		// TEST MOTIF - last sub-Motif is maximum
		// int[] motifData = {1, 3, 4, 6, 11, 12};

		GraphBuilder builder = rebuildGraph();
		
		// Starter
		GenericNode targetGraph = null;
		Motif targetMotif = new Motif();

		targetMotif.initMotif(motifData);
		
		System.out.println(builder.getNodeSet().length);
		targetMotif.generateAppliedMotif(builder.getNodeSet());
		
		MaxMotifSearch motifSearch;
		
		// Set test
		motifSearch = new MaxMotifSearch();

		// First test
		builder = rebuildGraph();
		targetGraph = builder.getStartNode();
		motifSearch.searchForMaxMotif(targetMotif, targetGraph, 0);
		motifSearch.printSubMotifs();
		motifSearch.printMaxSubMotif();
		motifSearch.printVisits();
		
		// First test
		builder = rebuildGraph();
		targetGraph = builder.getStartNode();
		motifSearch.searchForMaxMotif(targetMotif, targetGraph, 1);
		motifSearch.printSubMotifs();
		motifSearch.printMaxSubMotif();
		motifSearch.printVisits();
		
	}
	
	public TreeBuilder rebuildTree() {
		// TEST Tree
		int[] nodeColors = {0, 1, 2, 3, 4, 1, 6, 7, 8, 9, 10, 11, 12, 
								9, 1, 3, 3, 1, 1, 4, 6, 4, 5};
		
		TreeBuilder builder = new TreeBuilder(nodeColors);
		builder.addConnections(0, 1);
		builder.addConnections(0, 2);

		builder.addConnections(1, 5);

		builder.addConnections(5, 8);
		builder.addConnections(5, 7);

		builder.addConnections(2, 3);
		builder.addConnections(2, 4);

		builder.addConnections(3, 6);

		builder.addConnections(6, 9);
		builder.addConnections(6, 10);

		builder.addConnections(4, 11);
		builder.addConnections(4, 12);
		
		builder.addConnections(7, 13);
		
		builder.addConnections(13, 14);

		builder.addConnections(14, 15);
		builder.addConnections(14, 16);

		builder.addConnections(15, 17);

		builder.addConnections(16, 20);
		builder.addConnections(16, 18);
		
		builder.addConnections(17, 19);

		builder.addConnections(18, 21);
		builder.addConnections(18, 22);
		
		return builder;
	}
	
	public GraphBuilder rebuildGraph() {
		// TEST GRAPH - Still being a Tree
		int[] nodeColors = {0, 1, 2, 3, 4, 1, 6, 7, 8, 9, 10, 11, 12, 
								9, 1, 3, 3, 1, 1, 4, 6, 4, 5};
		
		GraphBuilder builder = new GraphBuilder(nodeColors, false);
		builder.addConnections(0, 1);
		builder.addConnections(0, 2);

		builder.addConnections(1, 5);

		builder.addConnections(5, 8);
		builder.addConnections(5, 7);

		builder.addConnections(2, 3);
		builder.addConnections(2, 4);

		builder.addConnections(3, 6);

		builder.addConnections(6, 9);
		builder.addConnections(6, 10);

		builder.addConnections(4, 11);
		builder.addConnections(4, 12);
		
		builder.addConnections(7, 13);
		
		builder.addConnections(13, 14);

		builder.addConnections(14, 15);
		builder.addConnections(14, 16);

		builder.addConnections(15, 17);

		builder.addConnections(16, 20);
		builder.addConnections(16, 18);
		
		builder.addConnections(17, 19);

		builder.addConnections(18, 21);
		builder.addConnections(18, 22);
		
		return builder;
	}
	
}
