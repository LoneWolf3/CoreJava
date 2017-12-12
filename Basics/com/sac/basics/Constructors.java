package com.sac.basics;

import java.io.IOException;

/**
 * Role of constructor is to initialization of object.
 * 
 * Inheritance not for constructors, only method transfers from parent to child
 * not constructors.
 * 
 * If you try to call child with default constructor compilation fails
 * 
 * Every class contains including abstract, not interface only.
 * 
 * recursive constructor calling - compile time error.
 * 
 * if Parent constructor throws checked exception and child construcor does not
 * then there is compile error
 * 
 * Abstract class can have constructor but only child class can use it, if
 * constructor is not there then we have to specifically initialize the values
 * 
 * @author ssachdev
 *
 */
public class Constructors {
	Constructors(int a) {

	}

	// Parent p = new Child();
	public static void main(String[] args) {
		// Constructors s = new Constructors();
	}
}

class Parent {
	Parent() throws IOException {
		// this(10);
		System.out.println("Parent");
	}

	Parent(int a) {
		// this();
	}
}

class Child extends Parent {

	Child() throws IOException {
		this(10);
		System.out.println("no");
	}

	Child(int t) throws IOException {
		this(2.5);
		System.out.println("int");

	}

	Child(double t) throws IOException {
		System.out.println("double");

	}

}