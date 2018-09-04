package TextGeneration;

import state.State;

public class SpecialElement implements TextElement{
	
	String str;
	
	public SpecialElement(String str) {
		this.str = str;
	}

	@Override
	public String yield(State state) {
		if (str.equals("STOP")) 
			return "\n";
		return "&"+str;
	}
	
	

}
