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
    
    public static List<Node> clearLabelSequence(List<Node> seq){
        List<Node> newSeq = new LinkedList<>();
        for (Node n : seq){
            if (!(StringMatcher.beginsWith(n.label, "\\$"))){
                newSeq.add(n);
            }
        }
        return newSeq;
    }
    
}
