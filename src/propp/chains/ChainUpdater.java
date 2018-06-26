/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propp.chains;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;

import myUtils.JsonDataManager;
import proppFunction.FunctionChain;
import state.AtomMatcher;
import state.Predicate;
import state.PredicateMatcher;
import state.PredicateSet;

/**
 * @author Riccardo
 */
public class ChainUpdater {
    
    public static void updateAllChains(){ 	
    	JsonDataManager jdm = new JsonDataManager("data.json");
    	JsonArray a = jdm.loadArray("chains");
    	for (JsonObject o : a.getValuesAs(JsonObject.class)) {
    		updateChain(o.getString("name"));
    	}
    	
        new TestChainSmall().createSave();
        
        System.out.println((a.size()+1)+" chains updated successfully");
        
        //checkPredicateConsistency();
    }
    
    private static void checkPredicateConsistency() {
    	throw new UnsupportedOperationException("legacy method, needs to be reimplemented");
    	/*ChainAnalyzer ca;
    	PredicateMatcher m;
    	PredicateSet added = new PredicateSet();
    	List<Predicate> required = new LinkedList<>();
    	List<FunctionChain> chainSeq = Arrays.asList(defaultSequence());
    	for (FunctionChain c : chainSeq) {
    		ca = new ChainAnalyzer(c);
    		for (Predicate p : ca.getAddedPredicates()) {
    			added.addPredicate(p);
    		}
    		required.addAll(ca.getRequiredPredicates());
    	}
    	for (Predicate req : required) {
    		m = new AtomMatcher(req);
    		if (!m.matchAny(added)) { 
    			throw new IllegalArgumentException("precondition contains unknown predicate: "+req.toString());
    		}
    	}*/
    }
    
    
    private static void updateChain(String chainName) {
    	chainName = "propp.chains."+chainName+"Chain";
    	ChainGenerator fc = null;
    	try {
    		Class c = Class.forName(chainName);
    		Constructor constructor = c.getConstructor();
    		fc = (ChainGenerator)constructor.newInstance();
    	}catch(Exception e) {
    		System.out.println("reflexion error");
    		e.printStackTrace();
    	}
    	fc.createSave();
    }
   
}
