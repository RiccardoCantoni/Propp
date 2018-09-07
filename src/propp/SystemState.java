/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp;

import myUtils.ConfigurationManager;
import proppFunction.InverseFrequencyTransition;
import proppFunction.MarkovTransition;
import proppFunction.PickFirstTransition;
import proppFunction.RandomTransition;

/**
 *
 * @author Riccardo
 */
public class SystemState {
    
    private static SystemState instance;
    
    public boolean loggingMode;
    public boolean unconstrained_mode;
    public MarkovTransition transition_function;
    public boolean debugMode;
    public String textDictionaryFile;
    public boolean globalFrequencyActive;
    
    private SystemState() {
        loggingMode = (boolean)ConfigurationManager.getConfig(boolean.class, "logging");
        unconstrained_mode = (boolean)ConfigurationManager.getConfig(boolean.class, "unconstrained_mode");
        transition_function = this.getTransitionFunction((String)ConfigurationManager.getConfig(String.class, "transition_type"));
        debugMode = (boolean)ConfigurationManager.getConfig(boolean.class, "debug_mode");
        textDictionaryFile = (String)ConfigurationManager.getConfig(String.class, "text_dictionary");
        globalFrequencyActive = (boolean)ConfigurationManager.getConfig(boolean.class, "global_frequency_active");
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
    	if (arg.equals("inverseFrequency"))
    		return new InverseFrequencyTransition();
    	throw new IllegalArgumentException("config: unrecognised transition type");
    }
    
}
