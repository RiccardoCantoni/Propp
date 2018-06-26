/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp;

import java.util.LinkedList;
import java.util.List;

import myUtils.ListUtil;
import plotGeneration.KnownSequence;
import plotGeneration.MultiPlotGenerator;
import plotGeneration.PlotArgument;
import propp.chains.ChainUpdater;
import proppFunction.Node;
import proppFunction.NodeType;
import proppFunction.SharedRandom;
import state.State;

/**
 *
 * @author Riccardo
 */
public class Propp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
    	ChainUpdater.updateAllChains();
        SharedRandom.getInstance().setRandom();
        List<Node> ls = plotContaining("recon_outcome_failure");
        ListUtil.printList(NodeSequenceManager.getLabelSequence(ls), true);
    }
    
    public static List<Node> walk() {   
        PlotArgument arg = new PlotArgument(KnownSequence.MAIN_SEQUENCE.getSequence(), new State(), new String[0]);
        MultiPlotGenerator multigen = new MultiPlotGenerator(arg, 0);
        List<Node> plot = multigen.generate();
        return plot;        
    }
    
    public static List<Node> plotContaining(String s){
    	List<Node> ls = new LinkedList<Node>();
    	while(true) {
    		ls = walk();
    		if (NodeSequenceManager.getLabelSequence(ls).contains(s)) return ls;
    	}
    }
    
}
