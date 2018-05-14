/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp.chains;

/**
 *
 * @author Riccardo
 */
public class ChainUpdater {
    
    public static void updateAllChains(){
        ChainGenerator cg;
        cg = new ChainAa();
        cg.createSave();
        cg = new ChainBC();
        cg.createSave();
        cg = new ChainDE();
        cg.createSave();
        cg = new ChainF();
        cg.createSave();
        cg = new ChainG();
        cg.createSave();
        cg = new ChainH();
        cg.createSave();
        System.out.println("all chains updated successfully");
    }
    
}
