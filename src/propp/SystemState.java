/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp;

import myUtils.ConfigurationManager;

/**
 *
 * @author Riccardo
 */
public class SystemState {
    
    private static SystemState instance;
    
    public boolean loggingMode = false;
    public boolean unconstrained_mode = false;
    
    private SystemState() {
        loggingMode = (boolean)ConfigurationManager.getConfig(boolean.class, "logging");
        unconstrained_mode = (boolean)ConfigurationManager.getConfig(boolean.class, "unconstrained_mode");
    }
    
    public static SystemState getInstance() {
        if(instance == null) {
          instance = new SystemState();
        }
        return instance;
    }
    
}
