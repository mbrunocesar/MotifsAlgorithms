package data;

public class GenericEdge {

	int from;
	int to;
	int capacity;
	
	int idEdge;
	
	GenericEdge returnEdge;
	
	static int numEdges = 0;
	
	public GenericEdge(int argFrom, int argTo, int argCapacity) {
		from = argFrom;
		to = argTo;
		capacity = argCapacity;
		
		idEdge = numEdges++;
	}
	
	public static void clearEdges() {
		numEdges = 0;
	}
	
	public int getId() {
		return idEdge;
	}
	
	public int getFrom() {
		return from;
	}
	
	public int getTo() {
		return to;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public String toString() {
		return from+"->"+to+":"+capacity;
	}
	
	public void addReturnEdge(GenericEdge edge) {
		returnEdge = edge;
	}
	
	public GenericEdge getReturnEdge() {
		return returnEdge;
	}

}
