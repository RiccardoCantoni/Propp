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

import TextGeneration.TextDictionary;
import TextGeneration.TextGenerator;
import myUtils.JsonDataManager;
import myUtils.ListUtil;
import myUtils.SharedRandom;
import plotGeneration.KnownSequence;
import plotGeneration.MultiPlotGenerator;
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
    	ChainUpdater.updateAllChains();   	
    	List<Node> ls = new LinkedList();
    	TextDictionary TD = new TextDictionary();
    	TD.updateDictionary("dictionary.csv");    	
        SharedRandom.getInstance().setRandom();
        TD.loadFromCSV("dictionary.csv");
        for (int i = 0; i<1; i++) {
        	ls = walk();
        }
        TextGenerator textgen = new TextGenerator();
        String text = textgen.generateText(ls);
        System.out.println("SEED: "+SharedRandom.getInstance().getSeed());
        System.out.print(text);
    }
    
    public static List<Node> walk() {
        PlotArgument arg = new PlotArgument(KnownSequence.MAIN_SEQUENCE.getSequence(), new State(), new String[0]);
        MultiPlotGenerator multigen = new MultiPlotGenerator(arg, 0);
        List<Node> plot = multigen.generate();
        return plot;
    }
    
    public static List<Node> plotContaining(String s){
    	List<Node> ls = new LinkedList<Node>();
    	while(true) {
    		ls = walk();
    		if (NodeSequenceManager.getLabelSequence(ls).contains(s)) return ls;
    	}
    }
    
    public static void analyzeState(List<String> chains, List<String>in, List<String> out) {
    	JsonDataManager jdm = new JsonDataManager("data.json");
    	JsonArray a = jdm.loadArray("chains");
    	ChainAnalyzer ca;
    	FunctionChain fc;
    	String chainName;
    	for (JsonObject o : a.getValuesAs(JsonObject.class)) {
    		chainName = o.getString("name");	
        	ca = new ChainAnalyzer(FunctionChain.deserializeFrom(chainName));
        	chains.add(chainName);
        	System.out.println("chain: "+chainName);
        	List<String> tmp = ca.getRequiredPredicates().stream().map(p -> p.toString()).collect(Collectors.toList());
        	in.addAll(tmp);
        	ListUtil.printList(tmp, false);
        	tmp = ca.getAddedPredicates().stream().map(p -> p.toString()).collect(Collectors.toList());
        	ListUtil.printList(tmp, false);
        	out.addAll(tmp);
    	}
    	List<String> inLackingOut = in;
    	inLackingOut.removeAll(out);
    	List<String> outLackingIn = out;
    	outLackingIn.removeAll(in);
    	System.out.println("produces lacking consumer");
    	ListUtil.printList(outLackingIn, false);
    	System.out.println("consumers lacking producer");
    	ListUtil.printList(inLackingOut, false);
    	
    }
    
}
