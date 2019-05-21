package quantum;

class PauliXGate extends QuantumGate {
	@Override
	void apply(QuantumState zeroState, QuantumState oneState) {
		Complex zeroCoeff = zeroState.coefficient;
		Complex oneCoeff = oneState.coefficient;
		zeroState.coefficient = oneCoeff;
		oneState.coefficient = zeroCoeff;
	}
}
