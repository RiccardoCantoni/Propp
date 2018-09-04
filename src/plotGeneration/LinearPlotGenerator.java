/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plotGeneration;

import java.util.LinkedList;
import java.util.List;

import propp.SystemState;
import propp.chains.ChainAnalyzer;
import proppFunction.FunctionChain;
import proppFunction.MarkovTransition;
import proppFunction.Node;
import state.State;

/**
 *
 * @author Riccardo
 */
public class LinearPlotGenerator{
	
	PlotArgument arg;
    
    FunctionChain currentChain;
    MarkovTransition transition;
    
    PlotGenerationState generationState;
    State state;
    FunctionChain[] chainSequence;
    int chainIndex;
    Node n;
    List<Node> plot;
    
    
    WalkerSingle walkerSingle;
    String[] injections;
    
    public LinearPlotGenerator(PlotArgument arg){
    	this.arg = arg;
    }
    
    public void generate() {
    	resetGeneration(arg);
    	resumeGeneration();
    }
    
    public void resumeGeneration() {
    	generationState=PlotGenerationState.RUNNING;
    	while (generationState==PlotGenerationState.RUNNING) {
    		generationLoop();
    	}   	
    }
    
    public void resetGeneration(PlotArgument arg) {
    	plot = new LinkedList<Node>();
    	generationState = PlotGenerationState.EARLY;
        this.chainSequence = arg.chainSequence;
        this.transition = SystemState.getInstance().transition_function;
        chainIndex = 0;
        state = arg.initialState;
        currentChain = chainSequence[chainIndex];
        this.injections = arg.injections;
        String[] resolvableInjections = new ChainAnalyzer(currentChain).resolvableInjections(injections); 
        if (resolvableInjections.length>0) {
        	walkerSingle = new WalkerSingle(currentChain, transition, state, resolvableInjections);
        }else {
        	walkerSingle = new WalkerSingle(currentChain, transition, state);
        }
    }
    
    void generationLoop() {
    	if (this.hasNextNode()) {
    		plot.add(next());
    		generationState = PlotGenerationState.RUNNING;
    		return;
    	}
    	if (this.hasNextFunction()) {
    		generationState = PlotGenerationState.FUNCTION_TERMINATED;
    		return;
    	}
    	generationState = PlotGenerationState.COMPLETED;
    }
    
    public List<Node> getPlot() {
    	if (this.generationState == PlotGenerationState.RUNNING)
    		throw new PlotGenerationException("retrieving plot when the generator is in RUNNING state");
    	return plot;
    }
    
    public State getState() {
    	if (this.generationState == PlotGenerationState.RUNNING)
    		throw new PlotGenerationException("retrieving state when the generator is in RUNNING state");
    	return state;
    }
    
    public PlotGenerationState getGenerationState() {
    	return this.generationState;
    }
 
    boolean hasNextNode() {
        return walkerSingle.hasNext();
    }

    boolean hasNextFunction() {
    	return (chainIndex<chainSequence.length-1);
    }
    
    Node next() {
        n = walkerSingle.next();
        if (n.label.equals("$entry_point"))
        	n.label = "&"+currentChain.FunctionName;
        return n;
    }
    
    public void nextFunction() {    
    	state = walkerSingle.getFinalState();
        plot = new LinkedList<Node>();
        chainIndex++;
        currentChain = chainSequence[chainIndex];
        String[] resolvableInjections = new ChainAnalyzer(currentChain).resolvableInjections(injections);
        if (resolvableInjections.length>0) {
        	walkerSingle = new WalkerSingle(currentChain, transition, state, resolvableInjections);
        }else {
        	walkerSingle = new WalkerSingle(currentChain, transition, state);
        }
    }
    
}
