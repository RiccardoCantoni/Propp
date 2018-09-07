package TextGeneration;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonString;

import myUtils.JsonManager;
import myUtils.SharedRandom;

public class ExistantDictionary {
	
	SharedRandom rnd;
	
	public ExistantDictionary() {
    	rnd = SharedRandom.getInstance();
	}
	
	private String[] loadDictionary(String dictname) {
		try {
			JsonManager jm = new JsonManager("data.json");
	    	JsonArray ja = jm.loadArray(dictname);
	    	List<String> tmp = new LinkedList<>();  	
	    	for (JsonString o : ja.getValuesAs(JsonString.class)) {
	    		tmp.add(o.getString());
	    	}
	    	return tmp.toArray(new String[tmp.size()]);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.print("dictionary error:" +dictname);
			return null;
		}
	}
		
	public String getRandomExistant(ExistantType type) {
		String[] ls = loadDictionary(type.name());
		return ls[rnd.nextInt(ls.length)];
	}
}
