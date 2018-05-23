/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import java.util.List;

public interface PredicateMatcher{
    
    public boolean matchAny(PredicateSet pset);
    public List<Predicate> requiredPredicates();
    
}
