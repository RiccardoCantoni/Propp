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
public class NotMatcher implements PredicateMatcher, Serializable{
    
    public PredicateMatcher pm;
    
    public NotMatcher(PredicateMatcher pm){
        this.pm = pm;
    }

    @Override
    public boolean matchAny(PredicateSet pset) {
        return !pm.matchAny(pset);
    }
    
}
