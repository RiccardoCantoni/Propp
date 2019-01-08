package propp.chains;

import proppFunction.ProppFunction;
import proppFunction.Node;
import proppFunction.NodeType;
import state.AtomMatcher;
import state.ConstantMatcher;
import state.NotMatcher;
import state.Predicate;

public class ReturnChain implements FunctionGenerator {

	@Override
	public void createSave() {
		ProppFunction C = new ProppFunction("Return");
		Node n;
		
		n = new Node("hero_return", NodeType.ACTION);
		n.preconditions = new AtomMatcher(new Predicate("struggle_outcome","positive","_"));
		C.addNode(n);
		C.setInitial(n);
		n = new Node("hero_arrival", NodeType.EVENT);
		C.addNode(n);
		C.addEdge("hero_return","hero_arrival");
		n = new Node("unrecognised_arrival", NodeType.EVENT);
		n.preconditions = new ConstantMatcher(false);
		C.addNode(n);
		C.addEdge("hero_return","unrecognised_arrival");
		n = new Node("hero_escape", NodeType.ACTION);
		n.preconditions = new NotMatcher(new AtomMatcher(new Predicate("struggle_outcome","positive","_")));
		C.addNode(n);
		C.setInitial(n);
		n = new Node("hero_returning", NodeType.EVENT);
		n.toAdd.addPredicate(new Predicate("subplot_required", "imposture"));
		C.addNode(n);
		C.addEdge("hero_return", "hero_returning");
		
		C.serialize();
	}

}
