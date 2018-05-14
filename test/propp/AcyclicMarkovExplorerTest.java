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
    
    public AcyclicMarkovExplorerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }

    /**
     * Test of explorationPath method, of class AcyclicMarkovExplorer.
     */
    @Test
    public void testExplorationPath() {
        FunctionChain G = new FunctionChain();
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
        n.preconditions = new AtomMatcher(new Predicate("a","b","c"));
        G.addNode(n);
        G.addEdge("d","e");
        n = new Node("f", NodeType.NONE);
        G.addNode(n);
        G.addEdge("d","f");
        n = new Node("g", NodeType.NONE);
        G.addNode(n);
        G.addEdge("d","g");
        AcyclicMarkovExplorer exp = new AcyclicMarkovExplorer();
        List<Node> path = exp.explorationPath(G, new State(), new PickFirstTransition());
        List<Node> correctPath = new LinkedList<>();
        correctPath.add(new Node("$entry_point", NodeType.NONE));
        correctPath.add(new Node("c", NodeType.NONE));
        correctPath.add(new Node("d", NodeType.NONE));
        correctPath.add(new Node("f", NodeType.NONE));
        assertTrue(ListComparer.compareToList(path, correctPath));
    }
    
}
