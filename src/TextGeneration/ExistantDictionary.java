package TextGeneration;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.json.JsonArray;
import javax.json.JsonString;

import myUtils.JsonManager;
import myUtils.RandomSequencePicker;
import myUtils.SharedRandom;

public class ExistantDictionary {
	
	SharedRandom rnd;
	Map<String,RandomSequencePicker<String>> pickers = new HashMap<String, RandomSequencePicker<String>>();
	
	public ExistantDictionary() {
    	rnd = SharedRandom.getInstance();
    	ExistantType[] types = ExistantType.class.getEnumConstants();
    	RandomSequencePicker<String> rsp;
    	for (ExistantType type : types) {
    		String[] seq = loadDictionary(type.name());
    		rsp = new RandomSequencePicker<>(seq);
    		pickers.put(type.name(), rsp);
    	}
	}
	
	private String[] loadDictionary(String dictname) {
		try {
			JsonManager jm = new JsonManager("existent_data.json");
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
		return pickers.get(type.name()).next();
	}
}
