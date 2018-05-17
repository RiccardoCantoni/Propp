/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.propp;

import graph.FunctionChain;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import graph.*;
import state.*;
import myUtils.*;
import propp.*;

/**
 *
 * @author Riccardo
 */
public class WalkerSingleTest {
    
    FunctionChain C;
    WalkerSingle walker;
    
    @Before
    public void setUp() {
         C = FunctionChain.deserializeFrom("test_small");
    }

    /**
     * Test of hasNext method, of class WalkerSingle.
     */
    @Test
    public void testEmptyState() {
        walker = new WalkerSingle(C, new PickFirstTransition(), new State());
        assertTrue(walker.hasNext());
        List<Node> path = new LinkedList<>();
        while(walker.hasNext()){
            path.add(walker.next());
        }
        assertTrue(ListUtil.ListArrayEquals(path, 
            new Node[]{
            new Node("$entry_point", NodeType.NONE),
            new Node("c", NodeType.NONE),
            new Node("d", NodeType.NONE),
            new Node("f", NodeType.NONE)
            }
        ));
    }
    
    @Test
    public void testWithState() {
        State initialState = new State();
        Node n = new Node("x",NodeType.ACTION);
        n.toAdd.addPredicate(new Predicate("a","b","c"));
        initialState.update(n);
        walker = new WalkerSingle(C, new PickFirstTransition(), initialState);
        assertTrue(walker.hasNext());
        List<Node> path = new LinkedList<>();
        while(walker.hasNext()){
            path.add(walker.next());
        }
        assertTrue(ListUtil.ListArrayEquals(path, 
            new Node[]{
            new Node("$entry_point", NodeType.NONE),
            new Node("a", NodeType.NONE),
            new Node("b", NodeType.NONE)
            }
        ));
    }
    
}
