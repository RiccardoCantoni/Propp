package plotGeneration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import state.Predicate;
import state.State;

public class ImpasseHandler {
	
	public Map<Predicate, KnownSequence> handlerSequenceMap;
	public Map<Predicate, String[]> handlerInjectionMap;
	
	public ImpasseHandler() {
		
		handlerSequenceMap = new HashMap<Predicate,KnownSequence>();
		handlerSequenceMap.put(new Predicate("recon_outcome","negative","_"), KnownSequence.RETRY_RECON_SEQUENCE);
		handlerSequenceMap.put(new Predicate("donor_test_outcome","negative","_"), KnownSequence.RETRY_TEST_SEQUENCE);
		handlerSequenceMap.put(new Predicate("guidance_outcome","negative","_"), KnownSequence.RETRY_GUIDANCE_SEQUENCE);
		handlerSequenceMap.put(new Predicate("struggle_outcome","negative","_"), KnownSequence.RETRY_STRUGGLE_SEQUENCE);
		handlerSequenceMap.put(new Predicate("recognition_outcome","negative","_"), KnownSequence.RETRY_RECOGNITION_SEQUENCE);
		handlerSequenceMap.put(new Predicate("subplot_required","quest_item","_"), KnownSequence.QUEST_ITEM_SEQUENCE);
		handlerSequenceMap.put(new Predicate("subplot_required","quest_helper","_"), KnownSequence.QUEST_HELPER_SEQUENCE);
		handlerSequenceMap.put(new Predicate("subplot_required","imposture","_"), KnownSequence.IMPOSTURE_SEQUENCE);
	
		handlerInjectionMap = new HashMap<Predicate, String[]>();
		handlerInjectionMap.put(new Predicate("recon_outcome","negative","_"), new String[] {"filler_villain_retry", "intended_encounter"});
		handlerInjectionMap.put(new Predicate("donor_test_outcome","negative","_"),new String[] {"filler_hero_retry"});
		handlerInjectionMap.put(new Predicate("guidance_outcome","negative","_"), new String[] {"filler_hero_retry"});
		handlerInjectionMap.put(new Predicate("struggle_outcome","negative","_"), new String[] {"filler_hero_retry"});
		handlerInjectionMap.put(new Predicate("recognition_outcome","negative","_"), new String[] {"filler_lack_item"});
		handlerInjectionMap.put(new Predicate("subplot_required","quest_item","_"), new String[] {});
		handlerInjectionMap.put(new Predicate("subplot_required","quest_helper","_"), new String[] {"filler_lack_helper"});
		handlerInjectionMap.put(new Predicate("subplot_required","imposture","_"), new String[] {"unrecognised_arrival"});
		
	}
	
	public PlotArgument handleImpasse(Impasse impasse) {
		if (impasse == null ) return null;
		return buildArgument(impasse);
	}
	
	private PlotArgument buildArgument(Impasse impasse) {
		String[] subseq = handlerSequenceMap.get(impasse.predicate).getSequence();
		State substate = impasse.state;
		substate.removePredicate(impasse.predicate);
		String[] injections = handlerInjectionMap.get(impasse.predicate);
		if (injections==null) {
			injections = new String[0];
		}
		return new PlotArgument(subseq, substate, injections);
	}
	
	private List<String> toList(String s){
		return Arrays.asList(new String[] {s});
	}
		
}
