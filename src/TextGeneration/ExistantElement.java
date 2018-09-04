package TextGeneration;

import state.Predicate;
import state.State;

public class ExistantElement implements TextElement{
	
	ExistantType type;
	String str;
	
	public ExistantElement(String str) {
		this.str = str;
		this.type = getType(str);
	}

	@Override
	public String yield(State state) {
		state.addPredicate(new Predicate("test","test","test"));
		return "$"+str;
	}
	
	ExistantType getType(String str) {
		if (str.equals("HERO")) {
			return ExistantType.HERO;
		}else if (str.equals("VILLAIN")) {
			return ExistantType.VILLAIN;
		}
		else if (str.equals("DONOR")) {
			return ExistantType.DONOR;
		}
		else if (str.equals("DISPATCHER")) {
			return ExistantType.DISPATCHER;
		}
		else if (str.equals("FRIEND")) {
			return ExistantType.FRIEND;
		}
		else if (str.equals("ITEM")) {
			return ExistantType.ITEM;
		}else if (str.equals("LOCATION")) {
			return ExistantType.LOCATION;
		}else {
			throw new IllegalArgumentException("ExistantType string not recognised: "+str);			
		}
	}

}
