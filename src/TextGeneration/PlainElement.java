package TextGeneration;

import state.State;

public class PlainElement implements TextElement {
	
	String str;
	
	public PlainElement(String str) {
		this.str = str;		
	}

	@Override
	public String yield(State state) {
		return str;
	}

}
