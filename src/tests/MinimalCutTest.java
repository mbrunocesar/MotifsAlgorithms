package tests;

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
	

	@Test
	public void test() {
		int[] nodeColors = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

		TreeBuilder builder = new TreeBuilder(nodeColors);
		builder.addConnections(0, 1);
		builder.addConnections(0, 2);
		builder.addConnections(0, 3);

		builder.addConnections(1, 3);

		builder.addConnections(2, 3);
		
		builder.addConnections(3, 4);

		builder.addConnections(4, 5);
		
		builder.addConnections(4, 6);
		
		builder.addConnections(4, 7);
		builder.addConnections(5, 7);
		builder.addConnections(6, 7);

		builder.addConnections(3, 8);
		builder.addConnections(8, 4);

		builder.addConnections(2, 9);
		builder.addConnections(9, 4);
		
		builder.addConnections(1, 10);
		builder.addConnections(10, 5);

		allPaths.calculateDistances(builder, builder.getNodeSetSize());

		MinimalCut minCut = new MinimalCut();
		GraphNode[] nodes = minCut.betweenTwoGraphsByAllPaths(allPaths, 3, 4);
		
		System.out.println("++++++++++++++++++++++++++++++++++");
		for (GraphNode node : nodes) {
			System.out.print("- " + node.getValue() + " ");
		}
		System.out.println();
		
	}

}
