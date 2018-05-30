/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
    
    public boolean contains(Predicate p){
        PredicateMatcher m = new AtomMatcher(p);
        return (m.matchAny(this));
    }
        
    public void addPredicate(Predicate p){
        if (!this.contains(p)){
            set.add(p);
        }
    }
    
    public void removeAllMatching(Predicate p){
        AtomMatcher m = new AtomMatcher(p);
        List<Predicate> toRemove = new LinkedList<>();
        for (Predicate pr : this.set){
            if (m.matchSingle(pr)){
                toRemove.add(pr);
            }
        }
        this.set.removeAll(toRemove);
    }
    
    public void union(PredicateSet ps){
        for (Predicate p: ps.set){
            this.addPredicate(p);
        }
    }
    
    public void difference(PredicateSet ps){
        for (Predicate p: ps.set){
            this.removeAllMatching(p);
        }
    }
    
    public List<Predicate> findAll(Predicate p){
        AtomMatcher m = new AtomMatcher(p);
        List<Predicate> out = new LinkedList<>();
        for (Predicate pr : this.set){
            if (m.matchSingle(pr)){
                out.add(pr);
            }
        }
        return out;
    }
    
    public Predicate find(Predicate p){
        List<Predicate> ls = this.findAll(p);
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
    
    @Override
    public String toString(){
        String s = "";
        for (Predicate p : set){
            s += (p.toString()+",");
        }
        return s;
    }
       
    public void prettyPrint(){
        String s = "";
        for (Predicate p : set){
            s += (p.toString()+",");
        }
        System.out.println(s);
    }
    
}
