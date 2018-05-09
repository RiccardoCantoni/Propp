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
public class ChainH implements ChainGenerator{

    @Override
    public void createSave() {
        FunctionChain C = new FunctionChain();
        Node n;
        
        n=new Node("fight", NodeType.ACTION);
        C.addNode(n);
        C.setInitial(n);
        n=new Node("contest", NodeType.ACTION);
        C.addNode(n);
        C.setInitial(n);
        n=new Node("struggle_outcome_positive", NodeType.OUTCOME);
        C.addNode(n);
        C.addEdge("fight","struggle_outcome_positive");
        C.addEdge("contest","struggle_outcome_positive");
        n=new Node("villain_slain", NodeType.OUTCOME);
        n.preconditions = new AtomMatcher(new Predicate("lack","vengeance","kill"));
        C.addNode(n);
        C.addEdge("struggle_outcome_positive","villain_slain");
        n=new Node("villain_prisoner", NodeType.OUTCOME);
        n.preconditions = new AtomMatcher(new Predicate("lack","vengeance","imprison"));
        C.addNode(n);
        C.addEdge("struggle_outcome_positive","villain_prisoner");
        
        
        C.serializeAs("H");
    }
    
}
