package data.motif;

import helpers.visits.VisitCounter;

import java.util.List;
import java.util.LinkedList;

public class SubMotifSet {

	int maxSubMotifSize = 0;
	
	int currentSubMotifSize = 0;
	int leastOfMotifSize = 0;
	
	SubMotif maxSubMotif;
	SubMotif currentSubMotif;
	List<SubMotif> subMotifSet;
	
	VisitCounter visitCounter;

	public SubMotifSet(int motifSize) {
		visitCounter = new VisitCounter();
		subMotifSet = new LinkedList<SubMotif>();
		leastOfMotifSize = motifSize;
	}

	public void startBlock() {
		currentSubMotif = new SubMotif();
		subMotifSet.add(currentSubMotif);
		
		currentSubMotifSize = 0;
	}
	
	public void endBlock() {
		if (currentSubMotifSize > maxSubMotifSize) {
			maxSubMotifSize = currentSubMotifSize;
		}
	}
	
	public boolean shouldEndSearch() {
		return leastOfMotifSize <= maxSubMotifSize;
	}

	public void add(int id) {
		currentSubMotif.add(id);
		
		currentSubMotifSize++;
		leastOfMotifSize--;
	}

	public int size() {
		return subMotifSet.size();
	}

	public void loadMaxMotif() {
		int biggerSize = -1;
		SubMotif biggerSubMotif = new SubMotif(); 

		for (SubMotif subMotif : subMotifSet) {
			if (subMotif.size() > biggerSize) {
				biggerSubMotif = subMotif;
				biggerSize = subMotif.size();
			}
		}

		maxSubMotif = biggerSubMotif;
	}
	
	public SubMotif getMaxMotif() {
		if (maxSubMotif == null) {
			loadMaxMotif();
		}
		return maxSubMotif;
	}
	
	public VisitCounter getVisitCounter() {
		return visitCounter;
	}
	
	// PRINT METHODS SHOULDN'T BE HERE
	public void printAll() {
		String textToPrint = "";

		textToPrint += "[";
		for (SubMotif subMotifNodes : subMotifSet) {
			textToPrint += "(";
			for (Integer nodeValue : subMotifNodes.getData()) {
				textToPrint += (nodeValue+",");
			}
			textToPrint += ") ";
		}
		textToPrint += "]";

		System.out.println(textToPrint);
	}

	public void printBiggerSubMotif() {
		String textToPrint = "";

		textToPrint += "(";
		for (Integer nodeValue : getMaxMotif().getData()) {
			textToPrint += (nodeValue+",");
		}
		textToPrint += ") ";

		System.out.println(textToPrint);
	}

}
