/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp.chains;
import proppFunction.ProppFunction;
import proppFunction.Node;
import proppFunction.NodeType;
import state.AtomMatcher;
import state.NotMatcher;
import state.Predicate;

/**
 * @author Riccardo
 */
public class MediationCounteractionChain implements FunctionGenerator{

    public void createSave() {
        ProppFunction C = new ProppFunction("MediationCounteraction");
        Node n; 
        String bc = "begin_counteraction";
        String dp = "departure";
        n = new Node(bc, NodeType.ACTION);
        C.addNode(n);
        n = new Node(dp, NodeType.ACTION);
        n.toAdd.addPredicate(new Predicate("departure","$hero"));
        C.addNode(n);
        C.addEdge(bc,dp);
        n = new Node("help_request", NodeType.ACTION);
        n.toAdd.addPredicate(new Predicate("help_request","dispatcher"));
        C.addNode(n);
        C.setInitial(n);
        n = new Node("help_request_ack", NodeType.PERCEPTION_INTERNAL);
        C.addNode(n);
        C.addEdge("help_request", "help_request_ack");
        n = new Node("help_response", NodeType.ACTION);
        C.addNode(n);
        C.addEdge("help_request_ack", "help_response");
        n = new Node("help_response_ack", NodeType.PERCEPTION_INTERNAL);
        C.addNode(n);
        C.addEdge("help_response", "help_response_ack");
        n = new Node("dispatch", NodeType.ACTION);
        n.toAdd.addPredicate(new Predicate("dispatch","$dispatcher","$hero"));
        n.preconditions = new NotMatcher(new AtomMatcher(new Predicate("lack","wealth","_")));
        C.addNode(n);
        C.setInitial(n);
        C.addEdge("help_response_ack", "dispatch");
        n = new Node("dispatch_ack", NodeType.PERCEPTION_INTERNAL);
        C.addNode(n);
        C.addEdge("dispatch", "dispatch_ack");
        C.addEdge("dispatch_ack",bc);
        n = new Node("realisation", NodeType.PERCEPTION);
        C.addNode(n);
        C.setInitial(n);
        n = new Node("initiative", NodeType.INTERNAL);
        C.addNode(n);
        C.addEdge("realisation", "initiative");
        C.addEdge("initiative", bc);
        n = new Node("misfortune_announcement", NodeType.ACTION);
        C.addNode(n);
        C.setInitial(n);
        n = new Node("misfortune_announcement_ack", NodeType.PERCEPTION_INTERNAL);
        C.addNode(n);
        C.addEdge("misfortune_announcement","misfortune_announcement_ack");
        C.addEdge("misfortune_announcement_ack",bc);
        
        C.serialize();
    }
    
}
