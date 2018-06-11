/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plotGeneration;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import myUtils.LogManager;
import propp.NodeSequenceManager;
import proppFunction.FunctionChain;
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
    
    FunctionChain graph;
    State state;
    NodeTree tree;
    MarkovTransition transition;    
    
    public List<Node> explorationPath(FunctionChain graph, State initialState, MarkovTransition transition, List<String>injections){
        this.graph = graph;
        this.state = initialState;
        this.transition = transition;
        List<Node> reversePath = new LinkedList<Node>(); 
        int maxAttempts = 20, i=0;
        while (i<maxAttempts && NodeSequenceManager.containsInjections(reversePath, injections)) {
        	reversePath = exploreChain();
        	i++;
        }
        Collections.reverse(reversePath);
        return reversePath;
    }
    
    public List<Node> explorationPath(FunctionChain graph, State initialState, MarkovTransition transition){
        this.graph = graph;
        this.state = initialState;
        this.transition = transition;
        List<Node> reversePath = exploreChain();
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
                    throw new GraphExplorationException("backtracking failed after node '" + currentNode.label +"' of chain "+graph.FunctionName);
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
    
}
