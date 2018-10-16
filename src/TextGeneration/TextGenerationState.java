package TextGeneration;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import state.AtomMatcher;
import state.Predicate;
import state.State;

public class TextGenerationState {
	
	Stack<State> stack;
	
	public TextGenerationState() {
		stack = new Stack<>();
		stack.push(new State());
	}

	public State getCurrentState() {
		return stack.peek();
	}
	
	public void subplot() {
		State s = filterState(stack.peek());
		stack.push(s);
	}
	
	public void subplotResolved() {
		State s1 = stack.pop();
		State s2 = stack.pop();
		stack.push(merge(s2,s1));
	}
	
	private State filterState(State s) {
		State s2 = new State();
		String[] toKeep = new String[] {"HERO","VILLAIN","FRIEND","DISPATCHER"};
		Predicate[] predsToKeep;
		for (String tk : toKeep) {
			predsToKeep = s.getSet().findAll(new Predicate(tk,"_","_")).toArray(new Predicate[0]);
			for (Predicate p : predsToKeep) {
				s2.addPredicate(p);
			}
		}
	return s2;
	}
	
	private State merge (State s1, State s2) {
		for (Predicate p: s1.getSet().set) {
			s2.addPredicate(p);
		}
		return s2;
	}
}
