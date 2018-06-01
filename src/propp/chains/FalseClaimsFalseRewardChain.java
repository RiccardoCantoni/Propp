package propp.chains;

import proppFunction.FunctionChain;
import proppFunction.Node;
import proppFunction.NodeType;

public class FalseClaimsFalseRewardChain implements ChainGenerator {

	@Override
	public void createSave() {
		
		FunctionChain C = new FunctionChain();
		C.FunctionName = "FalseClaimsFalseReward";
		Node n;
		
		n = new Node("false_claims", NodeType.ACTION);
		C.addNode(n);
		C.setInitial(n);
		n = new Node("false_claims_received", NodeType.PERCEPTION);
		C.addNode(n);
		C.addEdge("false_claims","false_claims_received");
		n = new Node("false_claims_accepted", NodeType.INTERNAL);
		C.addNode(n);
		C.addEdge("false_claims","false_claims_accepted");
		C.addEdge("false_claims_received","false_claims_accepted");
		n = new Node("falsehero_rewarded", NodeType.ACTION);
		C.addNode(n);
		C.addEdge("false_claims_accepted","falsehero_rewarded");
		
		C.serialize();
	}

}
