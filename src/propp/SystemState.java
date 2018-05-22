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
    
    private SystemState() {
        loggingMode = (boolean)ConfigurationManager.getConfig(boolean.class, "logging");
    }
    
    public static SystemState getInstance() {
        if(instance == null) {
          instance = new SystemState();
        }
        return instance;
    }
    
}
