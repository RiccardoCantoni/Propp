package TextGeneration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import javax.json.JsonArray;
import javax.json.JsonObject;

import myUtils.JsonManager;
import propp.Configuration;

public class ActivityMapping {
	
	Map<String,String> labelActivity;
	Map<String,String> activityLabel;
	
	private static ActivityMapping instance;
	
	private ActivityMapping() {
		this.labelActivity = new HashMap<String, String>();
		this.activityLabel = new HashMap<String, String>();
	}
	
	public static ActivityMapping getInstance() {
		if (instance==null) {
			instance = new ActivityMapping();
		}
		return instance;
	}
	
	public String[] getLabels(String[] activities) {
		String[] labels = new String[activities.length];
		for(int i=0; i<labels.length; i++) {
			labels[i] = getLabel(activities[i]);
		}
		return labels;
	}
	
	public String getActivity(String label){
		String a = labelActivity.get(label);
		if (a==null) return "";
		return a;
	}
	
	public String getLabel(String activity) {
		String a = activityLabel.get(activity);
		if (a==null) return "";
		return a;
	}
	
	void loadActivityMap() {
		List<String> labels = JsonManager.getAllLabels(false);
		JsonManager jm = new JsonManager(Configuration.getInstance().activity_mapping_location);
		JsonArray mapping = jm.loadArray("mapping");
		for (JsonObject o : mapping.getValuesAs(JsonObject.class)) {
			if (!labels.contains(o.getString("label")))
				throw new NoSuchElementException("exergame mapping: label not found");
			labelActivity.put(o.getString("label"), o.getString("activity"));
    	}
	}

}
