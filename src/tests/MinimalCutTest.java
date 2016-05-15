package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import data.graph.GraphNode;
import data.tree.TreeBuilder;
import paths.*;

public class MinimalCutTest {

	AllPathsCreator allPaths;
	
	@Before
	public void setUp() throws Exception {
		allPaths = new AllPathsCreator();
	}
	

	public void test1() {
		int[] nodeColors = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

		TreeBuilder builder = new TreeBuilder(nodeColors);
		builder.addConnections(0, 1);
		builder.addConnections(0, 2);
		builder.addConnections(0, 3);
		builder.addConnections(1, 3);
		builder.addConnections(2, 3);
		
		builder.addConnections(3, 4);
		builder.addConnections(4, 5);
		
		builder.addConnections(3, 9);
		builder.addConnections(9, 10);
		builder.addConnections(10, 5);
		
		builder.addConnections(5, 6);
		builder.addConnections(5, 7);
		builder.addConnections(5, 8);
		builder.addConnections(6, 8);
		builder.addConnections(7, 8);

		allPaths.calculateDistances(builder, builder.getNodeSetSize());
		
		MinimalCut minCut = new MinimalCut();
		GraphNode[] nodes;
		
		nodes = minCut.betweenTwoNodesByAllPaths(allPaths, 1, 11);
		assertEquals(0, nodes.length);
		System.out.println("++++++++++++++++++++++++++++++++++");

		
		nodes = minCut.betweenTwoNodesByAllPaths(allPaths, 5, 9);
		assertEquals(2, nodes.length);
		assertEquals(3, nodes[0].getIndex());
		assertEquals(10, nodes[1].getIndex());
		System.out.println("++++++++++++++++++++++++++++++++++");

		
		nodes = minCut.betweenTwoNodesByAllPaths(allPaths, 3, 5);
		assertEquals(2, nodes.length);
		assertEquals(4, nodes[0].getIndex());
		assertEquals(9, nodes[1].getIndex());
		System.out.println("++++++++++++++++++++++++++++++++++");

		
		nodes = minCut.betweenTwoNodesByAllPaths(allPaths, 0, 4);
		assertEquals(1, nodes.length);
		assertEquals(3, nodes[0].getIndex());
		System.out.println("++++++++++++++++++++++++++++++++++");
		
		for (GraphNode node : nodes) {
			System.out.print("- " + node.getValue() + " ");
		}
		System.out.println();
		
	}

	@Test
	public void test2() {
		int[] nodeColors = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

		TreeBuilder builder = new TreeBuilder(nodeColors);
		// upper path
		builder.addConnections(0, 1);
		builder.addConnections(1, 2);
		builder.addConnections(2, 3);
		builder.addConnections(3, 9);

		// straight path
		builder.addConnections(0, 4);
		builder.addConnections(4, 8);
		
		// lower path
		builder.addConnections(0, 5);
		builder.addConnections(5, 6);
		builder.addConnections(6, 7);
		builder.addConnections(7, 9);

		// complement path
		builder.addConnections(9, 8);
		
		allPaths.calculateDistances(builder, builder.getNodeSetSize());
		
		MinimalCut minCut = new MinimalCut();
		GraphNode[] nodes;
		
		nodes = minCut.betweenTwoNodesByAllPaths(allPaths, 8, 0);
		//assertEquals(0, nodes.length);
		System.out.println("++++++++++++++++++++++++++++++++++");
		
		for (GraphNode node : nodes) {
			System.out.print("- " + node.getValue() + " ");
		}
		System.out.println();
		
	}

}
