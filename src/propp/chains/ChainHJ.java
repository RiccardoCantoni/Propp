/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp.chains;

import graph.*;
import state.*;

/**
 *
 * @author Riccardo
 */
public class ChainHJ implements ChainGenerator{

    @Override
    public void createSave() {
        FunctionChain C = new FunctionChain();
        C.FunctionName = "HJ";
        Node n;
        n=new Node("fight", NodeType.ACTION);
        C.addNode(n);
        C.setInitial(n);
        n=new Node("contest", NodeType.ACTION);
        C.addNode(n);
        C.setInitial(n);
        n=new Node("struggle_outcome_positive", NodeType.OUTCOME);
        C.addNode(n);
        n=new Node("struggle_outcome_negative", NodeType.OUTCOME);
        C.addNode(n);
        C.addEdge("fight","struggle_outcome_positive");
        C.addEdge("contest","struggle_outcome_positive");
        C.addEdge("fight","struggle_outcome_negative");
        C.addEdge("contest","struggle_outcome_negative");
        n=new Node("branding_mark", NodeType.EVENT);
        C.addNode(n);
        n=new Node("branding_item", NodeType.EVENT);
        C.addNode(n);
        C.addEdge("struggle_outcome_positive", "branding_mark");
        C.addEdge("struggle_outcome_positive", "branding_item");
        C.addEdge("struggle_outcome_negative", "branding_mark");     
        
        C.serializeAs("HJ");
    }
    
}
