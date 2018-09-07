package plotGeneration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

import myUtils.JsonManager;
import propp.chains.ChainAnalyzer;
import proppFunction.FunctionChain;
import proppFunction.Node;

public class FrequencyDB {
	
	private static FrequencyDB instance;
	
	private Map<String, Integer> localFrequencies;
	private Map<String, Integer> globalFrequencies;
	private List<String> labels;
		
	private FrequencyDB() {
		instance = null;
		labels = getAllLabels();
		localFrequencies = new HashMap<String, Integer>();
		globalFrequencies = new HashMap<String, Integer>();
		resetLocalFrequencies();
		loadGlobalFrequencies();
	}
	
    public static FrequencyDB getInstance() {
        if(instance == null) {
          instance = new FrequencyDB();
        }
        return instance;
    }
    
    public int getLocalFrequency(Node n) {
    	return this.localFrequencies.get(n.label);
    }
    
    public int getGlobalFrequency(Node n) {
    	return this.globalFrequencies.get(n.label);
    }
    
    public void updateLocalFrequency(Node n) {
    	try {
	    	int i = localFrequencies.get(n.label);
	    	localFrequencies.put(n.label, i+1);
    	}catch(Exception e) {
			System.out.println(n.label);
			e.printStackTrace();
		}
    }
    
    public void updateGlobalFrequency(Node n) {
    	try {
	    	int i = globalFrequencies.get(n.label);
	    	globalFrequencies.put(n.label, i+1);
    	}catch(Exception e) {
			System.out.println(n.label);
			e.printStackTrace();
		}
    }
        
    public void resetLocalFrequencies() {
		for (String s : labels) {
			this.localFrequencies.put(s, 1);
			this.localFrequencies.put("$entry_point", 1);
		}
    }
    
    public void resetGlobalFrequencies() {
		for (String s : labels) {
			this.globalFrequencies.put(s, 1);
			this.globalFrequencies.put("$entry_point", 1);
		}
    }
   
    public void loadGlobalFrequencies() {
    	JsonManager jm = new JsonManager("frequencies.json");
    	JsonArray ja = jm.loadArray("frequencies");
    	String label;
    	int f;
    	for (JsonObject o : ja.getValuesAs(JsonObject.class)) {
    		label = o.getString("label");
    		f = o.getInt("frequency");
    		this.globalFrequencies.put(label, f);
    	}
    }
    
    public void saveGlobalFrequencies() {
    	JsonArrayBuilder ab = Json.createArrayBuilder();
    	JsonObjectBuilder ob;
    	for (Map.Entry<String, Integer> entry : globalFrequencies.entrySet())
    	{
    	    ob = Json.createObjectBuilder();
    	    ob.add("label", entry.getKey());
    	    ob.add("frequency", entry.getValue());
    	    ab.add(ob);
    	}
    	JsonArray a = ab.build();
    	ob = Json.createObjectBuilder();
    	ob.add("frequencies", a);
    	JsonObject obj = ob.build();
    	try {
	    	JsonWriter writer = Json.createWriter(new PrintWriter(new File("frequencies.json")));
	    	writer.writeObject(obj);
	    	writer.close();
    	}catch(Exception e) {
    		System.out.println("frequency DB update failed");
    		e.printStackTrace();
    	}
    }
    
    private static List<String> getAllLabels(){
    	List<String> labels = new LinkedList<>();
    	ChainAnalyzer ca;
    	JsonManager jdm = new JsonManager("data.json");
    	JsonArray chains = jdm.loadArray("chains");
    	for (JsonObject o : chains.getValuesAs(JsonObject.class)) {
    		ca = new ChainAnalyzer(FunctionChain.deserializeFrom(o.getString("name")));
    		for (String label : ca.getLabels()) {
    			if (labels.contains(label)) {
    				System.out.println("WARNING: duplicate label "+label);
    			}else {
    				labels.add(label);
    			}
    		}
    	}
    	labels.add("$entry_point");
    	return labels;
    }
    
}


