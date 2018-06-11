/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp;

import java.util.LinkedList;
import java.util.List;

import myUtils.ListUtil;
import plotGeneration.PlotArgument;
import plotGeneration.PlotWalker;
import propp.chains.ChainUpdater;
import proppFunction.AleatoryTransition;
import proppFunction.Node;
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
        List<Node> ls = walk();
        ListUtil.printList(NodeSequenceManager.getLabelSequence(ls), true);
    	
    }
    
    public static List<Node> walk() {
    	String[] seq = new String[] {
    			"ReconDelivery",
    			"Villainy",
    			"Lack",
    			"MediationCounteraction",
    			"FirstFunctionReaction",
    			"Acquisition",
    			"Guidance",
    			"Struggle",
    			"Branding",
    			"Liquidation",
    			"Return",
    			"FalseClaimsFalseReward",
    			"Recognition",
    			"ExposurePunishment",
    			"Reward"
    			};
        PlotArgument arg = new PlotArgument(seq, new State(), new AleatoryTransition(), new String[0]);
        PlotWalker walker = new PlotWalker(arg);
        List<Node> nodeSequence = new LinkedList<>();
        while(walker.hasNext()){
            nodeSequence.add(walker.next());
        }
        return nodeSequence;
    }
    
}
