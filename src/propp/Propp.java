/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp;

import graph.*;
import propp.chains.*;

/**
 *
 * @author Riccardo
 */
public class Propp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ChainGenerator a = new ChainF();
        a.createSave();
        SharedRandom srand = SharedRandom.getInstance();
        srand.setRandom();
        
        FunctionChain C = FunctionChain.deserializeFrom("F");
        
        MarkovWalker mw = new MarkovWalker(C, new AleatoryTransition());
        Node x;
        while(mw.hasNext()){
            x = mw.next();
            x.prettyPrint();
        }
        mw.state.prettyPrint();
                 
    }
    
}
