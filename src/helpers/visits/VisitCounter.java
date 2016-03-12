package helpers.visits;

public class VisitCounter {

	protected int nodeVisitCount = 0;
	protected int linkVisitCount = 0;

	protected void clearVisits() {
		nodeVisitCount = 0;
		linkVisitCount = 0;
	}
	
	public void visitNode() {
		nodeVisitCount++;
	}
	
	public void visitLink() {
		linkVisitCount++;
	}
	
	public int getVisitedNodes() {
		return nodeVisitCount;
	}
	
	public int getVisitedLinks() {
		return linkVisitCount;
	}
	
	public int[] getVisits() {
		int[] returnedVisits = {nodeVisitCount, linkVisitCount}; 
		return returnedVisits; 
	}

	public void printVisits() {
		System.out.println("++++++++++++++++++++++++++++++++++");
		System.out.println("Visitas a nós: " + nodeVisitCount);
		System.out.println("Visitas a Links: " + linkVisitCount);
	}
	
}
