package quantum;

class PauliZGate extends QuantumGate {
	private static final Complex neg1 = new Complex(-1.0);
	
	@Override
	void apply(QuantumState zeroState, QuantumState oneState) {
		Complex oneCoeff = oneState.coefficient;
		oneState.coefficient = oneCoeff.times(neg1);
	}
}
