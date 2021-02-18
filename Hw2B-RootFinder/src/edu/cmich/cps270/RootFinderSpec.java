package edu.cmich.cps270;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RootFinderSpec {

	@Test
	void intervalBoundsOutOfOrder() {
	   Func f = (double x) -> (x - 0.5);
		
	   Assertions.assertThrows(Exception.class, () -> {
		    new RootFinder(1, 0, f);});
	 
	}
	
	@Test
	void negativeProductForFaFb() {
	   Func f = (double x) -> (x - 0.5);
		
	   Assertions.assertThrows(Exception.class, () -> {
		    new RootFinder(1, 2, f).runBisectionMethod(1e-5, 10);});
	 
	}
	
	@Test
	void negativeToleranceForRunBisectionMethod() throws Exception {
		
	   Func f = (double x) -> (x - 0.5);
	   RootFinder rf = new RootFinder(0, 1.1, f);
	   
	   Assertions.assertThrows(Exception.class, () -> {
		    rf.runBisectionMethod(-1e-5, 1);
		    });
	 
	}
	
	@Test
	void negativeToleranceForRunNewtonMethod() throws Exception {
		
	   Func f = (double x) -> (x - 0.5);
	   Func fprime = (double x) -> (1);
	   RootFinder rf = new RootFinder(0, 1.1, f);
	   
	   Assertions.assertThrows(Exception.class, () -> {
		    rf.runNewtonsMethod(1., fprime, -1e-5, 1);
		    });
	 
	}
	
	@Test
	void negativeToleranceForRunSecantMethod() throws Exception {
		
	   Func f = (double x) -> (x - 0.5);
	   RootFinder rf = new RootFinder(0, 1.1, f);
	   
	   Assertions.assertThrows(Exception.class, () -> {
		    rf.runSecantMethod(1., 1.5, -1e-5, 1);
		    });
	 
	}
	
	@Test
	void negativeNForRunBisectionMethod() throws Exception {
		
	   Func f = (double x) -> (x - 0.5);
	   RootFinder rf = new RootFinder(0, 1.1, f);
	   
	   Assertions.assertThrows(Exception.class, () -> {
		    rf.runBisectionMethod(1e-5, -1);
		    });
	 
	}
	
	@Test
	void negativeNForRunNewtonsMethod() throws Exception {
		
	   Func f = (double x) -> (x - 0.5);
	   Func fprime = (double x) -> (1);
	   RootFinder rf = new RootFinder(0, 1.1, f);
	   
	   Assertions.assertThrows(Exception.class, () -> {
		    rf.runNewtonsMethod(1., fprime, 1e-5, -1);
		    });
	 
	}
	
	@Test
	void negativeNForRunSecantMethod() throws Exception {
		
	   Func f = (double x) -> (x - 0.5);
	   RootFinder rf = new RootFinder(0, 1.1, f);
	   
	   Assertions.assertThrows(Exception.class, () -> {
		    rf.runSecantMethod(1., 1.5, 1e-5, -1);
		    });
	 
	}
	
	@Test
	void runBisectionMethodFX2_1() throws Exception {
		Func f = (double x) -> (Math.pow(x, 2) - 1);
		RootFinder rf = new RootFinder(0, 2.5, f);
		double zero = rf.runBisectionMethod(1e-5, 50);
		
		assertTrue(Math.abs(zero - 1) < 1e-5);
		
	}
	
	@Test
	void runNewtonsMethodFX2_1() throws Exception {
		Func f = (double x) -> (Math.pow(x, 2) - 1);
		Func fprime = (double x) -> (2 * x);
		RootFinder rf = new RootFinder(0, 2.5, f);
		double zero = rf.runNewtonsMethod(.5, fprime, 1e-5, 50);
		
		assertTrue(Math.abs(zero - 1) < 1e-5);
		
	}
	
	@Test
	void runSecantMethodFX2_1() throws Exception {
		Func f = (double x) -> (Math.pow(x, 2) - 1);
		RootFinder rf = new RootFinder(0, 2.5, f);
		double zero = rf.runSecantMethod(.25, .5, 1e-5, 50);
		
		assertTrue(Math.abs(zero - 1) < 1e-5);
		
	}
	
	@Test
	void runBisectionMethodSmallN() throws Exception {
	   Func f = (double x) -> (x - 0.5);
	   RootFinder rf = new RootFinder(0, 1.1, f);
	   
	   Assertions.assertThrows(Exception.class, () -> {
		    rf.runBisectionMethod(1e-5, 1);
		    });
	}
	
	@Test 
	void runNewtonsMethodNoConvergence() throws Exception {
		
		Func f = (double x) -> (4 * Math.pow(x, 5) - 5 * Math.pow(x, 4) - 20 * Math.pow(x, 3) + 
				10 * Math.pow(x, 2) + 40 * x + 10);
		Func fprime = (double x) -> (20 * Math.pow(x, 4) - 20 * Math.pow(x, 3) - 60 * Math.pow(x, 2) +
				20 * x + 40);
		RootFinder rf = new RootFinder(-2, 10, f);
		
		Assertions.assertThrows(Exception.class, () -> {
			rf.runNewtonsMethod(2, fprime, 1e-5, 100);
		});

	}
}

