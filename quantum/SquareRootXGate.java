package quantum;

class SquareRootXGate extends QuantumGate {
	private static final Complex iPlus1Over2 = new Complex(0.5, 0.5);
	private static final Complex negiPlus1Over2 = new Complex(0.5, -0.5);

	@Override
	void apply(QuantumState zeroState, QuantumState oneState) {
		Complex zeroCoeff = zeroState.coefficient;
		Complex oneCoeff = oneState.coefficient;
		zeroState.coefficient = zeroCoeff.times(iPlus1Over2).plus(oneCoeff.times(negiPlus1Over2));
		zeroState.coefficient = oneCoeff.times(negiPlus1Over2).plus(zeroCoeff.times(iPlus1Over2));
	}

}
