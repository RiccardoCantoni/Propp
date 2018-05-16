/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp;

import graph.*;
import java.util.*;
import myUtils.StringMatcher;

/**
 *
 * @author Riccardo
 */
public class NodeSequenceManager {
    
    public static List<String> clearLabelSequence(List<String> seq){
        List<String> newSeq = new LinkedList<>();
        for (String n : seq){
            if (!(StringMatcher.matchPrefix(n, "\\$"))){
                newSeq.add(n);
            }
        }
        return newSeq;
    }
    
    public static List<String> getLabelSequence(List<Node> seq){
        List<String> newSeq = new LinkedList<>();
        for (Node n : seq){
            newSeq.add(n.label);
        }
        return newSeq;
    }
    
}
