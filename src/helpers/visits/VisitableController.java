package helpers.visits;

public class VisitableController {

	protected int visitedNodes;
	protected int visitedLinks;
	
	public int[] getVisits() {
		int[] returnedVisits = {visitedNodes, visitedLinks}; 
		return returnedVisits; 
	}

	public void printVisits() {
		System.out.println("++++++++++++++++++++++++++++++++++");
		System.out.println("Visitas a nós: " + visitedNodes);
		System.out.println("Visitas a Links: " + visitedLinks);
	}
	
}
