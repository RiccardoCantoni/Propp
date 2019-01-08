package test.proppFunction;

import org.junit.Test;

import propp.chains.ChainAnalyzer;
import proppFunction.ProppFunction;
import static org.junit.Assert.*;

public class ChainAnalyzerTest {
	
	@Test
	public void testCanResolve() {
		ProppFunction returnChain = ProppFunction.deserializeFrom("Return");
		ProppFunction fillerChain = ProppFunction.deserializeFrom("Filler");
		ChainAnalyzer returnAnalyzer = new ChainAnalyzer(returnChain);
		ChainAnalyzer fillerAnalyzer = new ChainAnalyzer(fillerChain);
		assertTrue(returnAnalyzer.resolvableInjections(new String[] {"unrecognised_arrival"}).length>0);
		assertFalse(returnAnalyzer.resolvableInjections(new String[] {"pippo"}).length>0);
		assertTrue(fillerAnalyzer.resolvableInjections(new String[] {"hero_quest"}).length>0);
		assertFalse(fillerAnalyzer.resolvableInjections(new String[] {"pippo"}).length>0);
	}

}
