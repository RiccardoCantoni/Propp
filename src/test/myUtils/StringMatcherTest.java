/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.myUtils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import myUtils.StringMatcher;

/**
 *
 * @author Riccardo
 */
public class StringMatcherTest {
    
    /**
     * Test of beginsWith method, of class StringMatcher.
     */
    @Test
    public void testBeginsWith() {
        assertTrue(StringMatcher.matchPrefix("$ciao", "\\$"));
        assertTrue(StringMatcher.matchPrefix("$", "\\$"));
        assertFalse(StringMatcher.matchPrefix("ciao", "\\$"));
        assertFalse(StringMatcher.matchPrefix("", "\\$"));
    }
    
}
