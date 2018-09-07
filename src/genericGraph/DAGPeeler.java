package genericGraph;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import myUtils.ListUtils;
import proppFunction.GraphExplorationException;

public class DAGPeeler<T> implements Iterator<T>{
	
	List<DirectedEdge<T>> edges;
	List<T> nodes;
	List<T> peelable;
	
	public DAGPeeler (DirectedAcyclicGraph<T> dag){
		this.edges = dag.edges();
		this.nodes = dag.nodeSet();
		peelable = nodes.stream().filter(n->indegree(n)==0).collect(Collectors.toList());
	}

	@Override
	public boolean hasNext() {
		return nodes.size() > 0;
	}

	@Override
	public T next() {
		if (peelable.isEmpty()) {
			throw new GraphExplorationException("invalid DAG: loop detected");
		}
		T n = ListUtils.pickRandom(peelable); 
		removeOutgoing(n);
		updateState(n);
		return n;
	}
	
	private void updateState(T removed){
		nodes.remove(removed);
		peelable = nodes.stream().filter(n->indegree(n)==0).collect(Collectors.toList());
	}
	
	private void removeOutgoing(T node){
		edges = edges.stream().filter(e->!e.from.equals(node)).collect(Collectors.toList());
	}
	
	private int indegree(T node) {
		int o = 0;
		for (DirectedEdge<T> e : edges) {
			if (e.to.equals(node)) o++;
		}
		return o;
	}

}
