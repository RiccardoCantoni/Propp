/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp;

import graph.FunctionChain;
import graph.Node;
import graph.NodeType;
import graph.PickFirstTransition;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import state.*;
import myUtils.*;

/**
 *
 * @author Riccardo
 */
public class AcyclicMarkovExplorerTest {
      
    /**
     * Test of explorationPath method, of class AcyclicMarkovExplorer.
     */
    @Test
    public void testExplorationPath() {
        FunctionChain G = FunctionChain.deserializeFrom("test_small");
        AcyclicMarkovExplorer exp = new AcyclicMarkovExplorer();
        List<Node> path = exp.explorationPath(G, new State(), new PickFirstTransition());
        assertTrue(ListComparer.compareToArray(path, 
            new Node[]{
            new Node("$entry_point", NodeType.NONE),
            new Node("c", NodeType.NONE),
            new Node("d", NodeType.NONE),
            new Node("f", NodeType.NONE)
            }
        ));
    }
    
}
