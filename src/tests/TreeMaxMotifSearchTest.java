package tests;

import static org.junit.Assert.*;
import heuristics.MaxMotifSearch;

import org.junit.Before;
import org.junit.Test;

import data.GenericNode;
import data.motif.Motif;
import data.motif.SubMotif;
import data.motif.SubMotifSet;
import data.tree.TreeBuilder;
import data.tree.TreeNode;

public class TreeMaxMotifSearchTest {

	GenericNode targetGraph;
	Motif targetMotif;

	MaxMotifSearch maxMotifSearch;
	
	private final int MAX_SUBNODES = 100;
	
	@Before
	public void setUp() throws Exception {
		// Constructors
		targetGraph = new TreeNode(-1, -1);
		targetMotif = new Motif();

		maxMotifSearch = new MaxMotifSearch();
	}

	@Test
	public void exampleTreeTest() {
		// BUILD TEST GRAPH
		int[] graphColors = {0, 1, 2, 3, 4, 1, 6, 7, 8, 9, 10, 11, 12};
		TreeBuilder builder = new TreeBuilder(graphColors);
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

		// TEST MOTIF - First sub-Motif is maximum
		int[] motifData = {1, 3, 4, 6, 9, 10, 11, 12};

		// TEST MOTIF - both sub-Motif are maximum - get first
		// int[] motifData = {1, 3, 4, 6, 10, 11, 12};

		// TEST MOTIF - last sub-Motif is maximum
		// int[] motifData = {1, 3, 4, 6, 11, 12};


		// Starter
		long searchTime = basicTestSteps(builder, motifData);
		System.out.println("Exemplar Graph Complexity "+searchTime);
		
		assertTrue(searchTime < 50);
	}

	@Test
	public void bestTimeComplexityTest() {
		
		int[] graphColors = new int[13*MAX_SUBNODES];
		for (int i=0; i<graphColors.length; i++) {
			graphColors[i] = i;
		}
		
		TreeBuilder builder = new TreeBuilder(graphColors);
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
		
		for (int i = 8; i < 13; i++) {
			for (int j = 0; j < MAX_SUBNODES; j++) {
				if (j<Math.ceil(Math.sqrt(MAX_SUBNODES))) {
					builder.addConnections(i, (i*MAX_SUBNODES)+ j);
				} else {
					builder.addConnections((i*MAX_SUBNODES) + (j%4), (i*MAX_SUBNODES)+j);
				}
			}
		}

		// Test to see the connection
		// builder.getGraphFromIndex(8).printConnectedNodes();
		// builder.getGraphFromIndex(9).printConnectedNodes();
		// builder.getGraphFromIndex(10).printConnectedNodes();
		// builder.getGraphFromIndex(11).printConnectedNodes();
		
		// TEST MOTIF
		int[] motifData = {1, 3, 4, 6, 9, 10, 11, 12};

		// Starter
		long searchTime = basicTestSteps(builder, motifData);
		System.out.println("Best Case Complexity "+searchTime);

		assertEquals(516, maxMotifSearch.getVisits()[0]);
		assertEquals(1024, maxMotifSearch.getVisits()[1]);
	}

	@Test
	public void worstTimeComplexityTest() {
		
		int[] graphColors = new int[5*MAX_SUBNODES];
		for (int i=0; i<graphColors.length; i++) {
			graphColors[i] = i;
		}
		TreeBuilder builder = new TreeBuilder(graphColors);
		
		builder.addConnections(0, 14);
		
		for (int i = 1; i < 5; i++) {
			builder.addConnections(14, i*MAX_SUBNODES);
			for (int j = 1; j < MAX_SUBNODES; j++) {
				if (j<Math.ceil(Math.sqrt(MAX_SUBNODES))) {
					builder.addConnections(i*MAX_SUBNODES, (i*MAX_SUBNODES)+ j);
				} else {
					builder.addConnections((i*MAX_SUBNODES + (j%4)), (i*MAX_SUBNODES)+(j));
				}
			}
		}
		
		builder.addConnections(196, 13);
		
		builder.addConnections(13, 1);
		builder.addConnections(13, 2);
		
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

		// Test to see the connection
		// builder.getGraphFromIndex(14).printConnectedNodes();
		// builder.getGraphFromIndex(160).printConnectedNodes();
		// builder.getGraphFromIndex(196).printConnectedNodes();
		// builder.getGraphFromIndex(13).printConnectedNodes();
		// builder.getGraphFromIndex(2).printConnectedNodes();
				
		// TEST MOTIF
		int[] motifData = {1, 3, 4, 6, 9, 10, 11, 12};

		// Starter
		long searchTime = basicTestSteps(builder, motifData);
		System.out.println("Worst Case Complexity "+searchTime);

		assertEquals(418, maxMotifSearch.getVisits()[0]);
		assertEquals(828, maxMotifSearch.getVisits()[1]);
	}
	
	public long basicTestSteps(TreeBuilder builder, int[] motifData) {
		// Starter
		targetGraph = builder.getStartNode();

		targetMotif.initMotif(motifData);
		targetMotif.generateAppliedMotif(builder.getNodeSet());
		
		int numRuns = 100;
		SubMotifSet subMotifSet = new SubMotifSet(0);
		
		// First Run
		long startTime = System.currentTimeMillis();
		for (int i=0; i<numRuns; i++) {
			subMotifSet = maxMotifSearch.searchForMaxMotif(targetMotif, targetGraph);
		}
		long endTime = System.currentTimeMillis();

		// First Assertions
		
		SubMotif maxSubMotif = subMotifSet.getMaxMotif();
		subMotifSet.printBiggerSubMotif();
		
		Integer[] expectedSubMotif = {3, 6, 9, 10};

		assertEquals(expectedSubMotif.length, maxSubMotif.size());

		for (int i=0; i < expectedSubMotif.length; i++) {
			assertEquals(expectedSubMotif[i], maxSubMotif.get(i));
		}
		
		long totalTime = endTime - startTime;
		return totalTime;
	}

}
