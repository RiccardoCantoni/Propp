/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp.chains;

import proppFunction.*;
import state.*;

/**
 *
 * @author Riccardo
 */
public class VillainyChain implements ChainGenerator{
    
    public void createSave(){ 
    FunctionChain C = new FunctionChain();
        C.FunctionName = "Villainy";        
        Node n;
        
        n = new Node("abduction", NodeType.ACTION);
        n.toAdd.addPredicate(new Predicate("villainy","abduction","$character"));
        C.addNode(n);
        C.setInitial(n);
        n = new Node("seizure", NodeType.ACTION);
        n.toAdd.addPredicate(new Predicate("villainy","seizure","$item"));
        C.addNode(n);
        C.setInitial(n);
        n = new Node("pillaging", NodeType.ACTION);
        n.toAdd.addPredicate(new Predicate("villainy","pillaging","$item"));
        C.addNode(n);
        C.setInitial(n);
        n = new Node("imprisonment", NodeType.ACTION);
        n.toAdd.addPredicate(new Predicate("villainy","imprisonment","$character"));
        C.addNode(n);
        C.setInitial(n);
        n = new Node("demand_imprisonment", NodeType.ACTION );
        C.addNode(n);
        C.setInitial(n);
        n = C.addEdge("demand_imprisonment","ack_demand_imprisonment");
        n.type = NodeType.PI;
        n = C.addEdge("ack_demand_imprisonment", "imprisonment");
        n.type = NodeType.ACTION;
        n = new Node("murder", NodeType.ACTION);
        n.toAdd.addPredicate(new Predicate("villainy","murder","$character"));
        C.addNode(n);
        C.setInitial(n);
        n = new Node("demand_murder", NodeType.ACTION);
        C.addNode(n);
        C.setInitial(n);
        n = C.addEdge("demand_murder","ack_demand_murder");
        n.type = NodeType.PI;
        n = C.addEdge("ack_demand_murder","murder");
        n.type = NodeType.ACTION;
        n = new Node("war_declaration", NodeType.ACTION);
        C.addNode(n);
        C.setInitial(n);
        n = C.addEdge("war_declaration", "war");
        n.type = NodeType.ACTION;
        n.toAdd.addPredicate(new Predicate("villainy","war"));
        
        C.serialize();
    }
    
}
