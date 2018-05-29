/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.proppFunction;

import org.junit.Test;

import proppFunction.*;

import static org.junit.Assert.*;
import java.util.*;

public class FunctionChainTest {
    
    Node n1, n2, n3, ne;
    
    public FunctionChainTest() {
        ne = new Node();
        n1 = new Node("a", NodeType.ACTION);
        n2 = new Node("b", NodeType.ACTION);
    }

    @Test
    public void testAddNode() {
        FunctionChain c = new FunctionChain();
        assertEquals(0, c.nodes.size());
        c.addNode(ne);
        c.addNode(n1);
        c.addNode(n2);
        assertEquals(3, c.nodes.size());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddNodeException() {
        FunctionChain c = new FunctionChain();
        c.addNode(n1);
        c.addNode(n2);
        c.addNode(n1);
    }

    @Test
    public void testAddEdge_Edge() {
        FunctionChain c = new FunctionChain();
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
        FunctionChain c = new FunctionChain();
        c.addEdge("a","b");
    }

    @Test
    public void testaddEdge_INode_INode() {
        FunctionChain c = new FunctionChain();
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
        FunctionChain c = new FunctionChain();
        Node n = new Node("aaa", NodeType.ACTION);
        Node nn = new Node("bbb", NodeType.ACTION);
        c.addNode(n);
        assertTrue(c.contains(n));
        assertFalse(c.contains(nn));
    }
    
    @Test
    public void testGetNodeById(){
        FunctionChain c = new FunctionChain();
        Node a = new Node("a", NodeType.EVENT);
        Node b = new Node("b", NodeType.INTERNAL);
        c.addNode(a);
        c.addNode(b);
        assertEquals(c.getNodeByLabel("b").type, NodeType.INTERNAL);
    }
    
}
