/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proppFunction;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Riccardo
 */
public class NodeTree {
    
    private LabelTree lt;
    
    public NodeTree(Node root){
        lt = new LabelTree(root.label);
    }
    
    public void addLeaf(Node leaf, Node parent){
        lt.addLeaf(leaf.label, parent.label);
    }
    
    public List<Node> pathToRoot(Node leaf, FunctionChain g){
        List<Node> path = new LinkedList<>();
        List<String> labelPath = lt.pathToRoot(leaf.label);
        for (String s : labelPath){
            path.add(g.getNodeByLabel(s));
        }
        return path;
    }
    
}
