/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp;

import state.*;
import graph.*;
import java.util.Iterator;

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
        FunctionChain[] seq = new FunctionChain[6];
        seq[0] = FunctionChain.deserializeFrom("Aa");
        seq[1] = FunctionChain.deserializeFrom("BC");
        seq[2] = FunctionChain.deserializeFrom("DE");
        seq[3] = FunctionChain.deserializeFrom("F");
        seq[4] = FunctionChain.deserializeFrom("G");
        seq[5] = FunctionChain.deserializeFrom("H");
        return seq;
    }
    
}
