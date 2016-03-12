package paths;

import java.util.LinkedList;
import java.util.List;

public class SimplePath {
	
	List<Integer> path;
	
	public SimplePath() {
		path = new LinkedList<Integer>();
	}
	
	public void add(int nodeValue) {
		path.add(nodeValue);
	}

}
