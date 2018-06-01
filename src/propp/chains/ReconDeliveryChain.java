package propp.chains;

import proppFunction.FunctionChain;
import proppFunction.Node;
import proppFunction.NodeType;

public class ReconDeliveryChain implements ChainGenerator {

	@Override
	public void createSave() {
		FunctionChain C = new FunctionChain();
		C.FunctionName = "ReconDelivery";
		Node n;
		
		n = new Node("casual_encounter", NodeType.EVENT);
		C.addNode(n);
		C.setInitial(n);
		n= new Node("villain_attempts_recon", NodeType.GOAL);
		C.addNode(n);
		C.setInitial(n);
		C.addEdge("casual_encounter", "villain_attempts_recon");
		n= new Node("approach", NodeType.ACTION);
		C.addNode(n);
		C.addEdge("villain_attempts_recon", "approach");
		n= new Node("inquiry", NodeType.ACTION);
		C.addNode(n);
		C.addEdge("approach", "inquiry");
		n= new Node("approach_ack", NodeType.PI);
		C.addNode(n);
		C.addEdge("approach", "approach_ack");
		n= new Node("conversation", NodeType.ACTION);
		C.addNode(n);
		C.addEdge("approach_ack", "conversation");
		n= new Node("observation", NodeType.ACTION);
		C.addNode(n);
		C.addEdge("villain_attempts_recon", "observation");
		n= new Node("answer", NodeType.ACTION);
		C.addNode(n);
		C.addEdge("inquiry", "answer");
		C.addEdge("conversation", "answer");
		n= new Node("recon_outcome_success", NodeType.OUTCOME);
		C.addNode(n);
		C.addEdge("answer", "recon_outcome_success");
		C.addEdge("observation", "recon_outcome_success");
		n= new Node("casual_hearing", NodeType.EVENT);
		C.addNode(n);
		C.setInitial(n);
		C.addEdge("casual_hearing", "recon_outcome_success");
		n= new Node("recon_outcome_failure", NodeType.OUTCOME);
		C.addNode(n);
		C.addEdge("inquiry", "recon_outcome_failure");
		C.addEdge("conversation", "recon_outcome_failure");
		
		C.serialize();
	}

}
