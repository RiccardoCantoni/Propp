package plotGeneration;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import state.Predicate;
import state.State;

public class ImpasseHandler {
	
	public Map<Predicate, KnownSequence> handlermap;
	
	public ImpasseHandler() {
		handlermap = new HashMap<Predicate,KnownSequence>();
		handlermap.put(new Predicate("recon_outcome","negative","_"), KnownSequence.RETRY_RECON_SEQUENCE);
		handlermap.put(new Predicate("donor_test_outcome","negative","_"), KnownSequence.RETRY_TEST_SEQUENCE);
		handlermap.put(new Predicate("guidance_outcome","negative","_"), KnownSequence.RETRY_GUIDANCE_SEQUENCE);
		handlermap.put(new Predicate("struggle_outcome","negative","_"), KnownSequence.RETRY_STRUGGLE_SEQUENCE);
		handlermap.put(new Predicate("recognition_outcome","negative","_"), KnownSequence.RETRY_RECOGNITION_SEQUENCE);
	}
	
	public PlotArgument handleImpasse(Impasse impasse) {
		if (impasse == null ) return null;
		State substate = impasse.state;
		substate.removePredicate(impasse.predicate);
		if (handlermap.get(impasse.predicate)==null) System.out.println("hello");
		String[] subsequence = handlermap.get(impasse.predicate).getSequence();
		return new PlotArgument(subsequence, substate, new String[0]);
	}
		

}
