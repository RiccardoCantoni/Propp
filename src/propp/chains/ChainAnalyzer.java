package propp.chains;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import plotGeneration.NodeSequenceManager;
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
	
	public String[] resolvableInjections(String[] injections) {
		List<String> labels = NodeSequenceManager.getLabelSequence(chain.nodes);
		List<String> ls = Arrays.asList(injections)
				.stream()
				.filter(x->labels.contains(x))
				.collect(Collectors.toList());
		return ls.toArray(new String[ls.size()]);
	}
	
}
