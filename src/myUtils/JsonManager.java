package myUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonWriter;

import TextGeneration.OutputItem;
import propp.Configuration;
import propp.chains.ChainAnalyzer;
import proppFunction.FunctionChain;

public class JsonManager {
	
	File file;
	
	public JsonManager(String file) {
		this.file = new File(file);
	}
	
	public JsonArray loadArray(String name){
		JsonArray arr = null;
		try {
			InputStream in = new FileInputStream(file);
			JsonReader reader = Json.createReader(in);
			JsonObject obj = reader.readObject();
			arr = obj.getJsonArray(name);			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("JSON exception: unable to find array "+name);
		}
		return arr;
	}
	
	public void saveOutput(OutputItem[] body) {
		if (!Configuration.getInstance().outputEnabled)
			return;
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (OutputItem o : body) {
			jab.add(o.toJsonObject());
		}
		JsonArray ja = jab.build();
		try {
			JsonWriter writer = Json.createWriter(new FileOutputStream(file));
			writer.write(ja);
			writer.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<String> getAllLabels(boolean checkDuplicates){
    	List<String> labels = new LinkedList<>();
    	ChainAnalyzer ca;
    	JsonManager jdm = new JsonManager(Configuration.getInstance().functions_data_location);
    	JsonArray chains = jdm.loadArray("functions");
    	for (JsonObject o : chains.getValuesAs(JsonObject.class)) {
    		ca = new ChainAnalyzer(FunctionChain.deserializeFrom(o.getString("name")));
    		for (String label : ca.getLabels()) {
    			if (checkDuplicates && labels.contains(label)) {
    				System.out.println("WARNING: duplicate label "+label);
    			}else {
    				labels.add(label);
    			}
    		}
    	}
    	return labels;
    }
}
