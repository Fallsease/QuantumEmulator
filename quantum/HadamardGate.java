package quantum;

class HadamardGate extends QuantumGate {
	private static final Complex sqrt2 = new Complex(Math.sqrt(2));

	@Override
	void apply(QuantumState zeroState, QuantumState oneState) {
		Complex zeroCoeff = zeroState.coefficient;
		Complex oneCoeff = oneState.coefficient;
		zeroState.coefficient = zeroCoeff.plus(oneCoeff).div(sqrt2);
		oneState.coefficient = zeroCoeff.minus(oneCoeff).div(sqrt2);
	}
}
