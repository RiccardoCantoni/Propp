package propp.chains;

import java.util.LinkedList;
import java.util.List;

import proppFunction.FunctionChain;
import proppFunction.Node;
import state.Predicate;

public class ChainAnalyzer {
	
	FunctionChain chain;
	
	public ChainAnalyzer(FunctionChain c) {
		this.chain = c;
	}
	
	public ChainAnalyzer(String chainFile) {
		this.chain = FunctionChain.deserializeFrom(chainFile);
	}
	
	public List<Predicate> getAddedPredicates() {
		List<Predicate> preds = new LinkedList<>();
		for (Node n : chain.nodes) {
			preds.addAll(n.toAdd.set);
		}
		return preds;
	}
	
	public List<Predicate> getRequiredPredicates() {
		List<Predicate> preds = new LinkedList<>();
		for (Node n : chain.nodes) {
			preds.addAll(n.preconditions.requiredPredicates());
		}
		return preds;
	}
	
	public List<String> getLabels(){
		List<String> labels = new LinkedList<>();
		for (Node n : chain.nodes) {
			labels.add(n.label);
		}
		return labels;
	}
	
}
