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
		Func f = (double x) -> (Math.exp(x) - x -1.5); 
		
		// #1. Print out f(1.5) making use of Func f.  
		System.out.println("f evaluated at 1.5 "+ f.apply(1.5));
		
		double zero = Double.NaN;
		RootFinder rf = new RootFinder(0, 1.1, f);
		
		
		Func fprime = (double x) -> (Math.exp(x) - 1) ;
		zero = rf.runNewtonsMethod(0.7, fprime, 1e-20, 20);
		System.out.println("The zero " + zero);
		
		// #6. Display the intermediate results of a Newton method run.
		rf.printNewtonsMethodRunDetails();
		
		System.out.println("---------------------------------------");
		rf = new RootFinder(0, 1.1, f);
		
		zero = rf.runBisectionMethod(1e-5, 20);
		System.out.println(zero);
		
		// #6. Display the intermediate results of a bisection method run.
		rf.printBisectionMethodRunDetails();

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

		if (N < 0 ) {
			throw new Exception("N(the number of iterations) is not positive.");

		}
		if (tolerance < 0 ) {
			throw new Exception("The tolerance is negative.");
		}

		this.pi =new double[N+1];
		double pi_Minus1 = p0;
		pi[0] = p0;
		this.iterationsExecuted = 0;
		int i = 1;
		while (i<=N) {
			double pi = pi_Minus1 -  ( this.f.apply(pi_Minus1) / fprime.apply(pi_Minus1) ) ;
			if( Math.abs(pi - pi_Minus1) < tolerance ) {
				return pi ;
			}
			this.pi[i] = pi;  
			iterationsExecuted++;
			i++;
			pi_Minus1 = pi;	
		}
		throw new Exception("A root is not found in"+ N +" iterations. ");
	}




	public void printNewtonsMethodRunDetails() {
		
		System.out.printf("%-4s %-8s %-8s\n---------------------------------------\n", 
				"i", "p_i's", "(p_i - p_i-1)");
		for(int i = 1; i < iterationsExecuted; i++) {
			System.out.printf("%-4d %f %f\n", i, pi[i], (pi[i] - pi[i-1]));
		}
	}


	public double runBisectionMethod(double tolerance, int N) throws Exception{
		if(tolerance <= 0) {
			throw new Exception("tolerance provided was not positive");
		}
		
		if(N < 0) {
			throw new Exception("N was not positive");
		}
		
		
		if(f.apply(a) * f.apply(b) >= 0 ) {
			throw new Exception("f(a) * f(b) is not negative");
		} 
		this.ai = new double[N+1];
		this.bi = new double[N+1];
		this.pi = new double[N+1];
		
		this.iterationsExecuted = 0;
		
		int i = 0;
		ai[0] = a;
		bi[0] = b;
		
		
		double fa = f.apply(a);
		while(i<N) {
			pi[i] = ai[i] + (bi[i] - ai[i]) / 2 ;
			double fp = f.apply(pi[i]);
			
			if(Math.abs(fp)< EPSILON || ((bi[i] - ai[i])/2 < tolerance)) {
				iterationsExecuted =i+1;
				return pi[i];
			}
			
			i++;
			
			if((fa * fp) > 0) {
				ai[i] = pi[i-1];
				bi[i] = bi[i-1];
				fa = fp;
			}
			else {
				ai[i] = ai[i-1];
				bi[i] = pi[i-1];
			}
		}
		
		throw new Exception("Method failed to find root after " + N +" iterations");
	}


	/**
	 * Display the intermediate values of a, b, p and (b-a) for each iteration of the
	 *   Bisection Method
	 * 
	 */
	public void printBisectionMethodRunDetails() {
		System.out.printf("%-4s %-8s %-8s %-8s %-8s\n---------------------------------------\n", 
				"i", "a_i's", "b_i's", "p_i's", "(b_i - a_i)");
		for(int i = 0; i < iterationsExecuted; i++) {
			System.out.printf("%-4d %f %f %f %f\n", i, ai[i], bi[i], pi[i], (bi[i] - ai[i]));
		}
	}

	public double runSecantMethod(double p0, double p1, double tolerance, int N) throws Exception{


		return 0;
	}


	public void printSecantMethodRunDetails() {

	}

}
