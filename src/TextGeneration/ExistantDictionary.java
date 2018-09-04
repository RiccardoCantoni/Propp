package TextGeneration;

import java.util.LinkedList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonString;

import myUtils.JsonDataManager;
import myUtils.SharedRandom;

public class ExistantDictionary {
	
	String[] heroes,villains, donors, items, dispatchers, locations, friends;
	SharedRandom rnd;
	
	public ExistantDictionary() {
    	heroes = loadDictionary("heroes");
    	villains = loadDictionary("villains");
    	donors = loadDictionary("donors");
    	dispatchers = loadDictionary("dispatchers");
    	items = loadDictionary("items");
    	locations = loadDictionary("locations");
    	friends = loadDictionary("friends");
    	rnd = SharedRandom.getInstance();
	}
	
	private String[] loadDictionary(String dictname) {
		JsonDataManager jm = new JsonDataManager("data.json");
    	JsonArray ja = jm.loadArray(dictname);
    	List<String> tmp = new LinkedList<>();  	
    	for (JsonString o : ja.getValuesAs(JsonString.class)) {
    		tmp.add(o.getString());
    	}
    	return tmp.toArray(new String[tmp.size()]);
	}
	
	public String getRandomHero() {
		String[] ls = loadDictionary("heroes");
		return ls[rnd.nextInt(ls.length)];
	}
	
	public String getRandomVillain() {
		String[] ls = loadDictionary("villains");
		return ls[rnd.nextInt(ls.length)];
	}
	
	public String getRandomDonor() {
		String[] ls = loadDictionary("donors");
		return ls[rnd.nextInt(ls.length)];
	}
	
	public String getRandomDispatcher() {
		String[] ls = loadDictionary("dispatchers");
		return ls[rnd.nextInt(ls.length)];
	}
	
	public String getRandomFriends() {
		String[] ls = loadDictionary("friends");
		return ls[rnd.nextInt(ls.length)];
	}
	
	public String getRandomItem() {
		String[] ls = loadDictionary("items");
		return ls[rnd.nextInt(ls.length)];
	}
	
	public String getRandomLocation() {
		String[] ls = loadDictionary("locations");
		return ls[rnd.nextInt(ls.length)];
	}
}
