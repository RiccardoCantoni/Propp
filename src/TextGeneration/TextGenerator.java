package TextGeneration;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import myUtils.DebugUtils;
import proppFunction.Node;
import proppFunction.NodeType;
import state.State;

public class TextGenerator {
	
	public Text generateText(List<Node> nodeList){
		DebugUtils.debugPrint("=== text generation initiated ===");
		String text = "";
		List<TextElement> textElements = getTextElements(nodeList);
		State state = new State();
		for (TextElement elem : textElements) {
			text+=elem.yield(state)+" ";
		}
		Text t = new Text();
		t.body = text;
		t.length = nodeList.size();
		t.title = new TitleGenerator().generateTitle(state);
		DebugUtils.debugPrint("=== text generation terminated ===");
		return t;
	}
	
	List<TextElement> getTextElements(List<Node> nodeList){
		List<TextElement> textElements = new LinkedList<>();
		for (Node n : nodeList) {
			if (n.type == NodeType.NONE) {
				textElements.add(new SpecialElement(n.label.substring(1)));
			}else {
				textElements.add(new StringElement(n.label));
			}
		}
		List<String> strings = new LinkedList<String>();
		for (TextElement e : textElements) {
			String str = e.yield(null);
			if (str!=null) {
				List<String> ls = Arrays.asList(str.split("\\s+"));
				strings.addAll(ls);
				strings.add("&STOP"); //newline at the end of an event
			}
		}
		textElements.clear();
		for (String s: strings) {
			if (s.charAt(0)=='&') {
				textElements.add(new SpecialElement(s.substring(1)));
			}else if (StringUtils.isAllUpperCase(s)) {
				textElements.add(new ExistantElement(s));
			}else {
				textElements.add(new PlainElement(s));
			}
		}
		return textElements;
	}
}
