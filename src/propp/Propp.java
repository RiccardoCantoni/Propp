/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp;

import java.util.LinkedList;
import java.util.List;

import TextGeneration.TextDictionary;
import TextGeneration.TextGenerator;
import myUtils.ListUtil;
import myUtils.LogManager;
import plotGeneration.KnownSequence;
import plotGeneration.MultiPlotGenerator;
import plotGeneration.PlotArgument;
import propp.chains.ChainUpdater;
import proppFunction.Node;
import proppFunction.SharedRandom;
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
    	
    	List<Node> ls = new LinkedList();
    	TextDictionary TD = new TextDictionary();
    	TD.updateDictionary("dictionary.csv");
    	ChainUpdater.updateAllChains();
    	//-8952703234742336930l causes loop
        SharedRandom.getInstance().setSeed(-8952703234742336930l);
        
        TD.loadFromCSV("dictionary.csv");
        for (int i = 0; i<1; i++) {
        	ls = walk();
        }
        TextGenerator textgen = new TextGenerator();
        String text = textgen.generateText(ls);
        System.out.println("SEED: "+SharedRandom.getInstance().getSeed());
        //System.out.print(text);
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
    
}
