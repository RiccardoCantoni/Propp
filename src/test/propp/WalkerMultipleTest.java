/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.propp;

import graph.*;
import java.util.*;
import static org.junit.Assert.assertTrue;
import state.*;
import org.junit.Test;
import myUtils.*;
import propp.*;

/**
 *
 * @author Riccardo
 */
public class WalkerMultipleTest {
    
    /**
     * Test of hasNext method, of class WalkerMultiple.
     */
    @Test
    public void testOne() {
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
        assertTrue(ListUtil.ListArrayEquals(walk, 
            new Node[]{
            new Node("$entry_point", NodeType.NONE),
            new Node("z", NodeType.NONE),
            new Node("$entry_point", NodeType.NONE),
            new Node("a", NodeType.NONE),
            new Node("b", NodeType.NONE)
            }
        ));  
    }
    
    @Test
    public void testTwo() {
        FunctionChain C0 = new FunctionChain();
        Node n = new Node("z", NodeType.NONE);
        n.toAdd.addPredicate(new Predicate("a","b","d"));
        C0.addNode(n);
        C0.setInitial(n);
        FunctionChain C1 = FunctionChain.deserializeFrom("test_small");
        WalkerMultiple walker = new WalkerMultiple(new FunctionChain[]{C0,C1}, new PickFirstTransition());
        List<Node> walk = new LinkedList<>();
        while(walker.hasNext()){
            walk.add(walker.next());
        }
        assertTrue(ListUtil.ListArrayEquals(walk, 
            new Node[]{
            new Node("$entry_point", NodeType.NONE),
            new Node("z", NodeType.NONE),
            new Node("$entry_point", NodeType.NONE),
            new Node("c", NodeType.NONE),
            new Node("d", NodeType.NONE),
            new Node("e", NodeType.NONE)
            }
        ));  
    }    
    
}
