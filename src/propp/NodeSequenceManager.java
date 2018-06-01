/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp;

import java.util.List;
import java.util.stream.Collectors;

import myUtils.StringMatcher;
import proppFunction.Node;
import proppFunction.NodeType;

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
    
    public static boolean containsInjections(List<Node> seq, List<String>injections) {
    	Node i = new Node("", NodeType.NONE);
    	for (String s : injections) {
    		i.label = s;
    		if (!seq.contains(i)) 
    			return false;
    	}
    	return true;
    }
  
}
