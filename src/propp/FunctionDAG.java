package propp;

import genericGraph.DirectedAcyclicGraph;

public class FunctionDAG {
	
	/*String rd = "ReconDelivery";
	String Aa = "VillainyLack";
	String BC = "MediationCounteraction";
	String DE = "FirstFunctionReaction";
	String F = "Acquisition";
	String G = "Guidance";
	String HJ = "StruggleBranding";
	String R = "Return";
	String K = "Liquidation";*/
	
	String rd = "rd";
	String Aa = "Aa";
	String BC = "BC";
	String DE = "DE";
	String F = "F";
	String G = "G";
	String HJ = "HJ";
	String R = "R";
	String K = "K";
	
	DirectedAcyclicGraph<String> dag;
	
	private void resetDAG() {
		dag = new DirectedAcyclicGraph<>();
		dag.addNode(rd);
		dag.addNode(Aa);
		dag.addNode(BC);
		dag.addNode(DE);
		dag.addNode(F);
		dag.addNode(G);
		dag.addNode(HJ);
		dag.addNode(R);
		dag.addNode(K);
	}
	
	public void updateLogicConstraints() {
		resetDAG();
		dag.addEdge(Aa, BC);
		dag.addEdge(Aa, rd);
		dag.addEdge(Aa, HJ);
		dag.addEdge(Aa, K);
		dag.addEdge(K, R);
		dag.addEdge(HJ, K);
		dag.addEdge(DE, HJ);
		dag.addEdge(DE, F);
		dag.addEdge(F, G);
		
		dag.serializeAs("LogicDependencies");
	}
	
	public void updateTimeConstraints() {
		resetDAG();
		dag.addEdge(rd, Aa);
		dag.addEdge(DE, F);
		dag.addEdge(Aa, BC);
		dag.addEdge(BC, DE);
		dag.addEdge(BC, HJ);
		dag.addEdge(HJ, K);
		dag.addEdge(K, R);
		dag.addEdge(HJ, K);
		dag.addEdge(F, G);
		dag.addEdge(G, HJ);

		dag.serializeAs("TimeDependencies");
	}
	
}
