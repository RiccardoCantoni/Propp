package proppFunction;

import java.util.List;
import java.util.stream.Collectors;

import myUtils.SharedRandom;

public class InverseLocalFrequencyTransition implements MarkovTransition{
	
	SharedRandom rnd;
	
	public InverseLocalFrequencyTransition() {
		this.rnd = SharedRandom.getInstance();
	}

	@Override
	public Node nextNode(List<Node> validSuccessors) {
		float[] p = new float[validSuccessors.size()];
		for (int i=0; i<validSuccessors.size(); i++) {
			p[i]=1f/validSuccessors.get(i).localFrequency;
		}
		return validSuccessors.get(rnd.roulette(p));	
	}

}
