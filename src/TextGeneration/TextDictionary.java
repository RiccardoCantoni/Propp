package TextGeneration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.json.JsonArray;
import javax.json.JsonObject;

import myUtils.JsonDataManager;
import myUtils.ListUtil;
import propp.chains.ChainAnalyzer;
import proppFunction.FunctionChain;
import proppFunction.SharedRandom;

public class TextDictionary {
	
	private Map <String, String[]> tdict;
	private String filename;
	
	public TextDictionary(String filename) {
		tdict = new HashMap<String, String[]>();
		this.filename = filename;
	}
	
	public void mockDict() {
		tdict.put("la", new String[] {"x","y","z"});
		tdict.put("lb", new String[] {});
		tdict.put("lc", new String[] {"t","t"});
		unloadToCSV(filename);
	}
	
	public void unloadToCSV(String filename) {
		try {
			PrintWriter pw = new PrintWriter(new File(filename));
			for (String s : tdict.keySet()) {
		        StringBuilder sb = new StringBuilder();
		        sb.append(s);
		        for (String ss : tdict.get(s)) {
		        	sb.append(","+ss);
		        }
		        sb.append('\n');
		        pw.write(sb.toString());
			}
	        pw.close();
	        System.out.println("dictionary unloaded to "+filename);
		}catch(Exception e) {
			System.out.println("unloading failed");
			e.printStackTrace();
		}	
	}
	
	public void loadFromCSV(String filename) {
		BufferedReader br;
		String line;
		String[] split, value;
		try {
	        br = new BufferedReader(new FileReader(filename));
	        while ((line = br.readLine()) != null) {
	            split = line.split(";");
	            value = new String[split.length-1];
	            System.arraycopy(split, 1, value, 0, split.length-1);
	            tdict.put(split[0], value);
	        }
        }catch(Exception e){
        	System.out.println("dictionary loading failed");
        	e.printStackTrace();
        }	
	}

	public String[] getTexts(String label) {
		return tdict.get(label);
	}
	
	public String getRandomText(String label) {
		SharedRandom rnd = SharedRandom.getInstance();
		String[] txs = getTexts(label);
		return txs[rnd.nextInt(txs.length)];
	}
	
	public void updateDictionary() {
		//List<String> chainLabels = getAllLabels();
		List<String> chainLabels = Arrays.asList(new String[] {
				"la",
				"lb",
				"lc",
				"ld"
		});
		loadFromCSV(filename);
		Map <String, String[]> newdict = new HashMap<String, String[]>();
		for (String label : chainLabels) {
			if (tdict.containsKey(label)) {
				newdict.put(label, tdict.get(label));
			}else {
				newdict.put(label, new String[0]);
			}
		}
		tdict = newdict;
		unloadToCSV(filename);
	}
	
	private static List<String> getAllLabels(){
    	List<String> labels = new LinkedList<>();
    	ChainAnalyzer ca;
    	JsonDataManager jdm = new JsonDataManager("data.json");
    	JsonArray chains = jdm.loadArray("chains");
    	for (JsonObject o : chains.getValuesAs(JsonObject.class)) {
    		ca = new ChainAnalyzer(FunctionChain.deserializeFrom(o.getString("name")));
    		labels.addAll(ca.getLabels());
    	}
    	return labels;
    }
	
}
