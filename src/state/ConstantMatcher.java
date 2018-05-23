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
public class ConstantMatcher implements PredicateMatcher, Serializable{
    
    public boolean constant;
    
    public ConstantMatcher(boolean constant){
        this.constant = constant;
    }

    @Override
    public boolean matchAny(PredicateSet pset) {
        return constant;
    }
    
	@Override
	public List<Predicate> requiredPredicates() {
		return new LinkedList<Predicate>();
	}
    
}
