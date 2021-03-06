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
import state.NotMatcher;
import state.Predicate;

/**
 *
 * @author Riccardo
 */
public class AcquisitionChain implements FunctionGenerator{

    @Override
    public void createSave() {
        
        ProppFunction C = new ProppFunction("Acquisition");
        Node n;
        String ai = "item_acquisition";
        String ah = "helper_acquisition";
        String ag = "guidance_acquisition";
        n = new Node(ai, NodeType.ACTION);
        n.toAdd.addPredicate(new Predicate("acquisition_type", "item"));
        n.toAdd.addPredicate(new Predicate("item_acquired", "$item"));
        C.addNode(n);
        n = new Node(ah, NodeType.ACTION);
        n.toAdd.addPredicate(new Predicate("acquisition_type", "helper"));
        n.toAdd.addPredicate(new Predicate("helper", "$donor"));
        C.addNode(n);
        n = new Node(ag, NodeType.ACTION);
        n.toAdd.addPredicate(new Predicate("acquisition_type", "guidance"));
        C.addNode(n);
        
        n = new Node("item_preparation", NodeType.ACTION);
        n.preconditions = new AtomMatcher(new Predicate("donor_test_outcome","success"));
        C.addNode(n);
        C.setInitial(n);
        String t = "item_indication";
        n = new Node(t, NodeType.ACTION);
        C.addNode(n);
        C.setInitial(n);
        C.addEdge("item_preparation",t);
        n = new Node("item_indication_ack", NodeType.PERCEPTION_INTERNAL);
        n.preconditions = new AtomMatcher(new Predicate("donor_test","success"));
        C.addNode(n);
        C.addEdge(t,"item_indication_ack");     
        C.addEdge("item_indication_ack",ai);
        C.addEdge("item_indication_ack",ag);
        n = new Node("item_delivery", NodeType.ACTION);
        C.addNode(n);
        C.setInitial(n);
        C.addEdge("item_delivery",ai);
        n = new Node("item_discovery", NodeType.PERCEPTION);
        n.preconditions = new NotMatcher(new AtomMatcher(new Predicate("donor_test","success")));
        C.addNode(n);
        C.setInitial(n);
        n = new Node("item_desire", NodeType.INTERNAL);
        C.addNode(n);
        C.addEdge("item_discovery","item_desire");
        C.addEdge("item_desire",ai);
        n = new Node("item_seizure", NodeType.ACTION);
        C.addNode(n);
        C.addEdge("item_desire","item_seizure");
        C.addEdge("item_seizure",ai);
        n = new Node("donor_servitude", NodeType.EVENT);
        n.toAdd.addPredicate(new Predicate("servitude","$donor","$hero"));
        n.preconditions = new AtomMatcher(new Predicate("donor_test_outcome","success"));
        C.addNode(n);
        C.setInitial(n);
        C.addEdge("donor_servitude",ah);
        C.addEdge("donor_servitude",ag);
        
        C.serialize();
        
    }
    
}
