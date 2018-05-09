/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp.chains;

import graph.*;
import state.*;

/**
 *
 * @author Riccardo
 */
public class ChainG implements ChainGenerator{

    @Override
    public void createSave() {
        FunctionChain C = new FunctionChain();
        Node n;
        
        String a = "arrival"; 
        n = new Node(a, NodeType.EVENT);
        n.toRemove.addPredicate(new Predicate("location","$hero","_"));
        n.toAdd.addPredicate(new Predicate("location","$hero","$location"));
        C.addNode(n);
        
        
        C.serializeAs("G");
    }
    
}
