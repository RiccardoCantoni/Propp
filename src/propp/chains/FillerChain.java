package propp.chains;

import proppFunction.ProppFunction;
import proppFunction.Node;
import proppFunction.NodeType;
import state.ConstantMatcher;
import state.Predicate;
import state.PredicateMatcher;

public class FillerChain implements FunctionGenerator{

	@Override
	public void createSave() {
		ProppFunction C = new ProppFunction("Filler");
		Node n;
		
		PredicateMatcher neverMatch = new ConstantMatcher(false);
		//non existing predicate to ensure that paths are only followed if injected
		//all nodes are blocked, unless injected
		n = new Node("filler_villain_retry", NodeType.ACTION);
		n.preconditions = neverMatch;
		C.addNode(n);
		C.setInitial(n);
		
		n = new Node("hero_quest", NodeType.ACTION);
		C.addNode(n);
		C.setInitial(n);
		
		n = new Node("filler_hero_retry", NodeType.ACTION);
		n.preconditions = neverMatch;
		C.addNode(n);
		C.setInitial(n);
		
		n = new Node("filler_lack_item", NodeType.EVENT);
		n.preconditions = neverMatch;
		n.toAdd.addPredicate(new Predicate("subplot_required","quest_item","_"));
		C.addNode(n);
		
		n = new Node("filler_lack_helper", NodeType.EVENT);
		n.preconditions = neverMatch;
		n.toAdd.addPredicate(new Predicate("subplot_required","quest_helper","_"));
		C.addNode(n);
		
		
		C.addEdge("hero_quest", "filler_lack_item");
		C.addEdge("hero_quest", "filler_lack_helper");
		
		C.serialize();
	}

}
