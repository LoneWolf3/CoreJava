package com.sac.basics;

/**
 * byte or char->short->int->long->float->double
 * 
 * a class can not have same method with same signature although return type is diff .
 * 
 * Encapsulation is data hiding + abstraction
 * 
 * if matched method is not avilable then argument are promoted e.g char is
 * promoted to int
 * 
 * 
 * If there is ambiguity that which data type to choose then go for closest
 * one.eg null is close to string then object, If two classes at same level then
 * compile time error.
 * 
 * varargs means any number of int value or 0 number of int
 * 
 * In overloading compiler always decide based upon reference .
 * 
 * @author ssachdev
 *
 */
public class Overloading {
	public static void main(String[] args) {
		Test t = new Test();
		Animal m = new Monkey();
		t.a(m);

	}
}

class A11 {
	void m1(int a) {
		System.out.println("Int");
	}

	void m1(float a) {
		System.out.println("float");
	}

	void m1(String a) {
		System.out.println("String");
	}

	void m1(StringBuffer a) {
		System.out.println("StringBuffer");
	}

	void m1(Object a) {
		System.out.println("Object");
	}
}

class Animal {

}

class Monkey extends Animal {

}

class Test {
	void a(Monkey m) {
		System.out.println("monkey");
	}

	void a(Animal m) {
		System.out.println("animal");
	}
}