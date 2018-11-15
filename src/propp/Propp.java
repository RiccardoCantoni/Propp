/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.json.JsonArray;
import javax.json.JsonObject;

import TextGeneration.ActivityMapping;
import TextGeneration.Text;
import TextGeneration.TextDictionary;
import TextGeneration.TextGenerator;
import myUtils.JsonManager;
import myUtils.ListUtils;
import myUtils.SharedRandom;
import plotGeneration.FrequencyDB;
import plotGeneration.KnownSequence;
import plotGeneration.MultiPlotGenerator;
import plotGeneration.NodeSequenceManager;
import plotGeneration.PlotArgument;
import propp.chains.ChainAnalyzer;
import propp.chains.ChainUpdater;
import proppFunction.FunctionChain;
import proppFunction.Node;
import state.State;

/**
 *
 * @author Riccardo
 */
public class Propp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	initSystem();   	
    	String[] activities = ActivityMapping.getInstance().getLabels(new String[] {"EXERGAME2"});
        List<Node> plot = generatePlot(activities);
        Text text = generateText(plot);
       
        System.out.println("SEED: "+SharedRandom.getInstance().getSeed());
        System.out.println("==========");
        text.prettyPrint();
        JsonManager jm = new JsonManager("out.json");
        jm.saveOutput(text.body);
        FrequencyDB.getInstance().saveGlobalFrequencies();
    }
    
    public static void initSystem() {
    	//init consistem random number provider
    	SharedRandom.getInstance().setRandom();
    	//update propp functions
    	ChainUpdater.updateAllChains(); 
    	//init node frequency database
    	FrequencyDB.getInstance().loadGlobalFrequencies();
    	//update text dictionary
    	TextDictionary.getInstance().updateDictionary(Configuration.getInstance().text_dictionary_location);
    }
    
    public static List<Node> generatePlot(String[] activities) {
        PlotArgument arg = new PlotArgument(KnownSequence.MAIN_SEQUENCE.getSequence(), new State(), activities);
        MultiPlotGenerator multigen = new MultiPlotGenerator(arg, 0);
        List<Node> plot = multigen.generate();
        return plot;
    }
    
    public static Text generateText(List<Node> plotlist) {
    	TextGenerator textgen = new TextGenerator();
        return textgen.generateText(plotlist);
    }
     
    public static void analizeState(List<String> chains, List<String>in, List<String> out) {
    	JsonManager jdm = new JsonManager("data.json");
    	JsonArray a = jdm.loadArray("chains");
    	ChainAnalyzer ca;
    	String chainName;
    	for (JsonObject o : a.getValuesAs(JsonObject.class)) {
    		chainName = o.getString("name");	
        	ca = new ChainAnalyzer(FunctionChain.deserializeFrom(chainName));
        	chains.add(chainName);
        	System.out.println("chain: "+chainName);
        	List<String> tmp = ca.getRequiredPredicates().stream().map(p -> p.toString()).collect(Collectors.toList());
        	in.addAll(tmp);
        	ListUtils.printList(tmp, false);
        	tmp = ca.getAddedPredicates().stream().map(p -> p.toString()).collect(Collectors.toList());
        	ListUtils.printList(tmp, false);
        	out.addAll(tmp);
    	}
    	List<String> inLackingOut = in;
    	inLackingOut.removeAll(out);
    	List<String> outLackingIn = out;
    	outLackingIn.removeAll(in);
    	System.out.println("produces lacking consumer");
    	ListUtils.printList(outLackingIn, false);
    	System.out.println("consumers lacking producer");
    	ListUtils.printList(inLackingOut, false);
    	
    }
    
}
