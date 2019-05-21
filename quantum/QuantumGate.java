package quantum;

abstract class QuantumGate {
	abstract void apply(QuantumState zeroState, QuantumState oneState);
}
