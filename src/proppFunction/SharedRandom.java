/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proppFunction;

import java.util.Random;

/**
 *
 * @author Riccardo
 */
public class SharedRandom {
    
    private boolean seedSet;
    private Random rnd;
    private long currentSeed;
    private static SharedRandom instance = null;
    
    private SharedRandom() {
        seedSet = false;
        rnd = new Random();
    }
    
    public static SharedRandom getInstance() {
        if(instance == null) {
          instance = new SharedRandom();
        }
        return instance;
    }
    
    public void setSeed(long seed){
        rnd.setSeed(seed);
        currentSeed = seed;
        seedSet = true;
    }
    
    public void setRandom(){
        rnd = new Random();
        seedSet = true;
    }
    
    public int nextInt(int upperBound){
        if (!seedSet)
            throw new IllegalStateException("shared random generator not initialized");
        return rnd.nextInt(upperBound);
    }
    
    public float nextFloat(){
        if (!seedSet)
            throw new IllegalStateException("shared random generator not initialized");
        return rnd.nextFloat();
    }

    public long getSeed(){
        if (!seedSet)
            throw new IllegalStateException("shared random generator not initialized");
        return currentSeed;
    }
    

    
    
    
    
}
