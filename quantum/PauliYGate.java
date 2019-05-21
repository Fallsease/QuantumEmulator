package quantum;

class PauliYGate extends QuantumGate {
	private static final Complex negi = new Complex(0.0, -1.0);
	private static final Complex i = new Complex(0.0, 1.0);

	@Override
	void apply(QuantumState zeroState, QuantumState oneState) {
		Complex zeroCoeff = zeroState.coefficient;
		Complex oneCoeff = oneState.coefficient;
		zeroState.coefficient = oneCoeff.times(negi);
		oneState.coefficient = zeroCoeff.times(i);
	}
}
