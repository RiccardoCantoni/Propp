/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proppFunction;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author Riccardo
 */
public class ProppFunction implements Serializable{
    
    public List<Node> nodes;
    public Node entryPoint;
    public String FunctionName;
    
    public ProppFunction(String name){
        nodes = new LinkedList<>();
        this.entryPoint = new Node("$entry_point", NodeType.NONE);
        this.FunctionName = name;
    }
    
    public void addNode(Node n){
        if (this.contains(n)){
            throw new IllegalArgumentException("node already present");
        }
        nodes.add(n);
    }
    
    public boolean contains(Node n){
        return (this.nodes.contains(n) || (this.entryPoint.equals(n)));
    }
    
    public Node getNodeByLabel(String s){
        if (s.equals("$entry_point")){
            return (Node)this.entryPoint;
        }
        for (Node n : nodes){
            if (n.label.equals(s)){
                return (Node)n;
            }
        }
        throw new IllegalArgumentException("node not found");
    }
    
    public void setInitial(Node n){
        if (this.contains(n)){
            this.addEdge(entryPoint, n);
        }else{
            throw new IllegalArgumentException("node not present");
        }
    }
    
    public Node addEdge(Edge e){
        if (!this.contains(new Node(e.getNodeFrom()))){
            throw new NoSuchElementException("nodefrom not found");
        }
        if (!this.contains(new Node(e.getNodeTo()))){
            this.addNode(new Node(e.getNodeTo()));
        }
        this.getNodeByLabel(e.getNodeFrom()).addSuccessor(this.getNodeByLabel(e.getNodeTo()));
        return this.getNodeByLabel(e.getNodeTo());
    }
    
    public void addEdge(Node from, Node to){
        Edge e = new Edge(from.label, to.label);
        this.addEdge(e);
    }
    
    public Node addEdge(String s1, String s2){
        Edge e = new Edge(s1,s2);
        this.addEdge(e);
        return this.getNodeByLabel(s2);
    }
    
    public void serialize() {
    	serializeAs(this.FunctionName);
    }
    
    private void serializeAs(String filename){
        try {
            FileOutputStream fileOut = new FileOutputStream("ProppFunctions/"+filename+".mc");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (Exception exc) {
            System.out.println("serialization failed");
            exc.printStackTrace();
        } 
    }
    
    public static ProppFunction deserializeFrom(String filename){
        ProppFunction c = null;
        try {
            FileInputStream fileIn = new FileInputStream("ProppFunctions/"+filename+".mc");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            c = (ProppFunction) in.readObject();
            in.close();
            fileIn.close();
        } catch (Exception exc) {
            System.out.println("deserialization failed");
            exc.printStackTrace();
        }
        return c;
    }
        
}
