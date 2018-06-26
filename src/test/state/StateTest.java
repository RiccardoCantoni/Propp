package test.state;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import state.Predicate;
import state.State;

public class StateTest {	
	
	
	@Test
	public void injectTest() {
		State state = new State();
		state.inject("test");
		assertTrue((state.getSet().contains(new Predicate("injection","test","_"))));
	}

}
