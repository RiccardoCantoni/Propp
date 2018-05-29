package genericGraph;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class DirectedAcyclicGraph<T> implements Serializable{
	
	private List<T> nodes;
	private List<DirectedEdge<T>> edges;
	
	public DirectedAcyclicGraph() {
		nodes = new LinkedList<T>();
		edges = new LinkedList<DirectedEdge<T>>();
	}
	
	public void addNode(T node) {
		nodes.add(node);
	}
	
	public void addEdge(T from, T to) {
		if (!containsNode(from)) 
			throw new IllegalArgumentException("node from not present: "+from.toString());
		if (!containsNode(to)) 
			throw new IllegalArgumentException("node from not present: "+to.toString());
		edges.add(new DirectedEdge<T>(from, to));
	}
	
	public boolean containsNode(T node) {
		return nodes.contains(node);
	}
	
	public int outdegree(T node) {
		int o = 0;
		for (DirectedEdge<T> e : edges) {
			if (e.from.equals(node)) o++;
		}
		return o;
	}
	
	public List<T> successors(T node){
		List<T> s = new LinkedList<>();
		for (DirectedEdge<T> e : edges) {
			if (e.from.equals(node)) 
				s.add(e.to);
		}
		return s;
	}
	
	public List<T> predecessors(T node){
		List<T> p = new LinkedList<>();
		for (DirectedEdge<T> e : edges) {
			if (e.to.equals(node)) 
				p.add(e.from);
		}
		return p;
	}
	
	public List<DirectedEdge<T>> edges(){
		return this.edges;
	}
	
	public List<T> nodeSet(){
		return this.nodes;
	}
	

	
	
	

}
