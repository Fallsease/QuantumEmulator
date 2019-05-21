package quantum;

public class Complex {
	private final double re;
	private final double im;
	
	//Sample Complex numbers
	public static final Complex ZERO = new Complex(0.0);
	public static final Complex ONE = new Complex(1.0);

	// Creators
	public Complex(double realPart, double imagPart) {
		re = realPart;
		im = imagPart;
	}
	
	public Complex(double re) {
		this(re, 0.0);
	}
	
	public Complex() {
		this(0.0, 0.0);
	}
	
	//Observers
	public double norm() {
		return re*re + im*im;
	}
	public double abs() {
		return Math.sqrt(norm());
	}
	
	public double arg() {
		return Math.atan2(im, re);
	}
	
	public double real() {
		return re;
	}
	
	public double imag() {
		return im;
	}

	// Producers
	public Complex neg() {
		return new Complex(-this.re, -this.im);
	}
	public Complex plus(Complex that) {
		return new Complex(this.re + that.re, this.im + that.im);
	}
	
	public Complex minus(Complex that) {
		return this.plus(that.neg());
	}
	
	public Complex inv() {
		double denom = this.re*this.re + this.im*this.im;
		return new Complex(this.re / denom, this.im / denom);
	}
	
	public Complex times(Complex that) {
		return new Complex(this.re*that.re - this.im*that.im, this.re*that.im + this.im*that.re);
	}
	
	public Complex div(Complex that) {
		return this.times(that.inv());
	}
	
	public Complex conj() {
		return new Complex(this.re, -this.im);
	}
	
	public Complex exp() {
		double mag = Math.exp(this.re);
		return new Complex(Math.cos(im),Math.sin(im)).times(new Complex(mag));
	}
}
