package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import data.network.*;

public class FlowTest {

	@Test
	public void test1() {
		Flow flow = new Flow(6, 8);
		flow.addEdge(0, 1, 1);
		flow.addEdge(0, 2, 1);

		flow.addEdge(1, 2, 1);

		flow.addEdge(1, 3, 1);
		flow.addEdge(2, 4, 1);

		flow.addEdge(3, 4, 1);

		flow.addEdge(3, 5, 1);
		flow.addEdge(4, 5, 1);

		assertEquals(2, flow.maxFlow(0, 5));

		System.out.println("\n");
	}



	@Test
	public void test2() {
		Flow flow = new Flow(6, 8);
		flow.addEdge(0, 1, 1);
		flow.addEdge(0, 2, 1);

		flow.addEdge(1, 2, 1);

		flow.addEdge(1, 3, 1);
		flow.addEdge(2, 4, 1);

		flow.addEdge(3, 4, 1);

		flow.addEdge(4, 5, 1);

		assertEquals(1, flow.maxFlow(0, 5));

		System.out.println("\n");

	}

}
