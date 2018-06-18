package propp.chains;

import proppFunction.FunctionChain;
import proppFunction.Node;
import proppFunction.NodeType;
import state.AtomMatcher;
import state.NotMatcher;
import state.Predicate;

public class ReturnChain implements ChainGenerator {

	@Override
	public void createSave() {
		FunctionChain C = new FunctionChain("Return");
		Node n;
		
		n = new Node("hero_return", NodeType.ACTION);
		n.preconditions = new AtomMatcher(new Predicate("struggle_outcome","positive","_"));
		C.addNode(n);
		C.setInitial(n);
		n = new Node("hero_arrival", NodeType.EVENT);
		C.addNode(n);
		C.addEdge("hero_return","hero_arrival");
		n = new Node("unrecognised_arrival", NodeType.EVENT);
		C.addNode(n);
		C.addEdge("hero_return","unrecognised_arrival");
		n = new Node("hero_escape", NodeType.ACTION);
		n.preconditions = new NotMatcher(new AtomMatcher(new Predicate("struggle_outcome","negative","_")));
		C.addNode(n);
		C.setInitial(n);
		
		C.serialize();
	}

}
