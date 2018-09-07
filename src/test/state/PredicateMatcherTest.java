/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.state;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import myUtils.ListUtils;
import state.AndMatcher;
import state.AtomMatcher;
import state.NotMatcher;
import state.OrMatcher;
import state.Predicate;
import state.PredicateMatcher;
import state.PredicateSet;

/**
 *
 * @author Riccardo
 */
public class PredicateMatcherTest {
    
    PredicateSet ps1, ps2;
    Predicate p1,p2,p3;
    
    @Before
    public void init() {
        p1 = new Predicate("a","b","c");
        p2 = new Predicate("a","b","d");
        p3 = new Predicate("x","y","z");
        ps1 = new PredicateSet();
        ps1.addPredicate(p1);
        ps1.addPredicate(p2);
        ps2 = new PredicateSet();
        ps2.addPredicate(p3);
    }

    @Test
    public void testMatchAtom() { 
        PredicateMatcher m = new AtomMatcher(p2);
        assertTrue(m.matchAny(ps1));
        m = new AtomMatcher(new Predicate("a","_","_"));
        assertTrue(m.matchAny(ps1));
        m = new AtomMatcher(p3);
        assertFalse(m.matchAny(ps1)); 
        
        AtomMatcher a = new AtomMatcher(p1);
        assertFalse(a.matchSingle(p2));
        assertTrue(a.matchSingle(new Predicate("a","_","_")));
    }
    
    @Test
    public void testMatchAnd() {
        AtomMatcher p1m = new AtomMatcher(p1);
        AtomMatcher p2m = new AtomMatcher(p2);
        AtomMatcher p3m = new AtomMatcher(p3);
        PredicateMatcher m = new AndMatcher(p1m,p2m);
        assertTrue(m.matchAny(ps1));
        m = new AndMatcher(p1m,p3m);
        assertFalse(m.matchAny(ps1));
    }
    
    @Test
    public void testMatchOr() {
        AtomMatcher p1m = new AtomMatcher(p1);
        AtomMatcher p2m = new AtomMatcher(p2);
        AtomMatcher p3m = new AtomMatcher(p3);
        PredicateMatcher m = new OrMatcher(p3m,p1m);
        assertTrue(m.matchAny(ps1));
        assertTrue(m.matchAny(ps2));
    }
    
    @Test
    public void testMatchComposite() {
        AtomMatcher p1m = new AtomMatcher(p1);
        AtomMatcher p2m = new AtomMatcher(p2);
        AtomMatcher p3m = new AtomMatcher(p3);
        PredicateSet ps = new PredicateSet();
        ps.addPredicate(p1);
        PredicateMatcher m = new AndMatcher(new OrMatcher(p1m,p2m),new NotMatcher(p3m));
        //(A | B) & !C
        assertTrue(m.matchAny(ps));
        ps.addPredicate(p3);
        assertFalse(m.matchAny(ps));
    }
    
    @Test
    public void testMatchRequired() {
        AtomMatcher p1m = new AtomMatcher(p1);
        AtomMatcher p2m = new AtomMatcher(p2);
        AtomMatcher p3m = new AtomMatcher(p3);
        PredicateSet ps = new PredicateSet();
        ps.addPredicate(p1);
        PredicateMatcher m = new AndMatcher(new OrMatcher(p1m,p2m),new NotMatcher(p3m));
        //(A | B) & !C
        assertTrue(ListUtils.listArrayEquals(m.requiredPredicates(), new Predicate[] {p1,p2,p3}));
    }

}
