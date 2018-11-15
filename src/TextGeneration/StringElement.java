package TextGeneration;

import state.State;

public class StringElement implements TextElement{
	
	public StringElement(String str) {
		this.label = str;
	}
	
	String label;
	

	@Override
	public String yield(TextGenerationState state) {
		return TextDictionary.getInstance().getRandomText(label);
	}

}
