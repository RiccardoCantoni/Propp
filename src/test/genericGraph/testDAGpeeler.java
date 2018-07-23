package test.genericGraph;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import genericGraph.DAGPeeler;
import genericGraph.DirectedAcyclicGraph;
import myUtils.ListUtil;
import myUtils.SharedRandom;

public class testDAGpeeler {

DirectedAcyclicGraph<Integer> dag;
	
	public testDAGpeeler() {
		SharedRandom.getInstance().setRandom();
		dag = new DirectedAcyclicGraph<>();
		dag.addNode(1);
		dag.addNode(2);
		dag.addNode(3);
		dag.addNode(4);
		dag.addEdge(1, 2);
		dag.addEdge(1, 3);
		dag.addEdge(2, 4);
		dag.addEdge(3, 4);
	}
	
	@Test
	public void peelTest() {
		DAGPeeler<Integer> peeler = new DAGPeeler<>(dag);
		List<Integer> out = new LinkedList<>();
		while(peeler.hasNext()) {
			Integer i = peeler.next();
			out.add(i);
		}
		List<Integer> ls1 = Arrays.asList(new Integer[] {1,2,3,4});
		List<Integer> ls2 = Arrays.asList(new Integer[] {1,3,2,4});
		assertTrue(
				ListUtil.listEquals(out, ls1) ||
				ListUtil.listEquals(out, ls2)
				);
	}
	
}
