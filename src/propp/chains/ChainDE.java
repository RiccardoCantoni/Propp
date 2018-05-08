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
public class ChainDE implements ChainGenerator{

    public void createSave() {
        FunctionChain C = new FunctionChain();
        Node n; 
        
        String ta = "test_ack";
        n = new Node(ta, NodeType.PI);
        C.addNode(n);
        n = new Node("test_outcome_success", NodeType.ACTION);
        C.addNode(n);
        C.addEdge("test_ack","test_outcome_success");
        n = new Node("test_outcome_failure", NodeType.ACTION);
        C.addNode(n);
        C.addEdge("test_ack","test_outcome_failure");
        n = new Node("test_outcome_success_ack", NodeType.PI);
        n.toAdd.addPredicate(new Predicate("donor_test_outcome","success"));
        C.addNode(n);
        C.addEdge("test_outcome_success","test_outcome_success_ack");
        n = new Node("test_outcome_failure_ack", NodeType.PI);
        n.toAdd.addPredicate(new Predicate("donor_test_outcome","failure"));
        C.addNode(n);
        C.addEdge("test_outcome_failure","test_outcome_failure_ack");
        n = new Node("donor_test", NodeType.ACTION);
        n.toAdd.addPredicate(new Predicate("donor_test_type","test"));
        C.addNode(n);
        C.setInitial(n);
        C.addEdge("donor_test",ta);
        n = new Node("donor_interrogation", NodeType.ACTION);
        n.toAdd.addPredicate(new Predicate("donor_test_type","interrogation"));
        C.addNode(n);
        C.setInitial(n);
        C.addEdge("donor_interrogation",ta);
        n = new Node("prisoner_hero_ack", NodeType.PERCEPTION);
        n.toAdd.addPredicate(new Predicate("donor_test_type","free_prisoner"));
        C.addNode(n);
        C.setInitial(n);
        n = new Node("prisoner_desire_freedom", NodeType.INTERNAL);
        C.addNode(n);
        C.addEdge("prisoner_hero_ack","prisoner_desire_freedom");
        n = new Node("prisoner_request_freedom", NodeType.ACTION);
        C.addNode(n);
        C.addEdge("prisoner_desire_freedom", "prisoner_request_freedom");
        C.addEdge("prisoner_request_freedom", ta);
        n = new Node("hero_donor_ack", NodeType.PERCEPTION);
        n.toAdd.addPredicate(new Predicate("donor_test_type","release_captive"));
        C.addNode(n);
        C.setInitial(n);
        n = new Node("hero_desire_donor", NodeType.INTERNAL);
        C.addNode(n);
        C.addEdge("hero_donor_ack","hero_desire_donor");
        n = new Node("donor_capture", NodeType.ACTION);
        C.addNode(n);
        C.addEdge("hero_desire_donor","donor_capture");
        n = new Node("donor_begging", NodeType.INTERNAL);
        C.addNode(n);
        C.addEdge("donor_capture","donor_begging");
        C.addEdge("donor_begging",ta);
        n = new Node("donor_attack", NodeType.EVENT);
        n.toAdd.addPredicate(new Predicate("donor_test_type","attack"));
        C.addNode(n);
        C.setInitial(n);
        C.addEdge("donor_attack",ta);
        n = new Node("donor_display", NodeType.EVENT);
        n.toAdd.addPredicate(new Predicate("donor_test_type","exchange"));
        C.addNode(n);
        C.setInitial(n);
        n = new Node("donor_hero_ack", NodeType.PI);
        C.addNode(n);
        C.addEdge("donor_display","donor_hero_ack");
        n = new Node("exchange_offer", NodeType.ACTION);
        C.addNode(n);
        C.addEdge("donor_hero_ack","exchange_offer");
        C.addEdge("exchange_offer",ta);
        n = new Node("donor_need", NodeType.EVENT);
        n.toAdd.addPredicate(new Predicate("donor_test_type","service"));
        C.addNode(n);
        C.setInitial(n);
        C.addEdge("donor_need",ta);
        
        
        
        
        
        
        
        C.serializeAs("DE");
    }
    
}
