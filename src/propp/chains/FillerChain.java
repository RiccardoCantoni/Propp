package propp.chains;

import proppFunction.FunctionChain;
import proppFunction.Node;
import proppFunction.NodeType;

public class FillerChain implements ChainGenerator{

	@Override
	public void createSave() {
		FunctionChain C = new FunctionChain("Filler");
		Node n;
		
		n = new Node("filler_PLACEHOLDER", NodeType.NONE);
		C.addNode(n);
		C.setInitial(n);
		
		C.serialize();
	}

}
