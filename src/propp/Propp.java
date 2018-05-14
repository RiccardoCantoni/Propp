/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp;

import graph.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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
        ChainUpdater.updateAllChains();
        SharedRandom srand = SharedRandom.getInstance();
        srand.setRandom();
        
        String[] a = new String[]{"a","b","c"};
        List<String> b = new LinkedList<>();
        b = Arrays.asList(a);
        for (String s : b){
            System.out.println(s);
        }
                 
    }
    
}
