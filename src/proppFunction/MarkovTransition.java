/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proppFunction;

import java.util.List;

/**
 *
 * @author Riccardo
 */
public interface MarkovTransition {
    
    public Node nextNode(List<Node> validSuccessors);
    
}
