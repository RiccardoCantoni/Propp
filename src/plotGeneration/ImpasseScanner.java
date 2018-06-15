package plotGeneration;

import java.util.Arrays;
import java.util.List;

import proppFunction.Node;
import state.AtomMatcher;
import state.Predicate;
import state.PredicateSet;
import state.State;

public class ImpasseScanner {
	
	Predicate[] impassePredicates = new Predicate[] {
			new Predicate("recon_outcome","negative"),
			new Predicate("donor_test_outcome","negative"),
			new Predicate("guidance_outcome","negative"),
			new Predicate("struggle_outcome","negative"),
			new Predicate("recognition_outcome","negative")
	};
	
	public boolean scanPath(List<Node> path, State finalstate) {
		PredicateSet set = finalstate.getSet();
		return Arrays.asList(impassePredicates).stream().anyMatch(p -> set.contains(p));
	}

}
