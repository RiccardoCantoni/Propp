/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.proppFunction;

import java.util.List;
import org.junit.*;

import proppFunction.*;

import static org.junit.Assert.*;

/**
 *
 * @author Riccardo
 */
public class LabelTreeTest {
    
    LabelTree tree;
    
    @Before
    public void setUpClass() { 
        tree = new LabelTree("root");
    }
    
    @Test
    public void testAddGetParent() {
        tree.addLeaf("a", "root");
        tree.addLeaf("b", "root");
        tree.addLeaf("c", "a");
        assertEquals(tree.getParent("a"), "root");
        assertEquals(tree.getParent("b"), "root");
        assertEquals(tree.getParent("c"),"a");
    }

    @Test
    public void testContains() {
        assertTrue(tree.contains("root"));
        assertFalse(tree.contains("xyz"));
        tree.addLeaf("a", "root");
        tree.addLeaf("b","a");
        assertTrue(tree.contains("a"));
        assertTrue(tree.contains("b"));
        assertFalse(tree.contains("c"));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testParentNotFound() { 
        tree.addLeaf("a", "root");
        assertFalse(tree.contains("c"));
        tree.addLeaf("b", "c");
        assertFalse(tree.contains("c"));
    }
    
    @Test
    public void testPathToRoot() {
        tree.addLeaf("a", "root");
        tree.addLeaf("b", "root");
        tree.addLeaf("c", "b");
        List<String> path = tree.pathToRoot("c");
        assertEquals(3,path.size());
        assertEquals(path.get(0),"c");
        assertEquals(path.get(1),"b");
        assertEquals(path.get(2),"root");
    }
    
}
