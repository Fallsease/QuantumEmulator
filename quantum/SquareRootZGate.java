package quantum;

class SquareRootZGate extends QuantumGate {
	private static final Complex i = new Complex(0.0, 1.0);
	
	@Override
	void apply(QuantumState zeroState, QuantumState oneState) {
		Complex oneCoeff = oneState.coefficient;
		oneState.coefficient = oneCoeff.times(i);
	}
}
