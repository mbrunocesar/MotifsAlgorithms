package heuristics;

import data.GenericNode;
import data.motif.Motif;
import data.motif.SubMotifSet;
import initial.Starter;
import helpers.visits.VisitCounter;
import heuristics.algorithms.FullyWidthSearch;
import heuristics.algorithms.PriorizedWidthSearch;
import heuristics.algorithms.bases.MotifSearchAlgorithm;
import heuristics.optimizers.SubTreeGenerator;

public class MaxMotifSearch {

	public static void main() {
		new Starter();
	}
	
	SubMotifSet maxMotifSetFound;
	MotifSearchAlgorithm searchAlgorithm;
	
	MotifSearchAlgorithm[] disponibleAlgorithm = {
			new PriorizedWidthSearch(),
			new FullyWidthSearch()}; 

	public MaxMotifSearch() {}

	public MaxMotifSearch(Motif targetMotif, GenericNode targetGraph) {
		searchForMaxMotif(targetMotif, targetGraph);
	}
	
	public SubMotifSet searchForMaxMotif(Motif targetMotif, GenericNode targetGraph) {
		// By default use priorized width search
		return searchForMaxMotif(targetMotif, targetGraph, 0);
	}

	int visitedNodes;
	int visitedLinks;
	
	public SubMotifSet searchForMaxMotif(Motif targetMotif, GenericNode targetGraph, int algorithmToUse) {
		visitedNodes = 0;
		visitedLinks = 0;
		
		SubTreeGenerator subTree = new SubTreeGenerator();
		GenericNode[] connectedGraphs = subTree.findSubTrees(targetGraph, targetMotif);

		SubMotifSet lastMotifSetFound;
		maxMotifSetFound = new SubMotifSet(0);
		
		// Test with priorized width search
		searchAlgorithm = disponibleAlgorithm[algorithmToUse];
		for (GenericNode subGraph : connectedGraphs) {
			lastMotifSetFound = searchAlgorithm.findMaxMotif(targetMotif, subGraph);
			
			VisitCounter visitCounter = lastMotifSetFound.getVisitCounter();
			visitedNodes += visitCounter.getVisitedNodes();
			visitedLinks += visitCounter.getVisitedLinks();
			
			if (maxMotifSetFound.getMaxMotif().size() < lastMotifSetFound.getMaxMotif().size()) {
				maxMotifSetFound = lastMotifSetFound;
			}
		}

		return maxMotifSetFound;
	}

	public void printSubMotifs() {
		maxMotifSetFound.printAll();
	}

	public void printMaxSubMotif() {
		maxMotifSetFound.printBiggerSubMotif();
	}

	public int[] getVisits() {
		int[] returnedVisits = {visitedNodes, visitedLinks}; 
		return returnedVisits; 
	}

	public void printVisits() {
		System.out.println("Visitas a nós: " + visitedNodes);
		System.out.println("Visitas a Links: " + visitedLinks);
	}


}
