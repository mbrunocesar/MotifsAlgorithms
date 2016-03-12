package data.motif;

import java.util.LinkedList;
import java.util.List;

public class SubMotif {

	List<Integer> subMotifData;

	public SubMotif() {
		subMotifData = new LinkedList<Integer>();
	}

	public SubMotif(List<Integer> motifData) {
		subMotifData = motifData;
	}
	
	public int size() {
		return subMotifData.size();
	}

	public void add(int id) {
		subMotifData.add(id);
	}

	public List<Integer> getData() {
		return subMotifData;
	}

	public Integer get(int id) { 
		return subMotifData.get(id);
	}

}
