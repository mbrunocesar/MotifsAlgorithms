package heuristics.algorithms;

import data.GenericNode;
import data.motif.Motif;
import data.motif.SubMotifSet;
import helpers.visits.VisitCounter;
import helpers.visits.VisitList;
import helpers.visits.VisitedGraphs;
import helpers.visits.VisitedMotifs;
import heuristics.algorithms.bases.MotifSearchScaffold;

public class PriorizedWidthSearch extends MotifSearchScaffold {

	@Override
	public SubMotifSet findMaxMotif(Motif targetMotif, GenericNode targetGraph) {
		SubMotifSet subMotifs = new SubMotifSet(targetMotif.getMotifSize());
		VisitCounter visitCounter = subMotifs.getVisitCounter();

		VisitedGraphs visiteds = new VisitedGraphs(targetMotif.getAppliedGraphSize());
		VisitList visitList = new VisitList();

		VisitList motifVisitList = new VisitList();

		visitList.add(targetGraph);

		while (visitList.isNotEmpty()) {
			visitCounter.visitNode();
			GenericNode currentNode = visitList.useFirst();
			visiteds.setAsVisitedById(currentNode.getIndex());

			if (targetMotif.hasColor(currentNode.getValue())) {
				// Sub-Algorithm - START
				subMotifs.startBlock();

				VisitedMotifs visitedsColors = new VisitedMotifs(targetMotif.getAppliedGraphSize());
				
				motifVisitList.add(currentNode);
				while (motifVisitList.isNotEmpty()) {
					visitCounter.visitNode();
					GenericNode currentMotifNode = motifVisitList.useFirst();

					subMotifs.add(currentMotifNode.getValue());
					visiteds.setAsVisitedById(currentMotifNode.getIndex());
					visitedsColors.setAsVisitedColor(currentMotifNode.getValue());

					for (GenericNode linkedNode : currentMotifNode.getConnections()) {
						visitCounter.visitLink();
						if (!visiteds.wasVisitedById(linkedNode.getIndex())
								&& !visitedsColors.wasVisitedColor(linkedNode.getValue())){
							
							if (targetMotif.hasColor(linkedNode.getValue())) {
								motifVisitList.add(linkedNode);
							} else {
								visitList.add(linkedNode);
							}
							visitedsColors.setAsVisitedColor(linkedNode.getValue());
						}
					}
				}
				subMotifs.endBlock();
				// Sub-Algorithm - END

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
