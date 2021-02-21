package edu.cmich.cps270;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BonusRootFinderSpec {

	@Test
	void zeroNotFoundAfterN_BisectionMethod() throws Exception {
		Func f = (double x) -> (Math.pow(x, 3) - 1);
		RootFinder rf = new RootFinder(0, 1.1, f);

		Assertions.assertThrows(Exception.class, () -> {
		    rf.runBisectionMethod(1e-5, 1);
		    });
		
	}
	
	@Test
	void zeroNotFoundAfterN_NewtonsMethod() throws Exception {
		Func f = (double x) -> (Math.pow(x, 2) - 1);
		Func fprime = (double x) -> (2 * x);
		RootFinder rf = new RootFinder(0, 2.5, f);
		
		Assertions.assertThrows(Exception.class, () -> {
		    rf.runNewtonsMethod(.5, fprime, 1e-5, 1);
		});
	}
	
	@Test
	void zeroNotFoundAfterN_SecantMethod() throws Exception {
		Func f = (double x) -> (Math.pow(x, 3) - 1);
		RootFinder rf = new RootFinder(0, 2.5, f);
		
		Assertions.assertThrows(Exception.class, () -> {
		    rf.runSecantMethod(.25, .5, 1e-5, 1);
		});
	}
	
	@Test
	void runBisectionMethodFe_x_1() throws Exception {
		Func f = (double x) -> (Math.exp(x) - 1);
		RootFinder rf = new RootFinder(-2.5, 2.5, f);
		double zero = rf.runBisectionMethod(1e-5, 40);
		
		assertTrue(Math.abs(zero - 0) < 1e-5);
		
	}
	
	@Test
	void runNewtonsMethodFX_sqroot_1() throws Exception {
		Func f = (double x) -> (Math.pow(x, 0.5) - 1);
		Func fprime = (double x) -> (0.5 * Math.pow(x, -0.5));
		RootFinder rf = new RootFinder(-2.5, 2.5, f);
		double zero = rf.runNewtonsMethod(.5, fprime, 1e-5, 50);
		
		assertTrue(Math.abs(zero - 1) < 1e-5);
		
	}
	
	@Test
	void runSecantMethod_e_x_1() throws Exception {
		Func f = (double x) -> (Math.exp(x) - 1);
		RootFinder rf = new RootFinder(-2.5, 2.5, f);
		double zero = rf.runSecantMethod(-2.5, 2.5, 1e-5, 50);
		
		assertTrue(Math.abs(zero - 0) < 1e-5);
		
	}

}
