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
public class ListUtils {
    
    public static <T> boolean listEquals (List<T> ls1, List<T> ls2){
        if (ls1.size()!= ls2.size()){
            return false;
        }
        for (int i=0; i<ls1.size(); i++){
            if (!ls1.get(i).equals(ls2.get(i)))
                return false;
        }
        return true;
    }
    
    public static <T> boolean listArrayEquals (List<T> ls1, T[] array){
        List<T> ls2 = Arrays.asList(array);
           return ListUtils.listEquals(ls1,ls2);
    }
    
    public static <T> String listToString (List<T> ls){
        String s = "list: [";
        s = ls.stream().map((item) -> item.toString()+"; ").reduce(s, String::concat);
        s = s.substring(0, s.length() - 2);
        s += "]";
        return s;
    }
    
    public static <T> String listToString (List<T> ls, boolean newline){
        String s = "list: [";
        if (newline) {
        s = ls.stream().map((item) -> item.toString()+";\r\n").reduce(s, String::concat);
        s = s.substring(0, s.length() - 2);
        s += "]";
        return s;
        }
        s = ls.stream().map((item) -> item.toString()+";").reduce(s, String::concat);
        s = s.substring(0, s.length() - 1);
        s += "]";
        return s;
    }
    
    public static <T> void printList(List<T> ls){
        System.out.println("list size: "+ls.size());
        System.out.println(ListUtils.listToString(ls));
    }
    
    public static <T> void printList(List<T> ls, boolean newline){
        System.out.println("list size: "+ls.size());
        System.out.println(ListUtils.listToString(ls, newline));
    }
    
    public static <T> T pickRandom(List<T> ls) {
    	SharedRandom srnd = SharedRandom.getInstance();
    	return ls.get(srnd.nextInt(ls.size()));
    }
    
}
