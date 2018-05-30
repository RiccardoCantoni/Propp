package propp.chains;

import proppFunction.FunctionChain;
import proppFunction.Node;
import proppFunction.NodeType;
import state.AtomMatcher;

public class BrandingChain implements ChainGenerator{

	@Override
	public void createSave() {
		// TODO Auto-generated method stub
		FunctionChain C = new FunctionChain();
		C.FunctionName = "Branding";
		Node n;
			
		n=new Node("branding_mark", NodeType.EVENT);
        C.addNode(n);
        C.setInitial(n);
        n=new Node("branding_item", NodeType.EVENT);
        n.preconditions = new AtomMatcher("struggle_outcome","positive","_");
        C.addNode(n);
        C.setInitial(n);
        
        C.serialize();
        
		
	}

	
	
}
