/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp;

import graph.*;
import state.*;
import java.util.Iterator;

/**
 *
 * @author Riccardo
 */
public class WalkerSingle implements Iterator<Node>{
    
    FunctionChain chain;
    public Node currentNode;
    MarkovTransition transition;
    State state;
    
    public WalkerSingle(FunctionChain chain, MarkovTransition transition, State initialState){
        this.chain = chain;
        this.transition = transition;
        this.state = initialState;
        this.currentNode = chain.entryPoint;
    }

    @Override
    public boolean hasNext() {
        return (currentNode.outdegree()>0);
    }

    @Override
    public Node next() {
        Node n = transition.nextNode(currentNode);
        currentNode = n;
        state.update(n);
        return n;
    }
    
    public State getFinalState(){
        if (this.hasNext()){
            throw new IllegalStateException("the state is not final");
        }
        return this.state;
    }
    
}
