/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp;

import java.util.LinkedList;
import java.util.List;

import myUtils.ListUtil;
import propp.chains.ChainUpdater;
import proppFunction.AleatoryTransition;
import proppFunction.FunctionChain;
import proppFunction.Node;
import proppFunction.SharedRandom;

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
        WalkerMultiple wk = new WalkerMultiple(WalkerMultiple.defaultSequence(), new AleatoryTransition());
        List<Node> ls = new LinkedList<>();
        while(wk.hasNext()) {
        	ls.add(wk.next());
        }
        ListUtil.printList(NodeSequenceManager.getLabelSequence(ls), true);
        
    }
    private static List<Node> walkSingleChain(String chain) {
    	FunctionChain [] ch = new FunctionChain [] {
    		FunctionChain.deserializeFrom(chain)
    	};
    	WalkerMultiple walker = new WalkerMultiple(ch, new AleatoryTransition());
        List<Node> nodeSequence = new LinkedList<>();
        while(walker.hasNext()){
            nodeSequence.add(walker.next());
        }
        return nodeSequence;
    }
    
    public static List<Node> walkAll() {
        WalkerMultiple walker = new WalkerMultiple(WalkerMultiple.defaultSequence(), new AleatoryTransition());
        List<Node> nodeSequence = new LinkedList<>();
        while(walker.hasNext()){
            nodeSequence.add(walker.next());
        }
        return nodeSequence;
    }
    
}
