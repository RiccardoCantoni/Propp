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
public class ChainAa implements ChainGenerator{
    
    public void createSave(){ 
    FunctionChain C = new FunctionChain();
        C.FunctionName = "Aa";
        String lhe = "lack_helper";
        String lhu = "lack_human";
        String lo = "lack_object";
        String lw = "lack_wealth";
        String lb = "lack_bride";
        String vk = "vengeance_kill";
        String vi = "vengeance_imprison";
        Node n = new Node("lhe", NodeType.EVENT);
        C.addNode(n);
        n.toAdd.addPredicate(new Predicate("lack","helper"));
        n = new Node(lhu, NodeType.EVENT);
        n.toAdd.addPredicate(new Predicate("lack","human"));
        C.addNode(n);
        n = new Node(lo, NodeType.EVENT);
        n.toAdd.addPredicate(new Predicate("lack","object"));
        C.addNode(n);
        n = new Node(lw, NodeType.EVENT);
        n.toAdd.addPredicate(new Predicate("lack","wealth"));
        C.addNode(n);
        C.setInitial(n);
        n = new Node(lb, NodeType.EVENT);
        n.toAdd.addPredicate(new Predicate("lack","bride"));
        C.addNode(n);
        C.setInitial(n);
        n = new Node(vk, NodeType.EVENT);
        n.toAdd.addPredicate(new Predicate("lack","kill","$villain"));
        C.addNode(n);
        n = new Node(vi, NodeType.EVENT);
        n.toAdd.addPredicate(new Predicate("lack","imprison","$villain"));
        C.addNode(n);
        n = new Node("abduction", NodeType.ACTION);
        n.toAdd.addPredicate(new Predicate("villainy","abduction","$character"));
        C.addNode(n);
        C.setInitial(n);
        C.addEdge("abduction", lhu);
        n = new Node("seizure", NodeType.ACTION);
        n.toAdd.addPredicate(new Predicate("villainy","seizure","$item"));
        C.addNode(n);
        C.setInitial(n);
        C.addEdge("seizure", lo);
        n = new Node("pillaging", NodeType.ACTION);
        n.toAdd.addPredicate(new Predicate("villainy","pillaging","$item"));
        C.addNode(n);
        C.setInitial(n);
        C.addEdge("pillaging", vk);
        C.addEdge("pillaging", vi);
        C.addEdge("pillaging", lw);
        C.addEdge("pillaging", lo);
        System.out.println();
        n = new Node("imprisonment", NodeType.ACTION);
        n.toAdd.addPredicate(new Predicate("villainy","imprisonment","$character"));
        C.addNode(n);
        C.setInitial(n);
        C.addEdge("imprisonment", vk);
        C.addEdge("imprisonment", vi);
        C.addEdge("imprisonment", lhu);
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
        C.addEdge("murder",vk);
        C.addEdge("murder", vi);
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
        C.addEdge("war",lhe);
        C.addEdge("war",lo);
        C.addEdge("war",vk);
        C.addEdge("war",vi);
        
        C.serializeAs("Aa");
    }
    
}
