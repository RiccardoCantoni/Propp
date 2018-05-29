/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Riccardo
 */
public class AtomMatcher implements PredicateMatcher, Serializable{
    
    public Predicate predicate;
    
    private static final int UNIFIED = 1;
    public static final int STRINGS_EQUAL = 0;
    public static final int STRINGS_UNEQUAL = -1;
    
    
    public AtomMatcher(Predicate p){
        this.predicate = p;
    }
    
    public AtomMatcher(String s1, String s2, String s3) {
    	this.predicate = new Predicate(s1,s2,s3);
    }

    @Override
    public boolean matchAny(PredicateSet pset) {
        for (Predicate p : pset.set){
            if (matchSingle(p)) 
                return true;
        }
        return false;
    }
    
    public boolean matchSingle(Predicate p){
        int val = stringMatch(this.predicate.pred, p.pred);
        if (val==UNIFIED) return true;
        if (val==STRINGS_UNEQUAL) return false;
        val = stringMatch(this.predicate.subj, p.subj);
        if (val==UNIFIED) return true;
        if (val==STRINGS_UNEQUAL) return false;
        val = stringMatch(this.predicate.obj, p.obj);
        if (val==UNIFIED || val==STRINGS_EQUAL) return true;
        return false;
 
    }
    
    private static final String UNIFY_ALL = "_";
    
    private int stringMatch(String s1, String s2){
        if (s1.equals(UNIFY_ALL) || s2.equals(UNIFY_ALL)) return 1;
        if (s1.equals(s2)) return 0;
        return -1;
    }

	@Override
	public List<Predicate> requiredPredicates() {
		List<Predicate> p = new LinkedList<>();
		p.add(this.predicate);
		return p;
	}
    
}
