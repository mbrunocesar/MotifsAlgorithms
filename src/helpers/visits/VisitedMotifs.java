package helpers.visits;

public class VisitedMotifs {

	boolean[] isVisited;
	
	public VisitedMotifs(int numElements) {
		isVisited = new boolean[numElements];
	}
	
	public boolean wasVisitedColor(int idVisited) {
		return isVisited[idVisited];
	}
	
	public void setAsVisitedColor(int idVisited) {
		isVisited[idVisited] = true;
	}

}
