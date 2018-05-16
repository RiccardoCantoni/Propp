/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp;

import graph.Node;
import graph.NodeType;
import java.util.LinkedList;
import java.util.List;
import myUtils.ListUtil;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

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
        seq = NodeSequenceManager.clearLabelSequence(seq);
        List<Node> clearSeq = new LinkedList<>();
        clearSeq.add(new Node("a", NodeType.NONE));
        clearSeq.add(new Node("b", NodeType.NONE));
        clearSeq.add(new Node("c", NodeType.NONE));
        assertTrue(ListUtil.listEquals(seq, clearSeq));
    }
    
}
