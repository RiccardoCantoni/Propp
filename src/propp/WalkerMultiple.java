/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp;

import state.*;

import java.util.Iterator;

import proppFunction.*;

/**
 *
 * @author Riccardo
 */
public class WalkerMultiple implements Iterator<Node>{
    
    FunctionChain currentChain;
    MarkovTransition transition;
    
    State state;
    FunctionChain[] chainSequence;
    int chainIndex;
    Node n;
    
    WalkerSingle walkerSingle;
    
    public WalkerMultiple(FunctionChain[] chainSequence, MarkovTransition transitionFunction){
        this.chainSequence = chainSequence;
        this.transition = transitionFunction;
        chainIndex = 0;
        state = new State();
        currentChain = chainSequence[chainIndex];
        walkerSingle = new WalkerSingle(currentChain, transition, state);
    }

    @Override
    public boolean hasNext() {
        return (walkerSingle.hasNext() || chainIndex<chainSequence.length-1);
    }

    @Override
    public Node next() {
        if (!walkerSingle.hasNext()){
            state = walkerSingle.getFinalState();
            chainIndex++;
            currentChain = chainSequence[chainIndex];
            walkerSingle = new WalkerSingle(currentChain, transition, state);
        }
        n = walkerSingle.next();
        if (n.label.equals("$entry_point"))
        	n.label = "$"+currentChain.FunctionName;
        return n;
    }
    
    public static FunctionChain[] defaultSequence(){
        FunctionChain[] seq = new FunctionChain[] {
        	FunctionChain.deserializeFrom("ReconDelivery"),
	        FunctionChain.deserializeFrom("Villainy"),
	        FunctionChain.deserializeFrom("Lack"),
	        FunctionChain.deserializeFrom("MediationCounteraction"),
	        FunctionChain.deserializeFrom("FirstFunctionReaction"),
	        FunctionChain.deserializeFrom("Acquisition"),
	        FunctionChain.deserializeFrom("Guidance"),
	        FunctionChain.deserializeFrom("StruggleBranding"),
	        FunctionChain.deserializeFrom("Return"),
	        FunctionChain.deserializeFrom("Liquidation"),
	        FunctionChain.deserializeFrom("Reward")	        
        };
        return seq;
    }
    
}
