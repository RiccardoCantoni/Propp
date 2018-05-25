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
public class PickFirstTransition implements MarkovTransition{

    @Override
    public Node nextNode(List<Node> successors) {
        if (successors.isEmpty()) 
            throw new GraphExplorationException("markov transition failed");
        return successors.get(0);
    }
    
}
