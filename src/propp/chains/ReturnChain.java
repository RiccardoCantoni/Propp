package propp.chains;

import proppFunction.FunctionChain;
import proppFunction.Node;
import proppFunction.NodeType;
import state.AtomMatcher;
import state.Predicate;

public class ReturnChain implements ChainGenerator {

	@Override
	public void createSave() {
		FunctionChain C = new FunctionChain();
		C.FunctionName = "Return";
		Node n;
		
		n = new Node("hero_return", NodeType.ACTION);
		n.preconditions = new AtomMatcher(new Predicate("struggle_outcome","positive","_"));
		C.addNode(n);
		C.setInitial(n);
		n = new Node("hero_escape", NodeType.ACTION);
		n.preconditions = new AtomMatcher(new Predicate("struggle_outcome","negative","_"));
		C.addNode(n);
		C.setInitial(n);
		
		C.serialize();
	}

}
