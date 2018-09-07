package TextGeneration;

import state.Predicate;
import state.State;

public class TitleGenerator {
	
	public TitleGenerator() {
		
	}
	
	public String generateTitle(State state) {
		String hero = state.getSet().find(new Predicate("HERO","_","_")).subj;
		String villain = state.getSet().find(new Predicate("VILLAIN","_","_")).subj;
		return hero + " and "+ villain;
	}

}
