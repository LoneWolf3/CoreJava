package com.sac.basics;

/**
 * 1) Initialization of static variable and they are in read indirect state.
 * 
 * 2)Assignment and excecution of static block of values to static variable from top to bottom.
 * 
 * 3) Initialization of instance variable.
 * 
 * 4) Assignment  and execution of instance block of values to instance variable from top to bottom.
 * 
 * 
 * Read direct state when you are accessing varible directly and read indirect when you are access is via method.
 * 
 * @author ssachdev
 *
 */
public class Intializations {
	static String s1 = "1";

	static {
		// Here compilation fails as s2 is in read indirect state.
		//System.out.println(s2);
		m2();
		s1 = m1("3");
		m2();

	}

	static String s2 = "2";
	static {
		m2();
	}

	private static void m2() {
		System.out.println(s1);
		System.out.println(s2);

	}

	private static String m1(String s) {
		return s;
	}
	

}

class Intialization {

	

}