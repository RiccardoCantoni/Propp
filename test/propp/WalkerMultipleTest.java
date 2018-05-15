/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp;

import graph.FunctionChain;
import graph.*;
import java.util.LinkedList;
import java.util.List;
import myUtils.ListComparer;
import static org.junit.Assert.assertTrue;
import state.*;
import org.junit.Test;

/**
 *
 * @author Riccardo
 */
public class WalkerMultipleTest {
    
    /**
     * Test of hasNext method, of class WalkerMultiple.
     */
    @Test
    public void testAll() {
        FunctionChain C0 = new FunctionChain();
        Node n = new Node("z", NodeType.NONE);
        n.toAdd.addPredicate(new Predicate("a","b","c"));
        C0.addNode(n);
        C0.setInitial(n);
        FunctionChain C1 = FunctionChain.deserializeFrom("test_small");
        WalkerMultiple walker = new WalkerMultiple(new FunctionChain[]{C0,C1}, new PickFirstTransition());
        List<Node> walk = new LinkedList<>();
        while(walker.hasNext()){
            walk.add(walker.next());
        }
        assertTrue(ListComparer.compareToArray(walk, 
            new Node[]{
            new Node("$entry_point", NodeType.NONE),
            new Node("z", NodeType.NONE),
            new Node("$entry_point", NodeType.NONE),
            new Node("c", NodeType.NONE),
            new Node("d", NodeType.NONE),
            new Node("f", NodeType.NONE)
            }
        ));
        
    }
    
    
}
