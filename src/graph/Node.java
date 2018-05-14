/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.io.Serializable;
import java.util.*;
import state.*;

/**
 *
 * @author Riccardo
 */
public class Node implements Serializable{
    
    public List<Node> successors;
    public NodeType type;
    public PredicateSet toAdd, toRemove;
    public PredicateMatcher preconditions;
    public String label;
    
    public Node(){
       this.successors = new LinkedList<>();
       this.type = null;
       this.preconditions = new ConstantMatcher(true);
       this.toAdd = new PredicateSet();
       this.toRemove = new PredicateSet();
       this.label = "";
    }
    
    public Node(NodeType t, Predicate[] preconditions, Predicate[] toAdd, Predicate[] toRemove){
        this.successors = new LinkedList<>();
        this.type = t;
        this.preconditions = new ConstantMatcher(true);
        this.toAdd = new PredicateSet(toAdd);
        this.toRemove = new PredicateSet(toRemove);
        this.label = "";
    }
    
    public Node(String label, NodeType type){
       this.successors = new LinkedList<>();
       this.type = type;
       this.preconditions = new ConstantMatcher(true);
       this.toAdd = new PredicateSet();
       this.toRemove = new PredicateSet();
       this.label = label;
    }
    
     public Node(String label){
       this.successors = new LinkedList<>();
       this.type = NodeType.NONE;
       this.preconditions = new ConstantMatcher(true);
       this.toAdd = new PredicateSet();
       this.toRemove = new PredicateSet();
       this.label = label;
    }

    public Node[] successors() {
        return successors.toArray(new Node[]{});
    }

    public boolean isValid(PredicateSet s) {
        return preconditions.matchAny(s);
    }

    public void addSuccessor(Node n) {
        this.successors.add(n);
    }
    
    public int outdegree(){
        return successors.size();
    }
    
    public boolean isFinal(){
        return successors.isEmpty();
    }
    
    @Override
    public boolean equals(Object o){
        if (o==null){
            return false;
        }
        if (!(o instanceof Node)){
            return false;
        }
        Node n = (Node)o;
        return (this.label.equals(n.label));
    }
    
    public void prettyPrint(){
        System.out.println(this.type + " | "+this.label);
    }
    
    
    
}
