package edu.cmich.cps270;

import java.util.function.Function;

@FunctionalInterface
interface Func {
	double apply(double x);
}

public class RootFinder {

	// Used to determine if doubles are equal
	public static final double EPSILON = 1e-15;  
	
	Func f;
	double a;
	double b;
	double[] ai;
	double[] bi;
	double[] pi;
	int iterationsExecuted;

	public static void main(String[] args) throws Exception{
		
	}
	
	
	
	public RootFinder(double a, double b, Func f) throws Exception {
		this.a = a;
		this.b = b;
		this.f = f;
		this.iterationsExecuted = 0;
		
		if(a >= b) {
			throw new Exception("Bounds of [a,b] are out of order");
		}

		
	}
	
	
	public double runNewtonsMethod(double p0, Func fprime, double tolerance, int N) throws Exception {

		return 0;
	}
		
		


	public void printNewtonsMethodRunDetails() {

	}
	

	public double runBisectionMethod(double tolerance, int N) throws Exception{
		
		return 0;
	}
	
	
	/**
	 * Display the intermediate values of a, b, p and (b-a) for each iteration of the
	 *   Bisection Method
	 * 
	 */
	public void printBisectionMethodRunDetails() {


	}
	
	public double runSecantMethod(double p0, double p1, double tolerance, int N) throws Exception{
		

		return 0;
	}
	

	public void printSecantMethodRunDetails() {

	}
	
}
