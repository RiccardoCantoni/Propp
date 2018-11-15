package TextGeneration;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import proppFunction.NodeType;

public class OutputItem {
	
	public String label;
	public String line;
	public String activity;
	public NodeType type;
	
	public OutputItem(String label, NodeType type, String line, String activity) {
		this.label = label;
		this.line = line;
		this.activity = activity;
		this.type = type;
	}
	
	public JsonObject toJsonObject() {
		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("label", label);
		builder.add("type", type.name());
		builder.add("line", line);
		builder.add("activity", activity);
		return builder.build();
	}
	

}
