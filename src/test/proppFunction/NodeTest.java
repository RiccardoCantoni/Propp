/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.proppFunction;

import org.junit.Test;

import proppFunction.*;

import static org.junit.Assert.*;

/**
 *
 * @author Riccardo
 */
public class NodeTest {
    
    public NodeTest() {
    }

    @Test
    public void testSuccessors() {
        Node n = new Node();
        Node n1 = new Node();
        Node n2 = new Node();
        assertEquals(0, n.successors().length);
        n.addSuccessor(n1);
        n.addSuccessor(n2);
        assertEquals(2, n.successors().length);
        assertEquals(n1, n.successors()[0]);
        assertEquals(n2, n.successors()[1]);
    }

    @Test
    public void testIsValid() {
        //NOT IMPLEMENTED YET
    }

    @Test
    public void testAddSuccessor() {
        Node n = new Node();
        Node n1 = new Node();
        Node n2 = new Node();
        assertEquals(0, n.successors.size());
        n.addSuccessor(n1);
        assertEquals(1, n.successors.size());
        n.addSuccessor(n2);
        assertEquals(2, n.outdegree());
    }

    @Test
    public void testOutdegree() {
        Node n = new Node();
        assertEquals(0,n.outdegree());
        n.addSuccessor(new Node());
        n.addSuccessor(new Node());
        n.addSuccessor(new Node());
        assertEquals(3,n.outdegree());
    }

    @Test
    public void testEquals() {
        Node n1 = new Node("a", NodeType.ACTION);
        Node n2 = new Node("a", NodeType.EVENT);
        assertTrue(n1.equals(n2));
        assertTrue(n2.equals(n1));
        n1 = new Node("b", NodeType.ACTION);
        assertFalse(n1.equals(n2));
    }
    
}
