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
        n.toAdd.addPredicate(new Predicate("dead","$villain"));
        n.toRemove.addPredicate(new Predicate("lack","kill","$villain"));
        n.preconditions = new AtomMatcher(new Predicate("lack","kill","$villain"));
        C.addNode(n);
        C.addEdge("struggle_outcome_positive","villain_slain");
        n=new Node("villain_prisoner", NodeType.OUTCOME);
        n.toAdd.addPredicate(new Predicate("prisoner","$villain", "$hero"));
        n.toRemove.addPredicate(new Predicate("lack","imprison","$villain"));
        n.preconditions = new AtomMatcher(new Predicate("lack","imprison","$villain"));
        C.addNode(n);
        C.addEdge("struggle_outcome_positive","villain_prisoner");
        
        C.serializeAs("H");
    }
    
}
