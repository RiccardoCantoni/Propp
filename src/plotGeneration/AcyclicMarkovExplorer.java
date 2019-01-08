/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plotGeneration;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import myUtils.ListUtils;
import myUtils.LogManager;
import propp.Configuration;
import proppFunction.ProppFunction;
import proppFunction.GraphExplorationException;
import proppFunction.MarkovTransition;
import proppFunction.Node;
import proppFunction.NodeTree;
import proppFunction.NodeType;
import state.State;

/**
 *
 * @author Riccardo
 */
public class AcyclicMarkovExplorer {
    
    ProppFunction graph;
    State state;
    NodeTree tree;
    MarkovTransition transition; 
    boolean globalFrequency;

    public AcyclicMarkovExplorer() {
    	this.globalFrequency = Configuration.getInstance().globalFrequencyActive;
    }
    
    public List<Node> explorationPath(ProppFunction graph, State initialState, MarkovTransition transition, String[] injections){
        this.graph = graph;
        this.state = initialState;
        injectAll(injections);
        this.transition = transition;
        List<Node> reversePath = new LinkedList<Node>(); 
        int maxAttempts = 20;
        for (int i=0; i<maxAttempts; i++) {
        	reversePath = exploreChain();
	        if (NodeSequenceManager.containsInjections(reversePath, injections)) {
	        	for (Node n : reversePath) {
	            	FrequencyDB.getInstance().updateLocalFrequency(n);
	            }
	        	if (globalFrequency) {
	        		for (Node n : reversePath) {
	        			FrequencyDB.getInstance().updateGlobalFrequency(n);
		            }
	        	}
	        	Collections.reverse(reversePath);
	        	return reversePath;
	        }
        }
        throw new GraphExplorationException("injections failed at chain "+ graph.FunctionName, injections, state, reversePath);      
    }
    
    public List<Node> explorationPath(ProppFunction graph, State initialState, MarkovTransition transition){
        this.graph = graph;
        this.state = initialState;
        this.transition = transition;
        List<Node> reversePath = exploreChain();
        updateLocalFrequency(reversePath);
        FrequencyDB fdb = FrequencyDB.getInstance();
        for (Node n : reversePath) {
        	fdb.updateLocalFrequency(n);
        }
        if (globalFrequency) {
    		for (Node n : reversePath) {
            	fdb.updateGlobalFrequency(n);
            }
    	}
        Collections.reverse(reversePath);
        return reversePath;
    }
    
    private List<Node> exploreChain(){
        Node currentNode=null;
        Node currentParent;
        List<Node> fringe= new LinkedList<>();
        List<Node> successors;
        tree = null;
        while(true){
            if (currentNode == null){
                tree = new NodeTree(graph.entryPoint);
                currentNode = graph.entryPoint;
            }
            if (explorationTerminated(currentNode)){
                break;
            }
            successors = getValidSuccessors(currentNode);
            if (!successors.isEmpty()){
                currentParent = currentNode;
                currentNode = transition.nextNode(successors);
                successors.remove(currentNode);
                for (Node succ : successors){
                    tree.addLeaf(succ, currentParent);
                }
                fringe.addAll(successors);
                tree.addLeaf(currentNode, currentParent);
            }else{//dead end, backtrack
                if (fringe.isEmpty()){
                    LogManager.addEntry("backtracking failed:");
                    LogManager.addEntry("node: "+currentNode.label);
                    LogManager.addEntry(state.toString());
                    throw new GraphExplorationException(
                    		"backtracking failed after node '" + currentNode.label +"' of chain "+graph.FunctionName);
                }
                currentNode = fringe.get(0);                
                fringe.remove(0);
            }
        }
        return tree.pathToRoot(currentNode, graph);
    }
    
    private boolean explorationTerminated(Node n){
        return n.isFinal() && state.isValidNode(n);
    }
    
    private List<Node> getValidSuccessors(Node n){
        List<Node> successors = new LinkedList<>();
        for (Node s : n.successors){
            if (state.isValidNode(s)){
                successors.add(s);
            }
        }
        return successors;
    }
    
    private void injectAll(String[] injections) {
    	for (String i : injections) {
    		state.inject(i);
    	}
    }
    
    private void updateLocalFrequency(List<Node> nodes) {
    	for (Node n:nodes) {
    		FrequencyDB.getInstance().updateLocalFrequency(n);
    	}
    }
    
}
