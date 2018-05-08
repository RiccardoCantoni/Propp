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
public class ChainG implements ChainGenerator{

    @Override
    public void createSave() {
        FunctionChain C = new FunctionChain();
        
        
        C.serializeAs("G");
    }
    
}
