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

	public static void main(String[] args) throws Exception {

	}

	public RootFinder(double a, double b, Func f) throws Exception {
		this.a = a;
		this.b = b;
		this.f = f;
		this.iterationsExecuted = 0;

		if (a >= b) {
			throw new Exception("Bounds of [a,b] are out of order");
		}

	}

	public double runNewtonsMethod(double p0, Func fprime, double tolerance, int N) throws Exception {

		return 0;
	}

	public void printNewtonsMethodRunDetails() {

	}

	public double runBisectionMethod(double tolerance, int N) throws Exception {

		return 0;
	}

	/**
	 * Display the intermediate values of a, b, p and (b-a) for each iteration of
	 * the Bisection Method
	 * 
	 */
	public void printBisectionMethodRunDetails() {

	}

	/**
	 * Attempts to find roots of a function using the Secant method.
	 * 
	 * @param p0
	 * @param p1
	 * @param tolerance an absolute error bound on zero
	 * @param N maximum number of iterations
	 * @return the approximation of a root for a function
	 * @throws Exception if invalid parameters are given or no zero is found
	 */
	public double runSecantMethod(double p0, double p1, double tolerance, int N) throws Exception {
		// Throwing exceptions if invalid parameters are given
		if (N < 0) {
			throw new Exception("N is less than zero.");
		}

		if (tolerance < 0) {
			throw new Exception("Tolerance is less than zero");
		}

		this.pi = new double[N + 2];
		double[] qi = new double[N + 2]; // An array for f(p[i]) values
		
		this.iterationsExecuted = 0;
		
		// Initializing arrays based on given parameters
		int i = 2;
		pi[1] = p1;
		pi[0] = p0;
		qi[1] = f.apply(pi[1]);
		qi[0] = f.apply(pi[0]);
		
		// Looping until N iterations complete or a candidate zero is found
		while (iterationsExecuted <= N) {
			iterationsExecuted++;
			
			pi[i] = pi[i - 1] - ((qi[i - 1] * (pi[i - 1] - pi[i - 2])) / (qi[i - 1] - qi[i - 2]));

			qi[i] = f.apply(pi[i]);

			if ((Math.abs(pi[i] - pi[i - 1]) < tolerance || Math.abs(qi[i]) < EPSILON)) {
				return pi[i];
			}

			i++;
		}

		throw new Exception("Method failed after " + N + " iterations.");
	}

	/**
	 * Displays the run details for the Secant method, including pi's and the
	 * difference in pi's for each iteration.
	 */
	public void printSecantMethodRunDetails() {
		// Printing header
		System.out.printf("%-4s %-8s %-8s\n---------------------------------------\n", "i", "p_i's", "(p_i - p_i-1)");
		
		for (int i = 1; i <= iterationsExecuted; i++) {
			System.out.printf("%-4d %f %f\n", i, pi[i], (pi[i] - pi[i - 1]));
		}
	}

}
