package plotGeneration;

import java.util.List;

import proppFunction.Node;
import state.Predicate;
import state.State;

public class Impasse {

	public List<Node> sequence;
	public Predicate predicate;
	public State state;
	
	public Impasse(List<Node> sequence, Predicate predicate, State state) {
		this.sequence = sequence;
		this.predicate = predicate;
		this.state = state;
	}
	
}
