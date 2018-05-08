/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author Riccardo
 */
public class PredicateSet implements Serializable{
    
    public List<Predicate> set;
    
    public PredicateSet(){
        set = new LinkedList<>();
    }
    
    public PredicateSet(Predicate[] predicates){
        set = Arrays.asList(predicates);
    }
        
    public void addPredicate(Predicate p){
        if (!this.contains(p)){
            set.add(p);
        }
    }
    
    public void removePredicate(Predicate p){
        if (this.contains(p)){
            set.remove(p);
        }
    }
    
    public boolean contains(Predicate p){
        return set.contains(p);
    }
    
    public void union(PredicateSet ps){
        for (Predicate p: ps.set){
            this.set.add(p);
        }
    }
    
    public List<Predicate> findAll(String pred){
        List<Predicate> out = new LinkedList<>();
        for (int i=0; i<set.size(); i++){
            if (set.get(i).pred.equals(pred)){
                out.add(set.get(i));
            }
        }
        return out;
    }
    
    public Predicate find(String pred){
        List<Predicate> ls = this.findAll(pred);
        if (ls.isEmpty())
            return null;
        return ls.get(0);
    }
    
    public int size(){
        return set.size();
    }
    
    @Override
    public boolean equals(Object o){
        if (o==null){
            return false;
        }
        if (!(o instanceof PredicateSet)){
            return false;
        }
        PredicateSet s = (PredicateSet)o;
        if (this.set.size() != s.set.size()){
            return false;
        }
        for (Predicate p : set){
            if (!s.contains(p)){
                return false;
            }
        }
        return true;
    }
    
    public boolean isSuperset(PredicateSet ps){
        for (Predicate p : ps.set){
            if (!this.contains(p)){
                return false;
            }
        }
        return true;
    }
    
    public void prettyPrint(){
        String s = "";
        for (Predicate p : set){
            s += (p.toString()+",");
        }
        System.out.println(s);
    }
    
}
