package propp.chains;

import proppFunction.FunctionChain;
import proppFunction.Node;
import proppFunction.NodeType;
import state.AtomMatcher;
import state.OrMatcher;
import state.PredicateMatcher;

public class RewardChain implements ChainGenerator{

	@Override
	public void createSave() {
		FunctionChain C = new FunctionChain("Reward");
		Node n;
		
		PredicateMatcher captivity = new OrMatcher(new AtomMatcher("villainy", "abduction","_"), new AtomMatcher("villainy","imprisonment","_"));
		
		n = new Node("reward_marriage", NodeType.EVENT);
		n.preconditions = new OrMatcher(captivity, new AtomMatcher("villainy","war","_"));
		C.addNode(n);
		C.setInitial(n);
		n = new Node("reward_kingdom", NodeType.EVENT);
		C.addNode(n);
		C.setInitial(n);
		n = new Node("reward_betrothal", NodeType.EVENT);
		n.preconditions = new OrMatcher(captivity, new AtomMatcher("villainy","war","_"));
		C.addNode(n);
		C.setInitial(n);
		n = new Node("reward_wealth", NodeType.EVENT);
		C.addNode(n);
		C.setInitial(n);
		n = new Node("reward_item", NodeType.EVENT);
		C.addNode(n);
		C.setInitial(n);
		n = new Node("reward_land", NodeType.EVENT);
		C.addNode(n);
		C.setInitial(n);
		n = new Node("reward_chiefdom", NodeType.EVENT);
		n.preconditions = new AtomMatcher("dispatch","_","_");
		C.addNode(n);
		C.setInitial(n);
		
		C.serialize();
	}

}
