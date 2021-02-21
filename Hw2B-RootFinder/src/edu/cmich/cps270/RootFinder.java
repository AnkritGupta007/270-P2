package edu.cmich.cps270;

import java.util.function.Function;

@FunctionalInterface
interface Func {
	double apply(double x);
}

/**
 * @author gupta4a, stjoh1sr, black9m
 * A class that contains Bisection, Newton's, and Secant method for root finding
 *
 */
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
 
		System.out.println("f evaluated at 1.5 "+ f.apply(1.5));

		double zero = Double.NaN;
		RootFinder rf = new RootFinder(0, 1.1, f);


		Func fprime = (double x) -> (Math.exp(x) - 1) ;
		zero = rf.runNewtonsMethod(0.7, fprime, 1e-20, 20);
		System.out.println("The zero " + zero);

		//  Display the intermediate results of a Newton method run.
		rf.printNewtonsMethodRunDetails();

		System.out.println("---------------------------------------");
		rf = new RootFinder(0, 1.1, f);

		zero = rf.runBisectionMethod(1e-5, 20);
		System.out.println(zero);

		//  Display the intermediate results of a bisection method run.
		rf.printBisectionMethodRunDetails();
		
		f = (double x) -> (Math.exp(x) - 1);
		RootFinder rf1 = new RootFinder(-2.5, 2.5, f);
		double zero1 = rf1.runSecantMethod(-2.5, 2.5, 1e-5, 50);
		System.out.println("The solution for the secant method is "+ zero1);
		
	//  Display the intermediate results of a Secant method run.
		rf.printSecantMethodRunDetails();

	}



	/**
	 * Initializes a root finder for a continuous function over a closed 
	 *   interval.
	 * 
	 * @param a the left endpoint of a closed interval of the domain of f
	 * @param b the right endpoint of a closed interval of the domain of f
	 * @param f a continuous function over the domain [a, b]
	 * @throws execption if end points of interval are out of order
	 */
	public RootFinder(double a, double b, Func f) throws Exception {
		this.a = a;
		this.b = b;
		this.f = f;
		this.iterationsExecuted = 0;

		if(a >= b) {
			throw new Exception("Bounds of [a,b] are out of order");
		}


	}


	/**
	 * @param p0 initial guess for the root
	 * @param fprime the derivative of the function 
	 * @param tolerance absolute error bound on the zero and p
	 * @param N N the maximum number of iterations of the bisection method
	 * @return a zero of f
	 * @throws execption if N is not positive
	 * @throws exception if tolerance is not positive
	 */
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




	/**
	 * Prints the details of Newton's method by printing values of current estimate, previous estimate,
	 *  and the difference between the two 
	 */
	public void printNewtonsMethodRunDetails() {

		System.out.printf("%-4s %-8s %-8s\n---------------------------------------\n", 
				"i", "p_i's", "(p_i - p_i-1)");
		for(int i = 1; i < iterationsExecuted; i++) {
			System.out.printf("%-4d %f %f\n", i, pi[i], (pi[i] - pi[i-1]));
		}
	}


	/**
	 * Finds a root for a function with the bisection method.   
	 * 
	 * @param tolerance absolute error bound on the zero and p
	 * @param N the maximum number of iterations of the bisection method
	 * @return a zero of f
	 * @throws exception if a root is not found in N iterations
	 * @throws execption if N is not positive
	 * @throws exception if tolerance is not positive
	 * @throws exception if f(a) * f(b) >= 0
	 */
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

	/**
	 * Attempts to find roots of a function using the Secant method.
	 * 
	 * @param p0 a point on the function
	 * @param p1 another point o the function
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
		pi[0] = p0;
		pi[1] = p1;
		qi[0] = f.apply(pi[0]);
		qi[1] = f.apply(pi[1]);
		
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
