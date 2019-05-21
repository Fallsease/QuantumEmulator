package quantum;

class PhaseShiftGate extends QuantumGate {
	private double phi;
	
	PhaseShiftGate(double phaseFactor){
		phi = phaseFactor;
	}
	
	@Override
	void apply(QuantumState zeroState, QuantumState oneState) {
		Complex oneCoeff = oneState.coefficient;
		oneState.coefficient = oneCoeff.times(new Complex(0.0, phi).exp());
	}
}
