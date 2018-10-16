package TextGeneration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import myUtils.JsonManager;

public class OutputItem {
	
	public String label;
	public String line;
	public String activity;
	
	public OutputItem(String label, String line, String activity) {
		this.label = label;
		this.line = line;
		this.activity = activity;
	}
	
	public JsonObject toJsonObject() {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("label", label);
		builder.add("line", line);
		builder.add("activity", activity);
		return builder.build();
	}
	

}
