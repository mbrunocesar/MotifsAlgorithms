package heuristics.algorithms;

import data.GenericNode;
import data.motif.Motif;
import data.motif.SubMotifSet;
import helpers.visits.VisitCounter;
import helpers.visits.VisitList;
import helpers.visits.VisitedGraphs;
import helpers.visits.VisitedMotifs;
import heuristics.algorithms.bases.MotifSearchScaffold;

public class FullyWidthSearch extends MotifSearchScaffold {

	public SubMotifSet findMaxMotif(Motif targetMotif, GenericNode targetGraph) {
		SubMotifSet subMotifs = new SubMotifSet(targetMotif.getMotifSize());
		VisitCounter visitCounter = subMotifs.getVisitCounter();

		VisitedGraphs visiteds = new VisitedGraphs(targetMotif.getAppliedGraphSize());
		VisitList visitList = new VisitList();

		visitList.add(targetGraph);

		while (visitList.isNotEmpty()) {
			visitCounter.visitNode();
			GenericNode currentNode = visitList.useFirst();
			visiteds.setAsVisitedById(currentNode.getIndex());

			for (GenericNode linkedNode : currentNode.getConnections()) {
				visitCounter.visitLink();
				if (!visiteds.wasVisitedById(linkedNode.getIndex())){
					visitList.add(linkedNode);
				}
			}

			if (targetMotif.hasColor(currentNode.getValue())) {
				// Sub-Algorithm - START
				subMotifs.startBlock();

				VisitList motifVisitList = new VisitList();
				VisitedGraphs visitedsInMotif = new VisitedGraphs(targetMotif.getAppliedGraphSize());
				VisitedMotifs visitedsColors = new VisitedMotifs(targetMotif.getAppliedGraphSize());

				motifVisitList.add(currentNode);
				while (motifVisitList.isNotEmpty()) {
					visitCounter.visitNode();
					GenericNode currentMotifNode = motifVisitList.useFirst();

					subMotifs.add(currentMotifNode.getValue());
					visitedsInMotif.setAsVisitedById(currentMotifNode.getIndex());
					visitedsColors.setAsVisitedColor(currentMotifNode.getValue());

					for (GenericNode linkedNode : currentMotifNode.getConnections()) {
						visitCounter.visitLink();
						if (!visitedsInMotif.wasVisitedById(linkedNode.getIndex())
								&& !visitedsColors.wasVisitedColor(linkedNode.getValue())
								&& targetMotif.hasColor(linkedNode.getValue())) {

							motifVisitList.add(linkedNode);
							visitedsColors.setAsVisitedColor(linkedNode.getValue());
						}
					}
				}
				subMotifs.endBlock();
				// Sub-Algorithm - END
			}

		}

		return subMotifs;
	}

}
