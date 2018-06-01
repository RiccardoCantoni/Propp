package propp.chains;

import proppFunction.FunctionChain;
import proppFunction.Node;
import proppFunction.NodeType;

public class ExposurePunishmentChain implements ChainGenerator{

	@Override
	public void createSave() {
		FunctionChain C = new FunctionChain();
		C.FunctionName = "ExposurePunishment";
		Node n;
		
		n = new Node("falsehero_exposed", NodeType.EVENT);
		C.addNode(n);
		C.setInitial(n);
		n = new Node("reward_withdrawn", NodeType.ACTION);
		C.addNode(n);
		C.addEdge("falsehero_exposed", "reward_withdrawn");
		n = new Node("falsehero_punished", NodeType.ACTION);
		C.addNode(n);
		C.addEdge("reward_withdrawn", "falsehero_punished");
		n = new Node("falsehero_killed", NodeType.ACTION);
		C.addNode(n);
		n = new Node("falsehero_exiled", NodeType.ACTION);
		C.addNode(n);
		n = new Node("falsehero_imprisoned", NodeType.ACTION);
		C.addNode(n);
		C.addEdge("falsehero_punished", "falsehero_killed");
		C.addEdge("falsehero_punished", "falsehero_exiled");
		C.addEdge("falsehero_punished", "falsehero_imprisoned");
		
		C.serialize();
	}

}
