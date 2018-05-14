/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myUtils;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Riccardo
 */
public class ListComparer {
    
    public static <T> boolean compareToList (List<T> ls1, List<T> ls2){
        if (ls1.size()!= ls2.size()){
            return false;
        }
        for (int i=0; i<ls1.size(); i++){
            if (!ls1.get(i).equals(ls2.get(i)))
                return false;
        }
        return true;
    }
    
    public static <T> boolean compareToArray (List<T> ls1, T[] array){
        List<T> ls2 = Arrays.asList(array);
           return ListComparer.compareToList(ls1,ls2);
    }
    
}
