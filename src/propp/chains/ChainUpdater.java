/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp.chains;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import proppFunction.FunctionChain;
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
        new VillainyChain().createSave();
        new LackChain().createSave();
        new MediationCounteractionChain().createSave();
        new FirstFunctionReactionChain().createSave();
        new AcquisitionChain().createSave();
        new GuidanceChain().createSave();
        new StruggleBrandingChain().createSave();
        new LiquidationChain().createSave();
        new ReconDeliveryChain().createSave();
        new ReturnChain().createSave();
        ChainGenerator cg = new RewardChain();
        cg.createSave();
        new RewardChain().createSave();
        
        new TestChainSmall().createSave();
        
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
    	throw new UnsupportedOperationException("legacy method needs to be reimplemented");
        /*FunctionChain[] seq = new FunctionChain[6];
        seq[0] = FunctionChain.deserializeFrom("Aa");
        seq[1] = FunctionChain.deserializeFrom("BC");
        seq[2] = FunctionChain.deserializeFrom("DE");
        seq[3] = FunctionChain.deserializeFrom("F");
        seq[4] = FunctionChain.deserializeFrom("G");
        seq[5] = FunctionChain.deserializeFrom("HJ");
        return seq;*/
    }
    
}
