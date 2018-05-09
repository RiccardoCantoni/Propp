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
public class ChainG implements ChainGenerator{

    @Override
    public void createSave() {
        FunctionChain C = new FunctionChain();
        Node n;
        
        String a = "arrival"; 
        n = new Node(a, NodeType.EVENT);
        n.toRemove.addPredicate(new Predicate("location","$hero","_"));
        n.toAdd.addPredicate(new Predicate("location","$hero","$location"));
        C.addNode(n);
        n = new Node("way_discovered", NodeType.PI);
        C.addNode(n);
        C.setInitial(n);
        n = new Node("way_followed", NodeType.ACTION);
        C.addNode(n);
        C.addEdge("way_discovered","way_followed");
        C.addEdge("way_followed",a);
        n = new Node("way_showed", NodeType.ACTION);
        n.preconditions = new OrMatcher(new AtomMatcher(new Predicate("acquisition_type","guidance")),new AtomMatcher(new Predicate("acquisition_type","helper")));
        C.addNode(n);
        C.setInitial(n);
        C.addEdge("way_showed","way_discovered");
        n = new Node("travel", NodeType.ACTION);
        C.addNode(n);
        C.setInitial(n);
        C.addEdge("travel",a);
        n = new Node("magical_travel", NodeType.ACTION);
        n.preconditions = new OrMatcher(new AtomMatcher(new Predicate("acquisition_type","item")),new AtomMatcher(new Predicate("acquisition_type","helper")));
        C.addNode(n);
        C.setInitial(n);
        C.addEdge("magical_travel",a);
        n = new Node("guided_travel", NodeType.ACTION);
        n.preconditions = new AtomMatcher(new Predicate("acquisition_type","guidance"));
        C.addNode(n);
        C.setInitial(n);
        C.addEdge("guided_travel",a);
        
        C.serializeAs("G");
    }
    
}
