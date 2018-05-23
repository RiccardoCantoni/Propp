/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp.chains;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import graph.FunctionChain;
import state.AtomMatcher;
import state.Predicate;
import state.PredicateMatcher;
import state.PredicateSet;

/**
 *
 * @author Riccardo
 */
public class ChainUpdater {
    
    public static void updateAllChains(){
        ChainGenerator cg;
        cg = new ChainAa();
        cg.createSave();
        cg = new ChainBC();
        cg.createSave();
        cg = new ChainDE();
        cg.createSave();
        cg = new ChainF();
        cg.createSave();
        cg = new ChainG();
        cg.createSave();
        cg = new ChainHJ();
        cg.createSave();
        cg = new TestChainSmall();
        cg.createSave();
        System.out.println("all chains updated successfully");
        //checkPredicateConsistency();
    }
    
    private static void checkPredicateConsistency() {
    	ChainAnalyzer ca;
    	PredicateMatcher m;
    	PredicateSet added = new PredicateSet();
    	List<Predicate> required = new LinkedList<>();
    	List<FunctionChain> chainSeq = Arrays.asList(defaultSequence());
    	for (FunctionChain c : chainSeq) {
    		ca = new ChainAnalyzer(c);
    		for (Predicate p : ca.getAddedPredicates()) {
    			added.addPredicate(p);
    		}
    		required.addAll(ca.getRequiredPredicates());
    	}
    	for (Predicate req : required) {
    		m = new AtomMatcher(req);
    		if (!m.matchAny(added)) { 
    			throw new IllegalArgumentException("precondition contains unknown predicate: "+req.toString());
    		}
    	}
    }
    
    private static FunctionChain[] defaultSequence(){
        FunctionChain[] seq = new FunctionChain[6];
        seq[0] = FunctionChain.deserializeFrom("Aa");
        seq[1] = FunctionChain.deserializeFrom("BC");
        seq[2] = FunctionChain.deserializeFrom("DE");
        seq[3] = FunctionChain.deserializeFrom("F");
        seq[4] = FunctionChain.deserializeFrom("G");
        seq[5] = FunctionChain.deserializeFrom("HJ");
        return seq;
    }
    
}
