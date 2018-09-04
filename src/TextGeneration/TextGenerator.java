package TextGeneration;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import proppFunction.Node;
import proppFunction.NodeType;
import state.State;

public class TextGenerator {
	
	public String generateText(List<Node> nodeList){
		String text = "";
		List<TextElement> textElements = getTextElements(nodeList);
		State state = new State();
		for (TextElement elem : textElements) {
			text+=elem.yield(state)+" ";
		}
		System.out.print("STATE: "+state.toString());
		return text;
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
			strings.addAll(Arrays.asList(e.yield(null).split("\\s+")));
			strings.add("&STOP"); //newline at the end of an event
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
