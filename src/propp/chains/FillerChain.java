package propp.chains;

import proppFunction.FunctionChain;
import proppFunction.Node;
import proppFunction.NodeType;
import state.Predicate;

public class FillerChain implements ChainGenerator{

	@Override
	public void createSave() {
		FunctionChain C = new FunctionChain("Filler");
		Node n;
		
		n = new Node("filler_retry", NodeType.ACTION);
		C.addNode(n);
		C.setInitial(n);
		n = new Node("filler_lack_item", NodeType.EVENT);
		n.toAdd.addPredicate(new Predicate("subplot_required","quest_item","_"));
		C.addNode(n);
		C.setInitial(n);
		n = new Node("filler_lack_helper", NodeType.EVENT);
		n.toAdd.addPredicate(new Predicate("subplot_required","quest_helper","_"));
		C.addNode(n);
		C.setInitial(n);
		n = new Node("hero_search", NodeType.ACTION);
		C.addNode(n);
		C.addEdge("filler_lack_item", "hero_search");
		C.addEdge("filler_lack_helper", "hero_search");
		
		C.serialize();
	}

}
