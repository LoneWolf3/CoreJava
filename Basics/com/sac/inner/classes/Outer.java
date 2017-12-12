package com.sac.inner.classes;

/**
 * We should go for inner classes when we want to make sure without existing one
 * type of object if there is no chance of other type of object e.g university
 * and Inner
 * 
 * 
 * Types of inner classes
 * 
 * 1)Normal inner class - Inner class with name, Inner class always talks about
 * instance terminology as without existing outer class object there is no
 * chance for inner class hence no static variable allowed, we can access inner
 * class from method directly as instance is required to access that method
 * Inner class can have static variables but only in methods.
 * 
 * 2)method local - Main purpose is to define method specific repeatedly
 * required func.we can use sattic variable in method local inner class, only
 * final abstract strictfp
 * 
 * 
 * 
 * 
 * 
 * anonymous class that extends a class used for one time requirement,
 * 
 * anonymous class that implements interface,
 * 
 * anonymous class that defined inside argument
 * 
 * 
 * static nested classes - Not object required, only access only static member of
 * outer class
 * 
 * @author ssachdev
 *
 */

public class Outer {
	int x = 0;
	static int z = 10000;

	class Inner {
		int x = 10;

		void m1() {
			int x = 20;
			System.out.println("inner");
			System.out.println(x);
			System.out.println(this.x);
			System.out.println(Outer.this.x);
		}

	}

	// Method local inner class
	void m2() {
		int methodLocal = 333;
		class methodInner {
			void sum(int a, int b) {
				System.out.println(z);
				System.out.println(methodLocal);
				System.out.println(a + b);
			}
		}
		methodInner obj = new methodInner();
		obj.sum(10, 20);
	}

	// static nested class
	static class staticInner {
		void m1() {
			System.out.println("Inside static");
		}
	}

	public static void main(String[] args) {
		Outer obj = new Outer();
		Outer.Inner inner = obj.new Inner();
		inner.m1();

		staticInner obj2 = new staticInner();
		obj2.m1();

		// Anonymous inner class
		Popcorn p = new Popcorn();
		p.taste();

		Popcorn p2 = new Popcorn() {
			@Override
			void taste() {
				System.out.println("sweet");
			}
		};
		p2.taste();
	}

}

class Popcorn {
	void taste() {
		System.out.println("salty");
	}
}