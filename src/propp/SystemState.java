/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp;

import myUtils.ConfigurationManager;
import proppFunction.MarkovTransition;
import proppFunction.PickFirstTransition;
import proppFunction.RandomTransition;

/**
 *
 * @author Riccardo
 */
public class SystemState {
    
    private static SystemState instance;
    
    public boolean loggingMode = false;
    public boolean unconstrained_mode = false;
    public MarkovTransition transition_function;
    
    private SystemState() {
        loggingMode = (boolean)ConfigurationManager.getConfig(boolean.class, "logging");
        unconstrained_mode = (boolean)ConfigurationManager.getConfig(boolean.class, "unconstrained_mode");
        transition_function = this.getTransitionFunction((String)ConfigurationManager.getConfig(String.class, "transition_type"));
    }
    
    public static SystemState getInstance() {
        if(instance == null) {
          instance = new SystemState();
        }
        return instance;
    }
    
    private MarkovTransition getTransitionFunction(String arg) {
    	if (arg.equals("random"))
    		return new RandomTransition();
    	if (arg.equals("pickFirst"))
    		return new PickFirstTransition();
    	else
    		throw new IllegalArgumentException("config: unrecognised transition type");
    }
    
}
