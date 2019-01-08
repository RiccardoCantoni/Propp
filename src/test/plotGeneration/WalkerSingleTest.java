/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.plotGeneration;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import myUtils.ListUtils;
import plotGeneration.NodeSequenceManager;
import plotGeneration.WalkerSingle;
import proppFunction.ProppFunction;
import proppFunction.Node;
import proppFunction.NodeType;
import proppFunction.PickFirstTransition;
import state.Predicate;
import state.State;

/**
 *
 * @author Riccardo
 */
public class WalkerSingleTest {
    
    ProppFunction C;
    WalkerSingle walker;
    
    @Before
    public void setUp() {
         C = ProppFunction.deserializeFrom("test_small");
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
        assertTrue(ListUtils.listArrayEquals(path, 
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
        n.toAdd.addPredicate(new Predicate("b","b","b"));
        initialState.update(n);
        walker = new WalkerSingle(C, new PickFirstTransition(), initialState);
        assertTrue(walker.hasNext());
        List<Node> path = new LinkedList<>();
        while(walker.hasNext()){
            path.add(walker.next());
        }
        assertTrue(ListUtils.listArrayEquals(path, 
            new Node[]{
            new Node("$entry_point", NodeType.NONE),
            new Node("a", NodeType.NONE),
            new Node("b", NodeType.NONE)
            }
        ));
    }
    
}
