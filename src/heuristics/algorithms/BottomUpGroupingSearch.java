package heuristics.algorithms;

import java.util.LinkedList;
import java.util.List;

import data.GenericNode;
import data.motif.Motif;
import data.motif.SubMotifSet;
import helpers.visits.VisitCounter;
import helpers.visits.VisitList;
import helpers.visits.VisitedGraphs;
import heuristics.algorithms.bases.MotifSearchScaffold;

/**
 * 
 * @author root
 * NOT COMPLETE! MUST SEARCH FOR BETTER COMPLEMENTARY ALGORITHMS
 */
public class BottomUpGroupingSearch extends MotifSearchScaffold {

	VisitCounter visitCounter;
	
	public List<List<GenericNode>> buildDegreeClassifiedTree(GenericNode targetGraph, int graphSize){
		LinkedList<List<GenericNode>> degreeClassifiedTree = new LinkedList<List<GenericNode>>();
		
		VisitList visitList = new VisitList();
		visitList.add(targetGraph);
		
		while (visitList.isNotEmpty()) {
			List<GenericNode> currentDegree = visitList.getList(); 
			degreeClassifiedTree.addFirst(currentDegree);
			visitList = new VisitList();
			
			for (GenericNode currentNode : currentDegree) {
				visitCounter.visitNode();
				visitList.add(currentNode);
			}
		}
		
		return degreeClassifiedTree;
	}
	
	@Override
	public SubMotifSet findMaxMotif(Motif targetMotif, GenericNode targetGraph) {
		SubMotifSet subMotifs = new SubMotifSet(targetMotif.getMotifSize());
		visitCounter = subMotifs.getVisitCounter();
		
		int graphSize = targetMotif.getAppliedGraphSize();
		
		List<List<GenericNode>> inverseTree = buildDegreeClassifiedTree(targetGraph, graphSize);
		
		for (List<GenericNode> degree : inverseTree) {
			int parentNumber = 0;
			
			GenericNode subGraph = null;
			
			for (GenericNode currentSubGraph : degree) {
				if (subGraph == null) {
					subGraph = currentSubGraph;
					continue;
				} else {
					
				}
			}
			
		}
		

		VisitedGraphs visiteds = new VisitedGraphs(graphSize);
		VisitList visitList = new VisitList();

		VisitList motifVisitList = new VisitList();
		

		visitList.add(targetGraph);

		while (visitList.isNotEmpty()) {
			visitCounter.visitNode();
			GenericNode currentNode = visitList.useFirst();
			visiteds.setAsVisitedById(currentNode.getIndex());

			if (targetMotif.hasColor(currentNode.getValue())) {
				

			} else {
				for (GenericNode linkedNode : currentNode.getConnections()) {
					visitCounter.visitLink();
					if (!visiteds.wasVisitedById(linkedNode.getIndex())){
						visitList.add(linkedNode);
					}
				}
			}

		}

		return subMotifs;
	}

}
