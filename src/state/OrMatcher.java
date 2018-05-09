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
public class OrMatcher implements PredicateMatcher, Serializable{
    
    public PredicateMatcher pm1, pm2;
    
    public OrMatcher(PredicateMatcher pm1, PredicateMatcher pm2){
        this.pm1 = pm1;
        this.pm2 = pm2;
    }

    @Override
    public boolean matchAny(PredicateSet pset) {
        return (pm1.matchAny(pset) || pm2.matchAny(pset));
    }
    
}
