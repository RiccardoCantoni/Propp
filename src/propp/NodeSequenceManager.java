/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp;

import java.util.*;
import java.util.stream.Collectors;

import myUtils.StringMatcher;
import proppFunction.*;

/**
 *
 * @author Riccardo
 */
public class NodeSequenceManager {
    
    public static List<String> clearLabelSequence(List<String> seq){
        seq.removeIf(s->StringMatcher.matchPrefix(s, "\\$"));
        return seq;
    }
    
    public static List<String> getLabelSequence(List<Node> seq){
        List<String> labels = seq.stream().map(n->n.label).collect(Collectors.toList());
        return labels;
    }
  
}
