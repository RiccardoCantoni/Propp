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
    
    PredicateSet pset;
    Predicate abc, abd, xyz, ab;
    
    @Before 
    public void setup(){
        pset = new PredicateSet();
        abc = new Predicate("a","b","c");
        abd = new Predicate("a","b","d");
        ab = new Predicate("a","b");
        xyz = new Predicate("x","y","z");
    }
    
    @After
    public void clear(){
        pset = new PredicateSet();
    }
    
    @Test
    public void testContains() {
        pset.addPredicate(abc);
        assertFalse(pset.contains(xyz));
        assertFalse(pset.contains(abd));
        assertTrue(pset.contains(abc));
        assertTrue(pset.contains(ab));
    }
    
    @Test
    public void testAddPredicate() {
        pset.addPredicate(abc);
        pset.addPredicate(abd);
        pset.addPredicate(ab);
        pset.addPredicate(xyz);  
        pset.prettyPrint();
        assertEquals(3,pset.size());
        assertTrue(pset.contains(abc));
        assertTrue(pset.contains(xyz));
        pset.addPredicate(abd);
        assertEquals(pset.size(),3);
        assertTrue(pset.contains(abd));
        
    }

    @Test
    public void testRemovePredicate() {
        pset.addPredicate(abc);
        pset.addPredicate(abd);
        pset.removeAllMatching(xyz);
        assertEquals(pset.size(),2);
        pset.addPredicate(xyz);
        assertEquals(pset.size(),3);
        pset.removeAllMatching(ab);
        assertEquals(pset.size(),1);
    }



    /*@Test
    public void testFindAll() {
        PredicateSet pset = new PredicateSet();
        Predicate abc = new Predicate("a","b","c");
        Predicate ab = new Predicate("a","b","d");
        Predicate aee = new Predicate("a","e","e");
        Predicate xyz = new Predicate("x","y","z");
        pset.addPredicate(abc);
        pset.addPredicate(ab);
        pset.addPredicate(aee);
        pset.addPredicate(xyz);
        for (Predicate p : pset.findAll(new Predicate("a","_","_"))){
            System.out.print(p.toString());
        }
        assertEquals(3, pset.findAll(new Predicate("a","_","_")).size());
        assertEquals(1, pset.findAll(new Predicate("x","_","_")).size());
        assertEquals(0, pset.findAll(new Predicate("b","_","_")).size());
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
    }*/
    
}
