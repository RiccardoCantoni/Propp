package TextGeneration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import proppFunction.SharedRandom;

public class TextDictionary {
	
	private Map <String, String[]> tdict;
	
	public TextDictionary() {
		tdict = new HashMap<String, String[]>();
		mockDict();
	}
	
	private void mockDict() {
		tdict.put("label1", new String[] {"a","b","c"});
		tdict.put("label3", new String[] {"a"});
		tdict.put("label2", new String[] {"a","b","c","d"});
		tdict.put("label4", new String[] {"a","b"});
	}
	
	public void unloadToCSV(String filename) {
		try {
			PrintWriter pw = new PrintWriter(new File(filename));
			for (String s : tdict.keySet()) {
		        StringBuilder sb = new StringBuilder();
		        sb.append(s);
		        for (String ss : tdict.get(s)) {
		        	sb.append(";"+ss);
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
	
}
