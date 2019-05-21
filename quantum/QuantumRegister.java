package quantum;

import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Set;

class QuantumRegister {
	private QuantumState[] states;
	private PriorityQueue<Integer> unusedIndices;
	private int alloc;
	private int size;

	public QuantumRegister() {
		states = new QuantumState[1];
		states[0].coefficient = Complex.ONE;
		unusedIndices = new PriorityQueue<Integer>();
		alloc = 0;
		size = 0;
	}

	int alloc(QuantumBit q) throws OutOfMemoryError {
		try {
			int index = unusedIndices.remove();
			size++;
			return index;
		}
		catch(NoSuchElementException e) {
			assert size == alloc;
			if(size >= 30) {
				throw new OutOfMemoryError("Only 30 Qubits allowed at one time");
			}

			QuantumState[] new_states = new QuantumState[2 << alloc];
			alloc++;
			for(int i = 0; i < states.length; i++) {
				new_states[i] = states[i];
			}
			for(int i = states.length; i < new_states.length; i++) {
				new_states[i] = new QuantumState();
				new_states[i].coefficient = Complex.ZERO;
			}
			states = new_states;
			return size++;
		}
	}
	
	void dealloc(QuantumBit q) {
		if(measure(q)) {
			q.X();
		}
		unusedIndices.add(q.index);
		size--;
	}
	
	void applyGate(QuantumGate gate, QuantumBit target, Set<QuantumBit> controls) {
		if(controls.contains(target)) {
			throw new IllegalArgumentException("Target qubit cannot be its own control");
		}
		controls.add(target);
		int stateValue = -1;
		for(QuantumBit control: controls) {
			stateValue ^= (1 << control.index);
		}
		
		
		for(int i = 0; i < states.length; i++) {
			if((stateValue | i) == -1) {
				gate.apply(states[i ^ (1 << target.index)], states[i]);
			}
		}
	}
	
	boolean measure(QuantumBit q) {
		int stateValue = 1 << q.index;
		double prob = 0.0;
		for(int i = 0; i < states.length; i++) {
			if((i & stateValue) != 0) {
				prob += states[i].coefficient.norm();
			}
		}
		boolean result = Math.random() < prob;
		Complex decrFactor;
		if(result) {
			decrFactor = new Complex(Math.sqrt(prob));
		} else {
			decrFactor = new Complex(Math.sqrt(1-prob));
		}
		
		for(int i = 0; i < states.length; i++) {
			if(((i & stateValue) != 0) == result) {
				states[i].coefficient = states[i].coefficient.div(decrFactor);
			}
			else {
				states[i].coefficient = Complex.ZERO;
			}
		}
		
		return result;
	}
}
