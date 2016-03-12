package data.motif;

import data.GenericNode;

public class Motif {

	int[] motifColors;

	boolean[] appliedMotif;
	
	public void initMotif(int[] newMotifData) {
		motifColors = newMotifData;
	}
	
	public void generateAppliedMotif(GenericNode[] graphData) {
		int graphSize = graphData.length;
		boolean[] mappedMotif = new boolean[graphSize];
		
		appliedMotif = new boolean[graphSize];

		for (int data: motifColors){
			mappedMotif[data] = true;
		}
		
		for (GenericNode data: graphData){
			appliedMotif[data.getIndex()] = mappedMotif[data.getValue()];
		}
	}
	
	public int getMotifSize() {
		return motifColors.length;
	}
	
	public int getAppliedGraphSize() {
		return appliedMotif.length;
	}
	
	public boolean hasColor(int idNode) {
		return appliedMotif[idNode];
	}

}
