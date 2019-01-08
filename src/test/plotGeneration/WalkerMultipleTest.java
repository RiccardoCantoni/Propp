/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.plotGeneration;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import myUtils.ListUtils;
import plotGeneration.PlotArgument;
import plotGeneration.LinearPlotGenerator;
import plotGeneration.NodeSequenceManager;
import propp.Configuration;
import proppFunction.ProppFunction;
import proppFunction.MarkovTransition;
import proppFunction.Node;
import proppFunction.NodeType;
import proppFunction.PickFirstTransition;
import state.Predicate;
import state.State;

/**
 *
 * @author Riccardo
 */
public class WalkerMultipleTest {
	
	/*private MarkovTransition t;
	private boolean um;
    
	@Before
	public void before() {
		t = SystemState.getInstance().transition_function;
		um = SystemState.getInstance().unconstrained_mode;
		SystemState.getInstance().transition_function = new PickFirstTransition();
		SystemState.getInstance().unconstrained_mode = false;
	}
	
	@After
	public void after() {
		SystemState.getInstance().transition_function = t;
		SystemState.getInstance().unconstrained_mode = um;
	}*/
	
    /**
     * Test of hasNext method, of class WalkerMultiple.
     */
    /*@Test
    public void testOne() {
        FunctionChain C0 = new FunctionChain();
        C0.FunctionName = "test";
        Node n = new Node("z", NodeType.NONE);
        n.toAdd.addPredicate(new Predicate("b","b","b"));
        C0.addNode(n);
        C0.setInitial(n);
        FunctionChain C1 = FunctionChain.deserializeFrom("test_small");
        PlotArgument arg = new PlotArgument(new FunctionChain[]{C0,C1}, new State(), new String[0]);
        LinearPlotGenerator walker = new LinearPlotGenerator(arg);
        List<Node> walk = new LinkedList<>();
        while(walker.hasNext()){
            walk.add(walker.next());
        }
        List<Node> expectedWalk = Arrays.asList( 
                new Node[]{
                        new Node("$test", NodeType.NONE),
                        new Node("z", NodeType.NONE),
                        new Node("$test", NodeType.NONE),
                        new Node("a", NodeType.NONE),
                        new Node("b", NodeType.NONE)
                        });
        assertTrue(ListUtil.listEquals(walk, expectedWalk));
    }
    
    @Test
    public void testTwo() {
        FunctionChain C0 = new FunctionChain();
        C0.FunctionName = "test";
        Node n = new Node("z", NodeType.NONE);
        n.toAdd.addPredicate(new Predicate("a","b","d"));
        C0.addNode(n);
        C0.setInitial(n);
        FunctionChain C1 = FunctionChain.deserializeFrom("test_small");
        PlotArgument arg = new PlotArgument(new FunctionChain[]{C0,C1}, new State(), new String[0]);
        LinearPlotGenerator walker = new LinearPlotGenerator(arg);
        List<Node> walk = new LinkedList<>();
        while(walker.hasNext()){
            walk.add(walker.next());
        }
        assertTrue(ListUtil.listArrayEquals(walk, 
            new Node[]{
            new Node("$test", NodeType.NONE),
            new Node("z", NodeType.NONE),
            new Node("$test", NodeType.NONE),
            new Node("c", NodeType.NONE),
            new Node("d", NodeType.NONE),
            new Node("e", NodeType.NONE)
            }
        ));  
    } */   
    
}
