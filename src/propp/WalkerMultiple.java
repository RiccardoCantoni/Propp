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
    Node currentNode;
    State state;
    MarkovTransition transition;
    
    FunctionChain[] chainSequence;
    int chainIndex;
    
    WalkerSingle walkerSingle;
    
    /*
    public WalkerMultiple(){
        chainSequence = new FunctionChain[6];
        chainIndex = 0;
        chainSequence[0] = FunctionChain.deserializeFrom("Aa");
        chainSequence[1] = FunctionChain.deserializeFrom("BC");
        chainSequence[2] = FunctionChain.deserializeFrom("DE");
        chainSequence[3] = FunctionChain.deserializeFrom("F");
        chainSequence[4] = FunctionChain.deserializeFrom("G");
        chainSequence[5] = FunctionChain.deserializeFrom("H");
        currentChain = chainSequence[0];
        state = new State();
        transition = new AleatoryTransition();
        walkerSingle = new WalkerSingle(currentChain, transition, state);
    }
*/
    
    public WalkerMultiple(FunctionChain[] chainSequence, MarkovTransition transitionFunction){
        this.chainSequence = chainSequence;
        this.transition = transitionFunction;
        
    }

    @Override
    public boolean hasNext() {
        return (walkerSingle.hasNext() || chainIndex<5);
    }

    @Override
    public Node next() {
        if (!walkerSingle.hasNext()){
            state = walkerSingle.getFinalState();
            Node n = walkerSingle.currentNode;
            
            chainIndex++;
            currentChain = chainSequence[chainIndex];
            walkerSingle = new WalkerSingle(currentChain, transition, state);
        }
        return walkerSingle.next();
    }
    
}
