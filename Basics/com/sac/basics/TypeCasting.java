package com.sac.basics;

/**
 * A b = (C)D
 * 
 * Type casting two checks by compiler one by jvm
 * 
 * 1)THere must be some relationship b/w object and class in which objected is
 * to be type casted
 * 
 * 2)Class C either should be same or subclass of A.
 * 
 * 3) Runtime object type of D must be same or derived type of C
 * 
 * With type casting we are not creating new object but only reference variable.
 * 
 * @author ssachdev
 *
 */
public class TypeCasting {

	public static void main(String[] args) {

		String s = new String("Sachin");
		Object o = (Object) s;
		// above is equal to Object o = new String("Sachin");

		// re: case 3
		Object o1 = new String("Sachin");
		StringBuffer sb = (StringBuffer) o1;

		Object o2 = new String("Sachin");
		// ce:rule 2 violates StringBuffer sb2= (String)o1;

		C1 obj = new C1();
		P1 obj2 = (P1) obj;
		obj2.m1();
		// Compile time check reference and its method but on run time check real time
		// in our case it is C - Overriding

	}

}

class P1 {
	void m1() {
		System.out.println("Inside Parent");
	}
}

class C1 extends P1 {
	void m2() {
		System.out.println("Inside Child");
	}

	void m1() {
		System.out.println("Inside Child");
	}
}
