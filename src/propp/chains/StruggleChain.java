/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp.chains;

import proppFunction.ProppFunction;
import proppFunction.Node;
import proppFunction.NodeType;
import state.AtomMatcher;
import state.Predicate;

/**
 *
 * @author Riccardo
 */
public class StruggleChain implements FunctionGenerator{

    @Override
    public void createSave() {
        ProppFunction C = new ProppFunction("Struggle");
        Node n;
        n=new Node("fight", NodeType.ACTION);
        C.addNode(n);
        C.setInitial(n);
        n=new Node("contest", NodeType.ACTION);
        C.addNode(n);
        C.setInitial(n);
        n=new Node("aid_helper_used", NodeType.ACTION);
        n.preconditions = new AtomMatcher(new Predicate("helper_acquired","_","_"));
        C.addNode(n);
        n=new Node("aid_item_used", NodeType.ACTION);
        n.preconditions = new AtomMatcher(new Predicate("item_acquired","_","_"));
        C.addNode(n);
        n=new Node("aid_helper_unavailable", NodeType.EVENT);
        C.addNode(n);
        n=new Node("aid_item_unavailable", NodeType.EVENT);
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
        
        C.addEdge("fight","aid_helper_available");
        C.addEdge("fight","aid_item_available");
        C.addEdge("contest","aid_helper_available");
        C.addEdge("contest","aid_item_available");
        C.addEdge("fight","aid_helper_unavailable");
        C.addEdge("fight","aid_item_unavailable");
        C.addEdge("contest","aid_helper_unavailable");
        C.addEdge("contest","aid_item_unavailable");

        C.addEdge("aid_helper_unavailable","struggle_outcome_negative");
        C.addEdge("aid_item_unavailable","struggle_outcome_negative");
        C.addEdge("aid_helper_available","struggle_outcome_positive");
        C.addEdge("aid_item_available","struggle_outcome_positive");
        
        C.serialize();
    }
    
}
 