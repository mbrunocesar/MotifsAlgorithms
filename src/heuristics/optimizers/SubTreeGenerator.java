package heuristics.optimizers;

import helpers.visits.VisitList;
import helpers.visits.VisitedGraphs;

import java.util.LinkedList;
import java.util.List;

import data.GenericNode;
import data.motif.Motif;

public class SubTreeGenerator {

	/**
	 * Complexity O(N) - Breaks the target Graph in smallers sub-Graph
	 * Makes graph motif search passible to parralel processing
	 * 
	 * @param targetGraph
	 * @param targetMotif
	 * @return
	 */
	public GenericNode[] findSubTrees(GenericNode targetGraph, Motif targetMotif) {
		List<GenericNode> connectedSubGraphs = new LinkedList<GenericNode>();

		connectedSubGraphs.add(targetGraph);

		VisitedGraphs visiteds = new VisitedGraphs(targetMotif.getAppliedGraphSize());
		VisitList visitList = new VisitList();

		visitList.add(targetGraph);
		
		while (visitList.isNotEmpty()) {
			GenericNode currentNode = visitList.useFirst();
						visiteds.setAsVisitedById(currentNode.getIndex());

			for (GenericNode linkedNode : currentNode.getConnections()) {
				if (!visiteds.wasVisitedById(linkedNode.getIndex())){
					if (targetMotif.hasColor(currentNode.getValue())
						&& currentNode.getValue() == linkedNode.getValue()) {
						
						currentNode.removeConnection(linkedNode);
						connectedSubGraphs.add(linkedNode);
					}
					
					visitList.add(linkedNode);
				}
			}
		}

		return connectedSubGraphs.toArray(new GenericNode[connectedSubGraphs.size()]);
	}
	
}
