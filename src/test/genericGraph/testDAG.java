package test.genericGraph;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import genericGraph.DirectedAcyclicGraph;
import genericGraph.DirectedEdge;
import myUtils.ListUtil;

public class testDAG {
	
	DirectedAcyclicGraph<Integer> dag;
	
	public testDAG() {
		dag = new DirectedAcyclicGraph<>();
		dag.addNode(1);
		dag.addNode(2);
		dag.addNode(3);
		dag.addNode(4);
		dag.addNode(5);
	}
	
	@Test
	public void addNodeTest() {
		List<Integer> ls2 = new LinkedList<>();
		ls2 = Arrays.asList(new Integer[] {1,2,3,4,5});
		assertTrue(ListUtil.listEquals(dag.nodeSet(), ls2));
	}
	
	@Test
	public void addEdgeTest() {
		List<DirectedEdge<Integer>> ls2 = new LinkedList<>();
		dag.addEdge(1, 2);
		dag.addEdge(1, 3);
		ls2.add(new DirectedEdge<Integer>(1,2));
		ls2.add(new DirectedEdge<Integer>(1,3));
		assertTrue(ListUtil.listEquals(dag.edges(), ls2));
	}
	
	@Test
	public void successors() {
		List<Integer> ls2 = new LinkedList<>();
		dag.addEdge(1, 2);
		dag.addEdge(1, 3);
		dag.addEdge(2, 4);
		ls2 = Arrays.asList(new Integer[] {2,3});
		assertTrue(ListUtil.listEquals(dag.successors(1), ls2));
	}
	
	@Test
	public void predecessors() {
		List<Integer> ls2 = new LinkedList<>();
		dag.addEdge(1, 2);
		dag.addEdge(1, 3);
		dag.addEdge(2, 4);
		dag.addEdge(3, 4);
		ls2 = Arrays.asList(new Integer[] {2,3});
		assertTrue(ListUtil.listEquals(dag.predecessors(4), ls2));
	}
	
	
	
	

}
