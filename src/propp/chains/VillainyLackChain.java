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
public class VillainyLackChain implements ChainGenerator{
    
    public void createSave(){ 
    FunctionChain C = new FunctionChain();
        C.FunctionName = "VillainyLack";
        
        String lHelper = "lack_helper";
        String lItem = "lack_item";
        String lVengeance = "lack_vengeance";
        String lWealth = "lack_wealth";
        String lFreedom = "lack_freedom";
        
        Node n;
        n = new Node(lHelper, NodeType.EVENT);
        C.addNode(n);
        n.toAdd.addPredicate(new Predicate("lack","helper"));
        n = new Node(lItem, NodeType.EVENT);
        n.toAdd.addPredicate(new Predicate("lack","item"));
        C.addNode(n);
        n = new Node(lVengeance, NodeType.EVENT);
        n.toAdd.addPredicate(new Predicate("lack","vengeance"));
        C.addNode(n);
        n = new Node(lWealth, NodeType.EVENT);
        n.toAdd.addPredicate(new Predicate("lack","wealth"));
        C.addNode(n);
        n = new Node(lFreedom, NodeType.EVENT);
        n.toAdd.addPredicate(new Predicate("lack","freedom"));
        C.addNode(n);
        n = new Node("abduction", NodeType.ACTION);
        n.toAdd.addPredicate(new Predicate("villainy","abduction","$character"));
        C.addNode(n);
        C.setInitial(n);
        C.addEdge("abduction", lFreedom);
        n = new Node("seizure", NodeType.ACTION);
        n.toAdd.addPredicate(new Predicate("villainy","seizure","$item"));
        C.addNode(n);
        C.setInitial(n);
        C.addEdge("seizure", lItem);
        C.addEdge("seizure", lVengeance);
        n = new Node("pillaging", NodeType.ACTION);
        n.toAdd.addPredicate(new Predicate("villainy","pillaging","$item"));
        C.addNode(n);
        C.setInitial(n);
        C.addEdge("pillaging", lVengeance);
        C.addEdge("pillaging", lWealth);
        n = new Node("imprisonment", NodeType.ACTION);
        n.toAdd.addPredicate(new Predicate("villainy","imprisonment","$character"));
        C.addNode(n);
        C.setInitial(n);
        C.addEdge("imprisonment", lFreedom);
        C.addEdge("imprisonment", lItem);
        C.addEdge("imprisonment", lHelper);
        C.addEdge("imprisonment", lVengeance);
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
        C.addEdge("murder",lVengeance);
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
        C.addEdge("war",lHelper);
        C.addEdge("war",lItem);
        C.addEdge("war",lVengeance);
        
        C.serialize();
    }
    
}
