package com.sac.basics;

/**
 * Has a relation is called composition or aggregation.
 * 
 * Strong association is composition e.g university has department- no
 * university no department and aggregation is weakly association exmaple
 * department has professors
 * 
 * No support for multiple inheritance because of ambiguity
 * 
 * With the help of interface multiple inheritance supported
 * 
 * Object class is root class, single inheritance,multilevel
 * inheritance,hierarchical inheritance
 * 
 * Both super and this not possible inside the constructor. as they both need to
 * be first statement
 * 
 * By default only super key word will be placed
 * 
 * Instance blocks are executed during object creation
 * 
 * @author ssachdev
 *
 */
public class InHeritance {
	public static void main(String[] args) {
		new BB().m1();

	}
}

class AA {
	int a;
	int b;

	void m1() {
		m2();
		System.out.println("I am parent m1");
	}

	AA() {
		System.out.println("Inside AA constructor");
	}

	void m2() {
		System.out.println("I am parent m2");
	}
}

class BB extends AA {
	int a = 4;
	int b = 5;

	BB(int a, int b) {
		System.out.println(super.a + super.b);
		System.out.println(a + b);
	}

	BB() {

	}

	/*void m1() {
		System.out.println("I am child");

	}*/

	void m2() {
		System.out.println("I am child m2");

	}
}
