package plotGeneration;

import java.util.LinkedList;
import java.util.List;

import myUtils.ListUtil;
import propp.NodeSequenceManager;
import proppFunction.Node;
import proppFunction.NodeType;

public class MultiPlotGenerator {
	
	PlotArgument arg0;
	
	public MultiPlotGenerator(PlotArgument arg0) {
		this.arg0 = arg0;		
	}
	
	public List<Node> generate(){
		ImpasseScanner scanner = new ImpasseScanner();
		ImpasseHandler handler = new ImpasseHandler();
		PlotArgument subarg = null;
		List<Node> fullPlot = new LinkedList<Node>();
		List<Node> chainPlot = new LinkedList<Node>();
		LinearPlotGenerator lingen = new LinearPlotGenerator(arg0);
		PlotGenerationState pgs;
    	lingen.generate();
		while(true) {	
			pgs = lingen.getGenerationState();
			if (pgs==PlotGenerationState.COMPLETED) {
				chainPlot = lingen.getPlot();
				fullPlot.addAll(chainPlot);
				break;
			}			
			if (pgs==PlotGenerationState.FUNCTION_TERMINATED) {
				chainPlot = lingen.getPlot();
				fullPlot.addAll(chainPlot);
				Impasse impasse = scanner.scanPath(chainPlot, lingen.getState());
				subarg = handler.handleImpasse(impasse);
				if (subarg!=null) {
					MultiPlotGenerator subplotGenerator = new MultiPlotGenerator(subarg);
					fullPlot.add(new Node("SUBPLOT!", NodeType.NONE));
					fullPlot.addAll(subplotGenerator.generate());
					lingen.state.removePredicate(impasse.predicate);
					fullPlot.add(new Node("SUBPLOT RESOLVED", NodeType.NONE));
				}
				lingen.nextFunction();
				lingen.resumeGeneration();
			}
		}
		return fullPlot;
	}

}
