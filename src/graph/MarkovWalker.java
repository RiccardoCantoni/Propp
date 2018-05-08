/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;
import java.util.*;
import state.*;

/**
 *
 * @author Riccardo
 */
public class MarkovWalker implements Iterator<Node>{
    
    MarkovTransition trans;
    public FunctionChain chain;
    public Node current;
    public PredicateSet state;
    
    public MarkovWalker(FunctionChain c, MarkovTransition trans){
        this.trans = trans;
        this.chain = c;
        this.current = c.entryPoint;
        this.state = new PredicateSet();
    }
    
    public void setState(String s){
        current = chain.getNodeByLabel(s);
    }

    @Override
    public boolean hasNext() {
        return (current.outdegree()>0);
    }

    @Override
    public Node next() {
        Node n = trans.nextNode(current);
        current = n;
        state.union(current.toAdd);
        return n;
    }
   
}
