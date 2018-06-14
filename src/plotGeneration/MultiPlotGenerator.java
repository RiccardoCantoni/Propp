package plotGeneration;

import java.util.LinkedList;
import java.util.List;

import proppFunction.Node;

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
				break;
			}			
			if (pgs==PlotGenerationState.FUNCTION_TERMINATED) {
				chainPlot = lingen.getPlot();
				fullPlot.addAll(chainPlot);
				if (scanner.scanPath(chainPlot, lingen.getState())) {
					subarg = handler.handleImpasse(chainPlot, lingen.getState());
					if (subarg!=null) {
						//TODO SUBPLOT
					}
				}
				lingen.nextFunction();
				lingen.resumeGeneration();
				continue;
			}
		}
		
		return fullPlot;
	}

}
