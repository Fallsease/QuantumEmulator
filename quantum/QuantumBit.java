/**
 * 
 */
package quantum;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Tejas Narayan
 *
 */
public class QuantumBit {
	final int index;
	private static final QuantumRegister qureg = new QuantumRegister();
	
	public QuantumBit() {
		index = qureg.alloc(this);
	}
	public QuantumBit(boolean b) {
		index = qureg.alloc(this);
		if(b) X();
	}
	public QuantumBit(QuantumBit q) {
		index = qureg.alloc(this);
		this.X(q);
	}
	
	public void finalize() {
		qureg.dealloc(this);
	}
	
	public boolean measure() {
		return qureg.measure(this);
	}
	
	
	//Quantum gates
	public void H(QuantumBit ...controls) {
		qureg.applyGate(new HadamardGate(), this, new HashSet<QuantumBit>(Arrays.asList(controls)));
	}
	
	public void R(double phi, QuantumBit ...controls) {
		qureg.applyGate(new PhaseShiftGate(phi), this, new HashSet<QuantumBit>(Arrays.asList(controls)));
	}
	
	public void X(QuantumBit ...controls) {
		qureg.applyGate(new PauliXGate(), this, new HashSet<QuantumBit>(Arrays.asList(controls)));
	}
	
	public void Y(QuantumBit ...controls) {
		qureg.applyGate(new PauliYGate(), this, new HashSet<QuantumBit>(Arrays.asList(controls)));
	}
	
	public void Z(QuantumBit ...controls) {
		qureg.applyGate(new PauliZGate(), this, new HashSet<QuantumBit>(Arrays.asList(controls)));
	}
	
	public void sqrtX(QuantumBit ...controls) {
		qureg.applyGate(new SquareRootXGate(), this, new HashSet<QuantumBit>(Arrays.asList(controls)));
	}
	
	public void sqrtZ(QuantumBit ...controls) {
		qureg.applyGate(new SquareRootZGate(), this, new HashSet<QuantumBit>(Arrays.asList(controls)));
	}
}
