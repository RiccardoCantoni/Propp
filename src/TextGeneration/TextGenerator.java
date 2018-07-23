package TextGeneration;

import java.util.LinkedList;
import java.util.List;

import proppFunction.Node;
import proppFunction.NodeType;

public class TextGenerator {
	
	public String generateText(List<Node> nodelist){
		String text = "";
		List<TextElement> textElements = new LinkedList<>();
		for (Node n : nodelist) {
			TextElement te;
			if (n.type == NodeType.NONE) {
				if (n.label.charAt(0)=='$') {
					textElements.add(new SpecialElement(n.label.substring(1)));
				}else {
					textElements.add(new SpecialElement(n.label));
				}
			}else {
				textElements.add(new StringElement(n.label));
			}
			
		}
		for (TextElement elem : textElements) {
			text+=elem.yield()+";\n";
		}
		return text;
	}
}
