package TextGeneration;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import myUtils.DebugUtils;
import proppFunction.Node;
import proppFunction.NodeType;

public class TextGenerator {
	
	public Text generateText(List<Node> nodeList){
		DebugUtils.debugPrint("=== text generation initiated ===");
		TextGenerationState tgs = new TextGenerationState();
		String[] lines = getLines(nodeList, tgs);
		Text t = new Text();
		t.body = lines;
		t.length = lines.length;
		t.title = new TitleGenerator().generateTitle(tgs.getCurrentState());
		DebugUtils.debugPrint("=== text generation terminated ===");
		return t;
	}
	
	private String[] getLines(List<Node> nodes, TextGenerationState state) {
		List<String> lines = new LinkedList<>();
		TextElement[] tes = null;
		for (Node n : nodes) {
			tes = N2Te(n,state);
			String line = "";
			if (tes!=null) {
				for (TextElement te : tes) {
					line+=te.yield(state)+" ";
				}
			lines.add(line);
			DebugUtils.debugPrint(line);
			}
		}
		return lines.toArray(new String[lines.size()]);
	}
	
	private TextElement[] N2Te(Node n, TextGenerationState state) {
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
	
	
}
