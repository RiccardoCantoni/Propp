/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myUtils;

import org.junit.Test;
import static org.junit.Assert.*;

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
        assertTrue(StringMatcher.beginsWith("$ciao", "\\$"));
        assertTrue(StringMatcher.beginsWith("$", "\\$"));
        assertFalse(StringMatcher.beginsWith("ciao", "\\$"));
        assertFalse(StringMatcher.beginsWith("", "\\$"));
    }
    
}
