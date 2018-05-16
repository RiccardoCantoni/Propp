/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import graph.*;

/**
 *
 * @author Riccardo
 */
public class State {
    
    private PredicateSet set;
    
    public State(){
        set = new PredicateSet();
    }
    
    public void update(Node n){
        set.difference(n.toRemove);
        set.union(n.toAdd);
    }
    
    public void rollback(Node n){
        set.difference(n.toAdd);
        set.union(n.toRemove);
    }
    
    public void prettyPrint(){
        set.prettyPrint();
    }
    
    @Override
    public String toString(){
        return "state: "+set.toString();
    }
    
    public boolean isValidNode(Node n){
        return n.isValid(set);
    }
    
    
}
