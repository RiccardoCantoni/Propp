package myUtils;

import java.util.Iterator;
import java.util.List;

public class RandomSequencePicker<T> implements Iterator<T>{

	T[] sequence;
	int nextIndex;
	SharedRandom rnd;
	
	public RandomSequencePicker(T[] set){
		rnd = SharedRandom.getInstance();
		sequence = shuffle(set);
		nextIndex = 0;
	}
	
	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public T next() {
		if (nextIndex==sequence.length) {
			sequence = shuffle(sequence);
			nextIndex = 0;
		}
		T x = sequence[nextIndex];
		nextIndex++;
		return x;
	}
	
	T[] shuffle(T[] input){
		for (int i=0; i<input.length; i++) {
			input = swap(input,i,rnd.nextInt(input.length));
		}
		return input;
	}
	
	T[] swap(T[] array, int a, int b) {
		T t = array[a];
		array[a] = array[b];
		array[b] = t;
		return array;
	}


	
}
