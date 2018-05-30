/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp;

import state.*;
import java.util.*;

import proppFunction.*;

/**
 *
 * @author Riccardo
 */
public class WalkerSingle implements Iterator<Node>{
    
    FunctionChain chain;
    public Node currentNode;
    MarkovTransition transition;
    State state;
    List<Node> path;
    int pathIndex;
    
    public WalkerSingle(FunctionChain chain, MarkovTransition transition, State initialState){
        this.chain = chain;
        this.transition = transition;
        this.state = initialState;
        AcyclicMarkovExplorer explorer = new AcyclicMarkovExplorer();
        path = explorer.explorationPath(chain, initialState, transition);
        pathIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return (pathIndex<path.size());
    }

    @Override
    public Node next() {
        if (!this.hasNext())
            throw new NoSuchElementException("next() invoked when hasNext() is false");
        Node n = path.get(pathIndex);
        pathIndex++;
        state.update(n);
        return n;
    }
    
    public State getFinalState(){
        if (hasNext()){
            throw new IllegalStateException("the state is not final");
        }
        state.cleanInjections();
        return state;
    }
    
}
