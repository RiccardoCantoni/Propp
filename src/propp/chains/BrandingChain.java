package propp.chains;

import proppFunction.FunctionChain;
import proppFunction.Node;
import proppFunction.NodeType;
import state.AtomMatcher;
import state.Predicate;

public class BrandingChain implements ChainGenerator{

	@Override
	public void createSave() {
		// TODO Auto-generated method stub
		FunctionChain C = new FunctionChain("Branding");
		Node n;
			
		n=new Node("branding_mark", NodeType.EVENT);
		n.toAdd.addPredicate(new Predicate("branding","mark","_"));
        C.addNode(n);
        C.setInitial(n);
        n=new Node("branding_item", NodeType.EVENT);
        n.toAdd.addPredicate(new Predicate("branding","item","_"));
        n.preconditions = new AtomMatcher("struggle_outcome","positive","_");
        C.addNode(n);
        C.setInitial(n);
        
        C.serialize();
        
		
	}

	
	
}
