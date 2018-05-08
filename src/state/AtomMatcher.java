/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import java.io.Serializable;

/**
 *
 * @author Riccardo
 */
public class AtomMatcher implements PredicateMatcher, Serializable{
    
    public Predicate predicate;
    
    public AtomMatcher(Predicate p){
        this.predicate = p;
    }

    @Override
    public boolean match(PredicateSet pset) {
        for (Predicate p2 : pset.set){
        if (stringMatch(this.predicate.pred, p2.pred)) return true;
        if (stringMatch(this.predicate.subj, p2.subj)) return true;
        if (stringMatch(this.predicate.obj, p2.obj)) return true;
        }
        return false;
    }
    
    private static final String UNIFY_ALL = "_";
    
    private boolean stringMatch(String s1, String s2){
        if (s1.equals(UNIFY_ALL) || s2.equals(UNIFY_ALL)) return true;
        return s1.equals(s2);
    }
    
}
