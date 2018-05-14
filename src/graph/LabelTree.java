/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.*;

/**
 *
 * @author Riccardo
 */
public class LabelTree {
    
    private Map<String, String> map;
    private String root;
    
    public LabelTree(String root){
        this.map = new HashMap<String,String>();
        this.root = root;
    }
    
    public boolean contains(String label){
        return (label.equals(root) ||
                map.containsKey(label) ||
                map.containsValue(label));
    }
    
    public String getParent(String label){
        if (!this.contains(label))
            throw new IllegalArgumentException("node not found");
        return this.map.get(label);
    }
    
    public void addLeaf(String leaf, String parent){
        if (!this.contains(parent)){
            throw new IllegalArgumentException("invalid leaf: parent not found: "+leaf+ ", " +parent);
        }else{
        if (this.contains(leaf))
            throw new IllegalArgumentException("invalid leaf: leaf already present");
        }
        map.put(leaf, parent);
    }
    
    public List<String> pathToRoot(String leaf){
       String current = leaf;
       List<String> path = new LinkedList<>();
       while (!current.equals(this.root)){
           path.add(current);
           current = this.getParent(current);
       }
       path.add(this.root);
       return path;
    }
    
}
