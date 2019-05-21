package quantum;

import java.util.Arrays;

public class QuantumByte {
	private QuantumBit[] bits;
	
	public QuantumByte(int n) {
		bits = new QuantumBit[8];
		for(int i = 0; i < 8; i++) {
			bits[i] = new QuantumBit((n & (1 << i)) != 0);
		}
	}
	
	public QuantumByte() {
		this(0);
	}
	
	//Quantum Fourier Transform
	public void qft() {
		for(int i = 0; i < 4; i++) {
			bits[i].X(bits[8-i]);
			bits[8-i].X(bits[i]);
			bits[i].X(bits[8-i]);
		}
		for(int i = 0; i < 8; i++) {
			bits[i].H();
			for(int j = i; j < 8; j++) {
				bits[i].R(-Math.PI / (1 << (j-i)), bits[j]);
			}
		}
	}
	
	//Increment or decrement by any known amount
	public void incr(int n) {
		for(int i = 0; i < 8; i++) {
			if((n & (1 << i)) != 0) {
				for(int j = 7; j >= i; j--) {
					bits[j].X(Arrays.copyOfRange(bits, 0, j));
				}
			}
		}
	}
	public void decr(int n) {
		incr(-n);
	}
	public void incr() {
		incr(1);
	}
	
	//Increment or decrement by an amount defined by a superposition
	public void incr(QuantumByte that) {
		
	}
}
