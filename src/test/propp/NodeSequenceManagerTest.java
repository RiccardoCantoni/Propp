/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.propp;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import myUtils.ListUtil;
import propp.NodeSequenceManager;
import proppFunction.Node;
import proppFunction.NodeType;

/**
 *
 * @author Riccardo
 */
public class NodeSequenceManagerTest {

    /**
     * Test of clearSequence method, of class NodeSequenceManager.
     */
    @Test
    public void testClearSequence() {
        List<Node> seq = new LinkedList<>();
        seq.add(new Node("a", NodeType.NONE));
        seq.add(new Node("b", NodeType.NONE));
        seq.add(new Node("$x", NodeType.NONE));
        seq.add(new Node("c", NodeType.NONE));
        List<String> s1 = NodeSequenceManager.getLabelSequence(seq);
        s1 = NodeSequenceManager.clearLabelSequence(s1);
        seq = new LinkedList<>();
        seq.add(new Node("a", NodeType.NONE));
        seq.add(new Node("b", NodeType.NONE));
        seq.add(new Node("c", NodeType.NONE));
        List<String> s2 = NodeSequenceManager.getLabelSequence(seq);
        s2 = NodeSequenceManager.clearLabelSequence(s2);
        assertTrue(ListUtil.listEquals(s1,s2));
    }
    
}
