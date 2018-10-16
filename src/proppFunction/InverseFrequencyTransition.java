package proppFunction;

import java.util.List;
import java.util.stream.Collectors;

import myUtils.SharedRandom;
import plotGeneration.FrequencyDB;
import propp.SystemState;

public class InverseFrequencyTransition implements MarkovTransition{
	
	SharedRandom rnd;
	
	public InverseFrequencyTransition() {
		this.rnd = SharedRandom.getInstance();
	}

	@Override
	public Node nextNode(List<Node> validSuccessors) {
		float[] p = new float[validSuccessors.size()];
		float local;
		float global;
		for (int i=0; i<validSuccessors.size(); i++) {
			local = 1f/(FrequencyDB.getInstance().getLocalFrequency(validSuccessors.get(i)));
			if (SystemState.getInstance().globalFrequencyActive) {
				global = 1f/(FrequencyDB.getInstance().getGlobalFrequency(validSuccessors.get(i)));
			}else {
				global = 1;
			}
			p[i] = local*global;
		}
		return validSuccessors.get(rnd.roulette(p));
		
	}

}
