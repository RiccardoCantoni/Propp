package propp.chains;

import proppFunction.FunctionChain;
import proppFunction.Node;
import proppFunction.NodeType;
import state.Predicate;

public class RecognitionChain implements ChainGenerator{

	@Override
	public void createSave() {
		
		FunctionChain C = new FunctionChain();
		C.FunctionName = "Recognition";
		Node n;
		
		n = new Node("hero_presents_claims", NodeType.ACTION);
		C.addNode(n);
		C.setInitial(n);
		n = new Node("claims_received_by_dispatcher", NodeType.PERCEPTION);
		C.addNode(n);
		C.addEdge("hero_presents_claims","claims_received_by_dispatcher");
		n = new Node("claims_denied", NodeType.INTERNAL);
		C.addNode(n);
		C.addEdge("hero_presents_claims","claims_denied");
		C.addEdge("claims_received_by_dispatcher","claims_denied");
		n = new Node("hero_lacks_proof", NodeType.EVENT);
		n.toAdd.addPredicate(new Predicate("recognition_outcome","failure","_"));
		C.addNode(n);
		C.addEdge("claims_denied","hero_lacks_proof");
		n = new Node("proof_required_item", NodeType.OUTCOME);
		C.addNode(n);
		C.addEdge("hero_lacks_proof", "proof_required_item");
		n = new Node("proof_required_mark", NodeType.OUTCOME);
		C.addNode(n);
		C.addEdge("hero_lacks_proof", "proof_required_mark");
		n = new Node("hero_confronts_falsehero", NodeType.ACTION);
		C.addNode(n);
		C.addEdge("claims_denied","hero_confronts_falsehero");
		n = new Node("hero_recognised_by_item", NodeType.PI);
		C.addNode(n);
		C.addEdge("hero_confronts_falsehero","hero_recognised_by_item");
		n = new Node("hero_recognised_by_mark", NodeType.PI);
		C.addNode(n);
		C.addEdge("hero_confronts_falsehero","hero_recognised_by_mark");
		n = new Node("hero_recognised", NodeType.OUTCOME);
		n.toAdd.addPredicate(new Predicate("recognition_outcome","success","_"));
		C.addNode(n);
		C.addEdge("hero_recognised_by_mark","hero_recognised");
		C.addEdge("hero_recognised_by_item","hero_recognised");
		
		
		
		C.serialize();
	}

}
