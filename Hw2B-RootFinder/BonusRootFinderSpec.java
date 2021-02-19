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

}
