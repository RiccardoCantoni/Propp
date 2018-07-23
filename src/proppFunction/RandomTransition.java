/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proppFunction;

import java.util.List;

import myUtils.SharedRandom;

/**
 *
 * @author Riccardo
 */
public class RandomTransition implements MarkovTransition{
    
	SharedRandom rnd;
	
    public RandomTransition(){
        rnd = SharedRandom.getInstance();
    }

    @Override
    public Node nextNode(List<Node> validSuccessors) {
    	return validSuccessors.get(rnd.nextInt(validSuccessors.size()));
    }
    
}
