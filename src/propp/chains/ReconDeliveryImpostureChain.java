package propp.chains;

import proppFunction.FunctionChain;
import proppFunction.Node;
import proppFunction.NodeType;
import state.ConstantMatcher;
import state.Predicate;

public class ReconDeliveryImpostureChain implements ChainGenerator{
	
	@Override
	public void createSave() {
		FunctionChain C = new FunctionChain("ReconDeliveryImposture");
		Node n;
		
		n = new Node("casual_encounter_imposture", NodeType.EVENT);
		C.addNode(n);
		C.setInitial(n);
		n = new Node("intended_encounter_imposture", NodeType.ACTION);
		C.addNode(n);
		C.setInitial(n);
		n= new Node("falsehero_attempts_recon", NodeType.GOAL);
		C.addNode(n);
		C.setInitial(n);
		C.addEdge("casual_encounter_imposture", "falsehero_attempts_recon");
		C.addEdge("intended_encounter_imposture", "falsehero_attempts_recon");
		n= new Node("approach", NodeType.ACTION);
		C.addNode(n);
		C.addEdge("falsehero_attempts_recon", "falsehero_approach");
		n= new Node("falsehero_inquiry", NodeType.ACTION);
		C.addNode(n);
		C.addEdge("falsehero_approach", "falsehero_inquiry");
		n= new Node("falsehero_approach_ack", NodeType.PERCEPTION_INTERNAL);
		C.addNode(n);
		C.addEdge("falsehero_approach", "falsehero_approach_ack");
		n= new Node("conversation_imposture", NodeType.ACTION);
		C.addNode(n);
		C.addEdge("falsehero_approach_ack", "conversation_imposture");
		n= new Node("observation_imposture", NodeType.ACTION);
		C.addNode(n);
		C.addEdge("falsehero_attempts_recon", "observation_imposture");
		n= new Node("answer_imposture", NodeType.ACTION);
		C.addNode(n);
		C.addEdge("falsehero_inquiry", "answer_imposture");
		C.addEdge("conversation_imposture", "answer_imposture");
		n= new Node("recon_outcome_success_imposture", NodeType.OUTCOME);
		n.toAdd.addPredicate(new Predicate("recon_outcome_imposture", "positive", "_"));
		C.addNode(n);
		C.addEdge("answer_imposture", "recon_outcome_success_imposture");
		C.addEdge("observation_imposture", "recon_outcome_success_imposture");
		n= new Node("casual_hearing_imposture", NodeType.EVENT);
		C.addNode(n);
		C.setInitial(n);
		C.addEdge("casual_hearing_imposture", "recon_outcome_success_imposture");		
		n= new Node("recon_outcome_failure_imposture", NodeType.OUTCOME);
		n.toAdd.addPredicate(new Predicate("recon_outcome_imposture", "negative", "_"));
		C.addNode(n);
		C.addEdge("falsehero_inquiry", "recon_outcome_failure_imposture");
		C.addEdge("conversation_imposture", "recon_outcome_failure_imposture");
		
		C.serialize();
	}

}
