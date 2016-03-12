package heuristics.algorithms.bases;

import data.GenericNode;
import data.motif.Motif;
import data.motif.SubMotifSet;

public interface MotifSearchAlgorithm {

	public SubMotifSet findMaxMotif(Motif targetMotif, GenericNode targetGraph);

}
