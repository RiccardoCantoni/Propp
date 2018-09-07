package plotGeneration;

import java.util.LinkedList;
import java.util.List;

import myUtils.DebugUtils;
import myUtils.ListUtils;
import myUtils.LogManager;
import myUtils.SharedRandom;
import propp.NodeSequenceManager;
import propp.SystemState;
import proppFunction.GraphExplorationException;
import proppFunction.Node;
import proppFunction.NodeType;

public class MultiPlotGenerator {
	
	int MAX_DEPTH = 15;
	
	PlotArgument arg0;
	int depth;
	
	public MultiPlotGenerator(PlotArgument arg0, int depth) {
		this.arg0 = arg0;	
		this.depth = depth;
	}
	
	public List<Node> generate(){	
		ImpasseScanner scanner = new ImpasseScanner();
		ImpasseHandler handler = new ImpasseHandler();
		PlotArgument subarg = null;
		List<Node> fullPlot = new LinkedList<Node>();
		if (depth == MAX_DEPTH) { //loop detection
			throw new IllegalStateException("seed:"+SharedRandom.getInstance().getSeed());   
		}
		List<Node> chainPlot = new LinkedList<Node>();
		LinearPlotGenerator lingen = new LinearPlotGenerator(arg0);
		PlotGenerationState pgs;
    	lingen.generate();
		while(true) {	
			pgs = lingen.getGenerationState();	
			chainPlot = lingen.getPlot();
			DebugUtils.debugPrintList(NodeSequenceManager.getLabelSequence(chainPlot));
			for (Node n: chainPlot) {
				fullPlot.add(n);
			}
			Impasse impasse = scanner.scanPath(chainPlot, lingen.getState());
			subarg = handler.handleImpasse(impasse);
			if (subarg!=null) {
				MultiPlotGenerator subplotGenerator = new MultiPlotGenerator(subarg, depth+1);
				DebugUtils.debugPrint("NEW SUBPLOT, depth: "+(depth+1));
				fullPlot.addAll(subplotGenerator.generate());
				lingen.state.removePredicate(impasse.predicate);
				DebugUtils.debugPrint("SUBPLOT RESOLVED, depth: "+(depth+1));
			}
			if (pgs == PlotGenerationState.COMPLETED)
				break;
			lingen.nextFunction();
			lingen.resumeGeneration();
		}
		if (depth==0) DebugUtils.debugPrint("=== plot generation terminated ===");
		return fullPlot;
	}

}
