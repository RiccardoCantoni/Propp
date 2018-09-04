/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myUtils;

import java.util.Arrays;
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
    
    public long getSeed(){
        if (!seedSet)
            throw new IllegalStateException("shared random generator not initialized");
        return currentSeed;
    }
    
    public void setRandom(){
        rnd = new Random();
        Long l = rnd.nextLong();
        setSeed(l);
    }
    
    public int nextInt(int upperBoundExclusive){
        if (!seedSet)
            throw new IllegalStateException("shared random generator not initialized");
        return rnd.nextInt(upperBoundExclusive);
    }
    
    public float nextFloat(){
        if (!seedSet)
            throw new IllegalStateException("shared random generator not initialized");
        return rnd.nextFloat();
    }
    
    public double nextDouble() {
        if (!seedSet)
            throw new IllegalStateException("shared random generator not initialized");
        return rnd.nextDouble();    	
    }

    public int roulette(float[] probabilities) {
    	float sum = 0;
    	for (int i=0; i<probabilities.length; i++) {
    		sum+=probabilities[i];
    	}
    	float x = this.nextFloat()*sum;
    	float acc = 0;
    	for (int i=0; i<probabilities.length; i++) {
    		acc+=probabilities[i];
    		if (x<=acc) 
    			return i;
    	}
    	return -1;
    	
    }
			
    

    
    
    
    
}
