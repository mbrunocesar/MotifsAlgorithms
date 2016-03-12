package helpers.visits;

import java.util.LinkedList;
import java.util.List;

import data.GenericNode;

public class VisitList {

	LinkedList<GenericNode> toVisit;
	
	public VisitList() {
		toVisit = new LinkedList<GenericNode>(); 
	}
	
	public void add(GenericNode node) {
		toVisit.addLast(node);
	}
	
	public GenericNode useFirst() {
		return toVisit.removeFirst();
	}
	
	public boolean isNotEmpty() {
		return toVisit.size() > 0;
	}
	
	public List<GenericNode> getList() {
		return toVisit;
	}

}
