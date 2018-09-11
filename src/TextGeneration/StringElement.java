package TextGeneration;

import state.State;

public class StringElement implements TextElement{
	
	public StringElement(String str) {
		this.label = str;
	}
	
	String label;
	

	@Override
	public String yield(TextGenerationState state) {
		TextDictionary td = new TextDictionary();
		td.loadDictionary();
		return td.getRandomText(label);
	}

}
