/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import proppFunction.FunctionChain;
import proppFunction.MarkovTransition;
import proppFunction.Node;
import state.State;

/**
 *
 * @author Riccardo
 */
public class WalkerSingle implements Iterator<Node>{
    
    FunctionChain chain;
    MarkovTransition transition;
    
    public Node currentNode;
    State state;
    
    List<Node> path;
    int pathIndex;
    List<String> injections;
    
    public WalkerSingle(FunctionChain chain, MarkovTransition transition, State initialState, List<String> injections){
        this.chain = chain;
        this.transition = transition;
        this.state = initialState;
        AcyclicMarkovExplorer explorer = new AcyclicMarkovExplorer();
        path = explorer.explorationPath(chain, initialState, transition, injections);
        pathIndex = 0;
    }
    
    public WalkerSingle(FunctionChain chain, MarkovTransition transition, State initialState){
        this.chain = chain;
        this.transition = transition;
        this.state = initialState;
        AcyclicMarkovExplorer explorer = new AcyclicMarkovExplorer();
        path = explorer.explorationPath(chain, initialState, transition, new LinkedList<String>());
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
