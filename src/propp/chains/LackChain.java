package propp.chains;

import proppFunction.FunctionChain;
import proppFunction.Node;
import proppFunction.NodeType;
import state.AtomMatcher;
import state.OrMatcher;
import state.Predicate;

public class LackChain implements ChainGenerator {

	@Override
	public void createSave() {
		FunctionChain C= new FunctionChain();
		C.FunctionName = "Lack";
		Node n;
        
        n = new Node("lack_helper", NodeType.INTERNAL);
        n.toAdd.addPredicate(new Predicate("lack","helper"));
        n.preconditions = new OrMatcher(
	        		new AtomMatcher("villainy","war","_"), 
	        		new AtomMatcher("villainy","pillaging","_")
	        		);
        C.addNode(n);
        C.setInitial(n);
        n = new Node("lack_item", NodeType.INTERNAL);
        n.toAdd.addPredicate(new Predicate("lack","item"));
        n.preconditions = new OrMatcher(
        		new OrMatcher(
		    		new AtomMatcher("villainy","war","_"), 
		    		new AtomMatcher("villainy","pillaging","_")
		    		),
        		new AtomMatcher("villainy", "seizure", "_")
        		);
        C.addNode(n);
        C.setInitial(n);
        n = new Node("lack_vengeance", NodeType.INTERNAL);
        n.toAdd.addPredicate(new Predicate("lack","vengeance"));
        C.addNode(n);
        C.setInitial(n);
        n = new Node("lack_wealth", NodeType.INTERNAL);
        n.toAdd.addPredicate(new Predicate("lack","wealth"));
        n.preconditions = new OrMatcher(
        		new AtomMatcher("villainy","seizure","_"), 
        		new AtomMatcher("villainy","pillaging","_")
        		);
        C.addNode(n);
        C.setInitial(n);
        n = new Node("lack_freedom", NodeType.INTERNAL);
        n.toAdd.addPredicate(new Predicate("lack","freedom"));
        n.preconditions = new OrMatcher(
        		new AtomMatcher("villainy","abduction","_"), 
        		new AtomMatcher("villainy","imprisonment","_")
        		);
        C.addNode(n);
        C.setInitial(n);
        
        C.serialize();
	}

}
