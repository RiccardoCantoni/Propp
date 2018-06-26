/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proppFunction;

import java.util.Arrays;
import java.util.List;

import myUtils.ListUtil;
import myUtils.LogManager;
import propp.NodeSequenceManager;
import propp.SystemState;
import state.State;

/**
 *
 * @author Riccardo
 */
public class GraphExplorationException extends RuntimeException{
	
	public GraphExplorationException(String message) {
		super(message);
	}
	      
    public GraphExplorationException(String message, String[] injections, State state, List<Node> sequence) {
    	super(message);
    	if (SystemState.getInstance().loggingMode) {
    		LogManager.addEntry("GRAPH_EXPLORATION_EXCEPTION:");
    		LogManager.addEntry(message);
    		LogManager.addEntry("state: "+ state.toString());
    		LogManager.addEntry("sequence: "+ ListUtil.listToString(NodeSequenceManager.getLabelSequence(sequence)));
    		LogManager.addEntry("injections: "+ListUtil.listToString(Arrays.asList(injections)));
    	}
    }

    
}
