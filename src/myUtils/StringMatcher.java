/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myUtils;

/**
 *
 * @author Riccardo
 */
public class StringMatcher {
    
    public static boolean beginsWith(String s, String c){
        String pattern = c+"(.*)";
        return s.matches(pattern);
    }
    
}
