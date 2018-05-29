/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proppFunction;

import java.io.Serializable;

/**
 *
 * @author Riccardo
 */
public class Edge implements Serializable{
    
    private String f,t;
    
    public Edge(String from, String to){
        this.f = from;
        this.t = to;
    }

    public String getNodeFrom() {
        return f;
    }

    public String getNodeTo() {
        return t;
    }
    
    
    
}
