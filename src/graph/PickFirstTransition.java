/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/**
 *
 * @author Riccardo
 */
public class PickFirstTransition implements MarkovTransition{

    @Override
    public Node nextNode(Node n) {
        if (n.outdegree()==0) 
            throw new GraphExplorationException("markov transition failed");
        return n.successors.get(0);
    }
    
}
