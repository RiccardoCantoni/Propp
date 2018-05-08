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
public class ChainF implements ChainGenerator{

    @Override
    public void createSave() {
        
        FunctionChain C = new FunctionChain();
        Node n;
        
        n = new Node("item_preparation", NodeType.ACTION);
        C.addNode(n);
        C.setInitial(n);
        String t = "item_indication";
        n = new Node(t, NodeType.ACTION);
        C.addNode(n);
        C.setInitial(n);
        C.addEdge("item_preparation",t);
        n = new Node("item_indication_ack", NodeType.PI);
        n.preconditions = new AtomMatcher(new Predicate("donor_test","success"));
        C.addNode(n);
        C.addEdge(t,"item_indication_ack");
        String a = "item_acquisition";
        n = new Node(a, NodeType.ACTION);
        n.toAdd.addPredicate(new Predicate("acquisition", "$hero", "$item"));
        C.addNode(n);
        C.addEdge("item_indication_ack",a);
        n = new Node("item_delivery", NodeType.ACTION);
        C.addNode(n);
        C.setInitial(n);
        C.addEdge("item_delivery",a);
        n = new Node("item_discovery", NodeType.PERCEPTION);
        n.preconditions = new NotMatcher(new AtomMatcher(new Predicate("donor_test","success")));
        C.addNode(n);
        C.setInitial(n);
        n = new Node("item_desire", NodeType.INTERNAL);
        C.addNode(n);
        C.addEdge("item_discovery","item_desire");
        C.addEdge("item_desire",a);
        n = new Node("item_seizure", NodeType.ACTION);
        C.addNode(n);
        C.addEdge("item_desire","item_seizure");
        C.addEdge("item_seizure",a);
        n = new Node("donor_servitude", NodeType.EVENT);
        n.toAdd.addPredicate(new Predicate("servitude","$donor","$hero"));
        n.preconditions = new AtomMatcher(new Predicate("donor_test","success"));
        C.addNode(n);
        C.addEdge("donor_servitude",a);
        
        
        C.serializeAs("F");
        
    }
    
}
