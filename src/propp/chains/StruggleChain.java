/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp.chains;

import proppFunction.FunctionChain;
import proppFunction.Node;
import proppFunction.NodeType;
import state.Predicate;

/**
 *
 * @author Riccardo
 */
public class StruggleChain implements ChainGenerator{

    @Override
    public void createSave() {
        FunctionChain C = new FunctionChain();
        C.FunctionName = "Struggle";
        Node n;
        n=new Node("fight", NodeType.ACTION);
        C.addNode(n);
        C.setInitial(n);
        n=new Node("contest", NodeType.ACTION);
        C.addNode(n);
        C.setInitial(n);
        n=new Node("aid_used", NodeType.ACTION);
        C.addNode(n);
        n=new Node("aid_unavailable", NodeType.EVENT);
        C.addNode(n);
        n=new Node("struggle_outcome_positive", NodeType.OUTCOME);
        n.toAdd.addPredicate(new Predicate("struggle_outcome","positive","_"));
        C.addNode(n);
        n=new Node("struggle_outcome_negative", NodeType.OUTCOME);
        n.toAdd.addPredicate(new Predicate("struggle_outcome","negative","_"));
        C.addNode(n);
        
        C.addEdge("fight","struggle_outcome_positive");
        C.addEdge("contest","struggle_outcome_positive");
        C.addEdge("fight","struggle_outcome_negative");
        C.addEdge("contest","struggle_outcome_negative");
        
        C.addEdge("fight","aid_available");
        C.addEdge("contest","aid_available");
        C.addEdge("fight","aid_unavailable");
        C.addEdge("contest","aid_unavailable");
        
        C.addEdge("aid_unavailable","struggle_outcome_negative");
        C.addEdge("aid_available","struggle_outcome_positive");
        
        C.serialize();
    }
    
}
