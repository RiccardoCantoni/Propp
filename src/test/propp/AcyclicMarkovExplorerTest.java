/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.propp;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import myUtils.ListUtil;
import propp.AcyclicMarkovExplorer;
import propp.NodeSequenceManager;
import proppFunction.FunctionChain;
import proppFunction.Node;
import proppFunction.NodeType;
import proppFunction.PickFirstTransition;
import state.State;

/**
 *
 * @author Riccardo
 */
public class AcyclicMarkovExplorerTest {
      
    /**
     * Test of explorationPath method, of class AcyclicMarkovExplorer.
     */
    @Test
    public void testSinglePath() {
        FunctionChain G = FunctionChain.deserializeFrom("test_small");
        AcyclicMarkovExplorer exp = new AcyclicMarkovExplorer();
        List<Node> path = exp.explorationPath(G, new State(), new PickFirstTransition());
        List<Node> path2 = Arrays.asList(new Node[]{
                new Node("$entry_point", NodeType.NONE),
                new Node("c", NodeType.NONE),
                new Node("d", NodeType.NONE),
                new Node("f", NodeType.NONE)
                });
        assertTrue(ListUtil.listEquals(path, path2)); 
    }
    
    @Test
    public void testMultiplePath() {
        FunctionChain G = new FunctionChain();
        Node n = new Node("a", NodeType.NONE);
        G.addNode(n);
        G.setInitial(n);
        n = new Node("b", NodeType.NONE);
        G.addNode(n);
        G.setInitial(n);
        G.addEdge("a", "b");
        AcyclicMarkovExplorer exp = new AcyclicMarkovExplorer();
        List<Node> path = exp.explorationPath(G, new State(), new PickFirstTransition());
        assertTrue(ListUtil.listArrayEquals(path, 
            new Node[]{
            new Node("$entry_point", NodeType.NONE),
            new Node("a", NodeType.NONE),
            new Node("b", NodeType.NONE)
            }
        ));
    }
}
