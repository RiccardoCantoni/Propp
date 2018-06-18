package plotGeneration;

import java.util.List;

import proppFunction.Node;
import state.Predicate;
import state.PredicateSet;
import state.State;

public class ImpasseScanner {
	
	public Predicate[] impassePredicates = new Predicate[] {
			new Predicate("recon_outcome","negative"),
			new Predicate("donor_test_outcome","negative"),
			new Predicate("guidance_outcome","negative"),
			new Predicate("struggle_outcome","negative"),
			new Predicate("recognition_outcome","negative")
	};
	
	public Impasse scanPath(List<Node> path, State finalstate) {
		PredicateSet set = finalstate.getSet();
		for (Predicate p : impassePredicates) {
			if (set.contains(p))
				return new Impasse(path, p, finalstate);
		}
		return null;
	}

}
