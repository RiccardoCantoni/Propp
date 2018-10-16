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
public class Configuration {
    
    private static Configuration instance;
    
    public boolean loggingMode;
    public boolean unconstrained_mode;
    public MarkovTransition transition_function;
    public boolean debugMode;
    public boolean globalFrequencyActive;
    public boolean outputEnabled;
    
    public String functions_data_location;
    public String existents_data_location;
    public String activity_mapping_location;
    public String text_dictionary_location;
    
    private Configuration() {
    	outputEnabled = (boolean)ConfigurationManager.getConfig(boolean.class, "output"); 
        loggingMode = (boolean)ConfigurationManager.getConfig(boolean.class, "logging");
        unconstrained_mode = (boolean)ConfigurationManager.getConfig(boolean.class, "unconstrained_mode");
        transition_function = this.getTransitionFunction((String)ConfigurationManager.getConfig(String.class, "transition_type"));
        debugMode = (boolean)ConfigurationManager.getConfig(boolean.class, "debug_mode");
        globalFrequencyActive = (boolean)ConfigurationManager.getConfig(boolean.class, "global_frequency_active");
        
        functions_data_location = (String)ConfigurationManager.getConfig(String.class, "functions_data_location");
        existents_data_location = (String)ConfigurationManager.getConfig(String.class, "existents_data_location");
        activity_mapping_location = (String)ConfigurationManager.getConfig(String.class, "activity_mapping_location");
        text_dictionary_location = (String)ConfigurationManager.getConfig(String.class, "text_dictionary_location");
    }
    
    public static Configuration getInstance() {
        if(instance == null) {
          instance = new Configuration();
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
