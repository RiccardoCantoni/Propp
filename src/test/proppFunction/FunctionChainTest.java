/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.proppFunction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Test;

import proppFunction.ProppFunction;
import proppFunction.Node;
import proppFunction.NodeType;

public class FunctionChainTest {
    
    Node n1, n2, n3, ne;
    
    public FunctionChainTest() {
        ne = new Node();
        n1 = new Node("a", NodeType.ACTION);
        n2 = new Node("b", NodeType.ACTION);
    }

    @Test
    public void testAddNode() {
        ProppFunction c = new ProppFunction("test");
        assertEquals(0, c.nodes.size());
        c.addNode(ne);
        c.addNode(n1);
        c.addNode(n2);
        assertEquals(3, c.nodes.size());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddNodeException() {
        ProppFunction c = new ProppFunction("test");
        c.addNode(n1);
        c.addNode(n2);
        c.addNode(n1);
    }

    @Test
    public void testAddEdge_Edge() {
        ProppFunction c = new ProppFunction("test");
        c.addNode(n1);
        c.addEdge(n1,n2);
        assertEquals(c.nodes.get(0).successors()[0], n2);
        assertEquals(c.nodes.size(), 2);
        c.addEdge(n2,n1);
        assertEquals(c.nodes.get(1).successors()[0], n1);
        assertEquals(c.nodes.size(), 2);
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testAddEdge_EdgeException() {
        ProppFunction c = new ProppFunction("test");
        c.addEdge("a","b");
    }

    @Test
    public void testaddEdge_INode_INode() {
        ProppFunction c = new ProppFunction("test");
        c.addNode(n1);
        c.addEdge(n1,n2);
        assertEquals(c.nodes.get(0).successors()[0], n2);
        assertEquals(c.nodes.size(), 2);
        c.addEdge(n2,n1);
        assertEquals(c.nodes.get(1).successors()[0], n1);
        assertEquals(c.nodes.size(), 2);
    }
    
    @Test
    public void testContains(){
        ProppFunction c = new ProppFunction("test");
        Node n = new Node("aaa", NodeType.ACTION);
        Node nn = new Node("bbb", NodeType.ACTION);
        c.addNode(n);
        assertTrue(c.contains(n));
        assertFalse(c.contains(nn));
    }
    
    @Test
    public void testGetNodeById(){
        ProppFunction c = new ProppFunction("test");
        Node a = new Node("a", NodeType.EVENT);
        Node b = new Node("b", NodeType.INTERNAL);
        c.addNode(a);
        c.addNode(b);
        assertEquals(c.getNodeByLabel("b").type, NodeType.INTERNAL);
    }
    
}
