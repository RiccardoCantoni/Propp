package plotGeneration;

import java.util.LinkedList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;

import myUtils.JsonManager;
import propp.Configuration;
import proppFunction.FunctionChain;
import proppFunction.MarkovTransition;
import state.State;

public class PlotArgument {
	
	public final String[] chainNames;
	public final FunctionChain[] chainSequence;
	public final State initialState;
	public final String[] injections;
	
	public PlotArgument(String[] chainNames, State initialState, String[] injections) {
		this.chainNames = chainNames;
		this.chainSequence = loadChains(); 
		this.initialState = initialState;
		this.injections = injections;
	}
	
	public PlotArgument(FunctionChain[] chainSequence, State initialState, String[] injections) {
		this.chainSequence = chainSequence;	
		this.chainNames = getNames();
		this.initialState = initialState;
		this.injections = injections;
	}	
	
	public FunctionChain[] loadChains() {
		List<FunctionChain> out = new LinkedList<>();
		FunctionChain[] allChains = loadAllChains();
		for (String s : chainNames) {
			for (FunctionChain c : allChains) {
				if (c.FunctionName.equals(s)) {
					out.add(c);
				}
			}
		}
		return out.toArray(new FunctionChain[chainNames.length]);
	}

	private String[] getNames() {
		List<String> out = new LinkedList<>();
		for (FunctionChain c : chainSequence) {
			out.add(c.FunctionName);
		}
		return out.toArray(new String[chainSequence.length]);
	}
	
	private static FunctionChain[] loadAllChains(){
    	List<FunctionChain> seq = new LinkedList<>();
    	JsonManager jdm = new JsonManager(Configuration.getInstance().functions_data_location);
    	JsonArray chains = jdm.loadArray("functions");
    	for (JsonObject o : chains.getValuesAs(JsonObject.class)) {
    		seq.add(FunctionChain.deserializeFrom(o.getString("name")));
    	}
    	return seq.toArray(new FunctionChain[seq.size()]);
    }
	
}
