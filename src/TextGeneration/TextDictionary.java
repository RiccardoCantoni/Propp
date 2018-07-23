package TextGeneration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.json.JsonArray;
import javax.json.JsonObject;

import myUtils.JsonDataManager;
import myUtils.SharedRandom;
import propp.SystemState;
import propp.chains.ChainAnalyzer;
import proppFunction.FunctionChain;

public class TextDictionary {
	
	private Map <String, String[]> tdict;
	private String splitchar = ";";
	
	public TextDictionary() {
		tdict = new HashMap<String, String[]>();
	}
	
	public void loadDictionary() {
		loadFromCSV(SystemState.getInstance().textDictionaryFile);
	}
	
	public void unloadToCSV(String filename) {
		try {
			PrintWriter pw = new PrintWriter(new File(filename));
			for (String s : tdict.keySet()) {
		        StringBuilder sb = new StringBuilder();
		        sb.append(s);
		        for (String ss : tdict.get(s)) {
		        	sb.append(splitchar+ss);
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
	            split = line.split(splitchar);
	            value = new String[split.length-1];
	            System.arraycopy(split, 1, value, 0, split.length-1);
	            tdict.put(split[0], value);
	        }
        }catch(Exception e){
        	System.out.println("dictionary loading failed");
        	e.printStackTrace();
        }	
		if (tdict.keySet().size() == 0) {
			System.out.println("loading failed");
		}
	}

	public String[] getTexts(String label) {
		if (!tdict.containsKey(label)) {
			return new String[] {"text_not_found: "+label};
		}
		return tdict.get(label);
	}
	
	public String getRandomText(String label) {
		SharedRandom rnd = SharedRandom.getInstance();
		String[] txs = getTexts(label);
		if (txs.length==0) return "";
		return txs[rnd.nextInt(txs.length)];
	}
	
	public void updateDictionary(String filename) {
		List<String> chainLabels = getAllLabels();
		loadFromCSV(filename);
		int n = chainLabels.size()-tdict.size();
		if (n>0)
			System.out.println("Labels missing string: " + n);
		Map <String, String[]> newdict = new HashMap<String, String[]>();
		for (String label : chainLabels) {
			if (tdict.containsKey(label)) {
				newdict.put(label, tdict.get(label));
			}else {
				newdict.put(label, new String[0]);
			}
		}
		tdict = newdict;
		Path src = Paths.get(filename);
		Path dst = Paths.get("backup_"+filename);
		try {
			Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);
		}catch(Exception e) {
			e.printStackTrace();
		}
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
