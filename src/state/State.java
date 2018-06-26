/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import propp.SystemState;
import proppFunction.Node;

/**
 *
 * @author Riccardo
 */
public class State {
	
	SystemState system;
    
    private PredicateSet set;
    
    public State(){
    	system = SystemState.getInstance();
        set = new PredicateSet();
    }
    
    public PredicateSet getSet() {
    	return set;
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
    	if (system.unconstrained_mode) return true;
        return n.isValid(set);
    }
    
    public void inject(String label) {
    	set.addPredicate(new Predicate("injection",label,"_"));
    }
    
    public void cleanInjections() {
    	Predicate injection = new Predicate("injection","_","_");
    	set.removeAllMatching(injection);
    }   
    
    public void removePredicate(Predicate p) {
    	this.set.removeOneMatching(p);
    }
    
}
