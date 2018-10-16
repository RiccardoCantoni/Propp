package TextGeneration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.json.JsonArray;
import javax.json.JsonObject;

import org.apache.commons.lang3.StringUtils;

import myUtils.DebugUtils;
import myUtils.JsonManager;
import propp.Configuration;
import proppFunction.Node;
import proppFunction.NodeType;

public class TextGenerator {
	
	Map<String,String> labelActivityMap;
	
	public Text generateText(List<Node> nodeList){
		DebugUtils.debugPrint("=== text generation initiated ===");
		loadActivityMap();
		TextGenerationState tgs = new TextGenerationState();
		OutputItem[] body = process(nodeList, tgs);
		String title = new TitleGenerator().generateTitle(tgs.getCurrentState());
		DebugUtils.debugPrint("=== text generation terminated ===");
		return new Text(title, body);
	}
	
	private OutputItem[] process(List<Node> nodes, TextGenerationState state) {
		List<OutputItem> lines = new LinkedList<>();
		TextElement[] tes = null;
		for (Node n : nodes) {
			tes = N2Te(n,state);
			String line = "";
			if (tes!=null) {
				for (TextElement te : tes) {
					line+=te.yield(state)+" ";
				}
			lines.add(new OutputItem(n.label,line,getActivity(n.label)));
			DebugUtils.debugPrint(line);
			}
		}
		return lines.toArray(new OutputItem[lines.size()]);
	}
	
	TextElement[] N2Te(Node n, TextGenerationState state) {
		List<String> strlist = new LinkedList<>();
		List<TextElement> telist = new LinkedList<>();
		TextElement te;
		String str;
		if (n.type == NodeType.NONE) {
			te = new SpecialElement(n.label.substring(1));
		}else {
			te = new StringElement(n.label);
		}
		str = te.yield(state);
		if (str==null) return null;
		strlist = Arrays.asList(str.split("\\s+"));
		for (String s : strlist) {
			if (s.charAt(0)=='&') {
				telist.add(new SpecialElement(s.substring(1)));
			}else if (StringUtils.isAllUpperCase(s)) {
				telist.add(new ExistantElement(s));
			}else {
				telist.add(new PlainElement(s));
			}
		}
		
		return telist.toArray(new TextElement[telist.size()]);
	}
	
	String getActivity(String label){
		String a = labelActivityMap.get(label);
		if (a==null) return "";
		System.out.println(a+"==========");
		return a;
	}
	
	void loadActivityMap() {
		List<String> labels = JsonManager.getAllLabels(false);
		labelActivityMap = new HashMap<>();
		JsonManager jm = new JsonManager(Configuration.getInstance().activity_mapping_location);
		JsonArray mapping = jm.loadArray("mapping");
		for (JsonObject o : mapping.getValuesAs(JsonObject.class)) {
			if (!labels.contains(o.getString("label")))
				throw new NoSuchElementException("exergame mapping: label not found");
			labelActivityMap.put(o.getString("label"), o.getString("activity"));
    	}
	}

	
}
