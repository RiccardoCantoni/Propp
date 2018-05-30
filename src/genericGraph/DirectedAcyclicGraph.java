package genericGraph;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

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
	
    public void serializeAs(String filename){
        try {
            FileOutputStream fileOut = new FileOutputStream(filename+".dag");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (Exception exc) {
            System.out.println("serialization failed");
            exc.printStackTrace();
        } 
    }
    
    public static <T1> DirectedAcyclicGraph<T1> deserializeFrom(String filename){
    	DirectedAcyclicGraph<T1> dag = null;
        try {
            FileInputStream fileIn = new FileInputStream(filename+".dag");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            dag = (DirectedAcyclicGraph<T1>) in.readObject();
            in.close();
            fileIn.close();
        } catch (Exception exc) {
            System.out.println("deserialization failed");
            exc.printStackTrace();
        }
        return dag;
    }
	

	
	
	

}
