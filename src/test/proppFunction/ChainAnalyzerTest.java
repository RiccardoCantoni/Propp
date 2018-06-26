package test.proppFunction;

import org.junit.Test;

import propp.chains.ChainAnalyzer;
import proppFunction.FunctionChain;
import static org.junit.Assert.*;

public class ChainAnalyzerTest {
	
	@Test
	public void testCanResolve() {
		FunctionChain returnChain = FunctionChain.deserializeFrom("Return");
		FunctionChain fillerChain = FunctionChain.deserializeFrom("Filler");
		ChainAnalyzer returnAnalyzer = new ChainAnalyzer(returnChain);
		ChainAnalyzer fillerAnalyzer = new ChainAnalyzer(fillerChain);
		assertTrue(returnAnalyzer.canResolveInjections(new String[] {"unrecognised_arrival"}));
		assertFalse(returnAnalyzer.canResolveInjections(new String[] {"pippo"}));
		assertTrue(fillerAnalyzer.canResolveInjections(new String[] {"hero_quest"}));
		assertFalse(fillerAnalyzer.canResolveInjections(new String[] {"pippo"}));
	}

}
