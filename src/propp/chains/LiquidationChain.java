package propp.chains;

import proppFunction.FunctionChain;
import proppFunction.Node;
import proppFunction.NodeType;
import state.AtomMatcher;
import state.Predicate;

public class LiquidationChain implements ChainGenerator{

	@Override
	public void createSave() {
		FunctionChain C = new FunctionChain("Liquidation");
		Node n;
		
		n = new Node("item_seizure", NodeType.ACTION);
		n.preconditions = new AtomMatcher(new Predicate("lack","item","_"));
		n.toRemove.addPredicate(new Predicate("lack","item","_"));
		C.addNode(n);
		C.setInitial(n);
		n = new Node("villain_defeated", NodeType.EVENT);
		n.preconditions = new AtomMatcher(new Predicate("lack","vengeance","_"));
		n.toRemove.addPredicate(new Predicate("lack","vengeance","_"));
		C.addNode(n);
		C.setInitial(n);
		n = new Node("villain_killed", NodeType.ACTION);
		C.addNode(n);
		C.addEdge("villain_defeated","villain_killed");
		n = new Node("villain_imprisoned", NodeType.ACTION);
		C.addNode(n);
		C.addEdge("villain_defeated","villain_imprisoned");
		n = new Node("item_provides wealth", NodeType.EVENT);
		n.preconditions = new AtomMatcher(new Predicate("lack","wealth","_"));
		n.toRemove.addPredicate(new Predicate("lack","wealth","_"));
		C.addNode(n);
		C.setInitial(n);
		n = new Node("captive_freed", NodeType.ACTION);
		n.preconditions = new AtomMatcher(new Predicate("lack","freedom","_"));
		n.toRemove.addPredicate(new Predicate("lack","freedom","_"));
		C.addNode(n);
		C.setInitial(n);
		n = new Node("helper_found", NodeType.EVENT);
		n.preconditions = new AtomMatcher(new Predicate("lack","helper","_"));
		n.toRemove.addPredicate(new Predicate("lack","helper","_"));
		C.addNode(n);
		C.setInitial(n);
		
		C.serialize();
	}

}
