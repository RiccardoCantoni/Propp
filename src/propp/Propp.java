/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp;

import graph.*;
import java.util.LinkedList;
import java.util.List;
import myUtils.LogManager;
import propp.chains.*;
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
        SharedRandom srand = SharedRandom.getInstance();
        srand.setRandom();
        WalkerMultiple walker = new WalkerMultiple(WalkerMultiple.defaultSequence(), new AleatoryTransition());
        List<Node> story = new LinkedList<>();
        while(walker.hasNext()){
            story.add(walker.next());
        }
        story = NodeSequenceManager.clearLabelSequence(story);
        for (Node n:story){
            System.out.println(n.label);
        }
        
    }
    
}
