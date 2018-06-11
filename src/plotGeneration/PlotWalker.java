/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plotGeneration;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;

import myUtils.JsonDataManager;
import propp.SystemState;
import proppFunction.FunctionChain;
import proppFunction.MarkovTransition;
import proppFunction.Node;
import state.State;

/**
 *
 * @author Riccardo
 */
public class PlotWalker implements Iterator<Node>{
    
    FunctionChain currentChain;
    MarkovTransition transition;
    
    State state;
    FunctionChain[] chainSequence;
    int chainIndex;
    Node n;
    
    WalkerSingle walkerSingle;
    List<String> injections;
    
    public PlotWalker(PlotArgument arg){
        this.chainSequence = arg.chainSequence;
        this.transition = SystemState.getInstance().transition_function;
        chainIndex = 0;
        state = arg.initialState;
        currentChain = chainSequence[chainIndex];
        injections = Arrays.asList(arg.injections);
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
            walkerSingle = new WalkerSingle(currentChain, transition, state, injections);
        }
        n = walkerSingle.next();
        if (n.label.equals("$entry_point"))
        	n.label = "$"+currentChain.FunctionName;
        return n;
    }
    
    
}
