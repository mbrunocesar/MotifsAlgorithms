package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import data.tree.TreeBuilder;
import paths.AllPathsCreator;

public class AllPathsCreatorTest {

	AllPathsCreator allPaths;
	TreeBuilder builder;

	boolean debug = false;

	@Before
	public void setUp() throws Exception {
		allPaths = new AllPathsCreator();

		int[] nodeColors = {0, 1, 2, 3, 4, 1, 6, 7, 8, 9, 10, 11, 12, 
				9, 1, 3, 3, 1, 1, 4, 6, 4, 5};

		builder = new TreeBuilder(nodeColors);
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
	}



	@Test
	public void test1() {
		allPaths.calculateDistances(builder, builder.getNodeSetSize());

		int[][] distances = allPaths.getDistancesMatrix();

		if (debug) {
			System.out.println("++++++++++++++++++++++++++++++++++");
			for (int[] line : distances) {
				for (int element : line) {
					System.out.print(element+" ");
				}
				System.out.println();
			}
		}


		for (int i = 0; i < distances.length; i++) {
			for (int j = 0; j < distances.length; j++) {
				if (i!=j) {
					assertTrue(distances[i][j] > 0);
				} else {
					assertTrue(distances[i][i] == 0);
				}

			}
		}
	}

	@Test
	public void test2() {
		allPaths.calculateDistances(builder, builder.getNodeSetSize());

		if (debug) {
			allPaths.printVisits();
		}

		List<Integer> path = allPaths.pathBetweenTwoNodes(4, 14);
		assertEquals(8, path.size());


		if (debug) {
			System.out.println("++++++++++++++++++++++++++++++++++");
			for (Integer node : path) {
				System.out.print("-> " + node + " ");
			}
			System.out.println();
			allPaths.printVisits();
		}

		assertEquals(8, path.size());

	}

}
