package TextGeneration;

import state.State;

public class SpecialElement implements TextElement{
	
	String str;
	
	public SpecialElement(String str) {
		this.str = str;
	}

	@Override
	public String yield(TextGenerationState state) {
		if (str.equals("STOP")) 
			return "\n";
		if (str.equals("SUBPLOT")) {
			state.subplot();
			return null;
		}
		if (str.equals("RESOLVED")){
			state.subplotResolved();
			return null;
		}
		return null;
		//return "&"+str;
	}
	
}
