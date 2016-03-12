package helpers.visits;

public class VisitedGraphs {

	boolean[] isVisited;
	
	public VisitedGraphs(int numElements) {
		isVisited = new boolean[numElements];
	}
	
	public boolean wasVisitedById(int idVisited) {
		return isVisited[idVisited];
	}
	
	public void setAsVisitedById(int idVisited) {
		isVisited[idVisited] = true;
	}

}
