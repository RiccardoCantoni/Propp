/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;

import myUtils.JsonDataManager;
import proppFunction.FunctionChain;
import proppFunction.MarkovTransition;
import proppFunction.Node;
import state.State;

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
    	List<FunctionChain> seq = new LinkedList<>();
    	JsonDataManager jdm = new JsonDataManager("data.json");
    	JsonArray chains = jdm.loadArray("chains");
    	for (JsonObject o : chains.getValuesAs(JsonObject.class)) {
    		seq.add(FunctionChain.deserializeFrom(o.getString("name")));
    	}
    	return seq.toArray(new FunctionChain[seq.size()]);
    }
    
}
