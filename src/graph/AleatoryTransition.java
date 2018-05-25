/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.List;

/**
 *
 * @author Riccardo
 */
public class AleatoryTransition implements MarkovTransition{
    
	SharedRandom rnd;
	
    public AleatoryTransition(){
        rnd = SharedRandom.getInstance();
    }

    @Override
    public Node nextNode(List<Node> validSuccessors) {
    	return validSuccessors.get(rnd.nextInt(validSuccessors.size()));
    }
    
}
