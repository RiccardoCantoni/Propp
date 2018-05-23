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
public class TestChainSmall implements ChainGenerator{

    @Override
    public void createSave() {
        FunctionChain G = new FunctionChain();
        G.FunctionName = "test";
        Node n;
        n = new Node("a", NodeType.NONE);
        G.addNode(n);
        G.setInitial(n);
        n = new Node("b", NodeType.NONE);
        n.preconditions = new AtomMatcher(new Predicate("a","b","c"));
        G.addNode(n);
        n = new Node("c", NodeType.NONE);
        G.addNode(n);
        G.setInitial(n);
        G.addEdge("a","b");
        n = new Node("d", NodeType.NONE);
        G.addNode(n);
        G.addEdge("c","d");
        n = new Node("e", NodeType.NONE);
        n.preconditions = new AtomMatcher(new Predicate("a","b","d"));
        G.addNode(n);
        G.addEdge("d","e");
        n = new Node("f", NodeType.NONE);
        G.addNode(n);
        G.addEdge("d","f");
        n = new Node("g", NodeType.NONE);
        G.addNode(n);
        G.addEdge("d","g");
        G.serializeAs("test_small");
    }
    
}
