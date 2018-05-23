/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.myUtils;

import java.util.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import myUtils.*;

/**
 *
 * @author Riccardo
 */
public class ListComparerTest {
    
    List<String> ls1;
    List<String> ls2;
    List<String> ls3;
    String[] a1;
    String[] a2;
    String[] a3;
    
    public ListComparerTest() {
        ls1 = new LinkedList<>();
        ls2 = new LinkedList<>();
        ls3 = new LinkedList<>();
        ls1.add("abc");
        ls1.add("def");
        ls2.add("abc");
        ls2.add("def");
        ls3.add("xyz");
        ls3.add("abc");
        a1 = new String[]{"abc","def"};
        a2 = new String[]{"xyz","xyz"};
        a3 = new String[]{"abc"};
    }

    /**
     * Test of compareToList method, of class ListComparer.
     */
    @Test
    public void testCompareToList() {
        assertTrue(ListUtil.listEquals(ls1, ls2));
        assertFalse(ListUtil.listEquals(ls1,ls3));
    }

    /**
     * Test of compareToArray method, of class ListComparer.
     */
    @Test
    public void testCompareToArray() {
        assertTrue(ListUtil.listArrayEquals(ls1, a1));
        assertFalse(ListUtil.listArrayEquals(ls1, a2));
        assertFalse(ListUtil.listArrayEquals(ls1, a3));
    }
    
}
