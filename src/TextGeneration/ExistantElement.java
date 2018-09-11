package TextGeneration;

import state.AtomMatcher;
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
	public String yield(TextGenerationState state) {
		Predicate p = state.getCurrentState().getSet().find(new Predicate(type.name(),"_","_"));
		if (p!=null) {  //existant already introduced
			return p.subj;
		}
		return introduceExistant(type, state); //intro new existant
	}
	
	String introduceExistant(ExistantType type, TextGenerationState state) {
		ExistantDictionary dict = new ExistantDictionary();
		String existant = dict.getRandomExistant(type);
		state.getCurrentState().addPredicate(new Predicate(type.name(),existant,"_"));
		return existant;
	}
	
	ExistantType getType(String str) {
		if (str.equals("HERO")) {
			return ExistantType.HERO;
		}
		else if (str.equals("VILLAIN")) {
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
		else if (str.equals("FALSEHERO")) {
			return ExistantType.FALSEHERO;
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
