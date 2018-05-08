/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Riccardo
 */
public class PredicateSetTest {
    
    public PredicateSetTest() {
    }
    
    @Test
    public void testAddPredicate() {
        PredicateSet pset = new PredicateSet();
        Predicate abc = new Predicate("a","b","c");
        Predicate abc2 = new Predicate("a","b","c");
        Predicate xyz = new Predicate("x","y","z");
        pset.addPredicate(abc);
        pset.addPredicate(abc);
        pset.addPredicate(abc2);
        pset.addPredicate(xyz);      
        assertEquals(pset.size(),2);
    }

    @Test
    public void testRemovePredicate() {
        PredicateSet pset = new PredicateSet();
        Predicate abc = new Predicate("a","b","c");
        Predicate abc2 = new Predicate("a","b","c");
        Predicate xyz = new Predicate("x","y","z");
        pset.addPredicate(abc);
        pset.removePredicate(xyz);
        assertEquals(pset.size(),1);
        pset.addPredicate(xyz);
        assertEquals(pset.size(),2);
        pset.removePredicate(xyz);
        assertEquals(pset.size(),1);
    }

    @Test
    public void testContains() {
        PredicateSet pset = new PredicateSet();
        Predicate abc = new Predicate("a","b","c");
        Predicate xyz = new Predicate("x","y","z");
        pset.addPredicate(abc);
        assertTrue(!pset.contains(xyz));
        assertTrue(pset.contains(abc));
    }

    @Test
    public void testFindAll() {
        PredicateSet pset = new PredicateSet();
        Predicate abc = new Predicate("a","b","c");
        Predicate ab = new Predicate("a","b");
        Predicate aee = new Predicate("a","e","e");
        Predicate xyz = new Predicate("x","y","z");
        pset.addPredicate(abc);
        pset.addPredicate(ab);
        pset.addPredicate(aee);
        pset.addPredicate(xyz);
        assertEquals(3, pset.findAll("a").size());
        assertEquals(1, pset.findAll("x").size());
        assertEquals(0, pset.findAll("b").size());
    }
    
    @Test
    public void testEquals(){
        PredicateSet pset = new PredicateSet();
        PredicateSet pset2 = new PredicateSet();
        Predicate abc = new Predicate("a","b","c");
        Predicate ab = new Predicate("a","b");
        pset.addPredicate(ab);
        pset.addPredicate(abc);
        pset2.addPredicate(abc);
        pset2.addPredicate(ab);
        assertTrue(pset.equals(pset2));
        pset.removePredicate(ab);
        assertFalse(pset.equals(pset2));
        assertFalse(pset2.equals(pset));
        assertTrue(pset.equals(pset));
    }
    
    @Test
    public void testUnion(){
        PredicateSet ps1 = new PredicateSet();
        Predicate aaa = new Predicate("a","a","a");
        ps1.addPredicate(aaa);
        PredicateSet ps2 = new PredicateSet();
        Predicate bbb = new Predicate("b","b","b");
        ps2.addPredicate(bbb);
        ps1.union(ps2);
        assertEquals(ps1.size(),2); 
    }
    
}
