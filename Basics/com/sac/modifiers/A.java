package com.sac.modifiers;

import com.sac.basics.A;
import com.sac.pac1.*;

/**
 * Private: Same class.
 * 
 * Default: Same class, Same package.
 * 
 * Protected: Same class, Same package, Subclasses object.
 * 
 * Public: Same class, Same package, Subclasses, Everyone.
 * 
 * 
 * Class level modifiers - package private, public ,abstract,final
 * 
 * abstract method cannot be static or final
 * 
 * final class cannot have abstract but abstract class can have final if
 * abstract mehtod is there then class must be abstract class,abstract mthd can
 * not be private
 * 
 * 
 * if a instance variable is final it should be initialized. if not then has to
 * be initalized in consutructor or instance block
 * 
 * final and static go in hand because only one copy is required among objects
 * and that needs to be changed
 * 
 * Local variables needs to be initialized.Formal parametre can be final, means
 * with in method you can not change formal param value.
 * 
 * @author ssachdev
 *
 */
class A {
	private int time;
	public int time2;
	// public final int f;

}

class B extends OtherPackage {
	public static void main(String[] args) {
		A obj = new A();
		B obj2 = new B();
		SamePackage obj3 = new SamePackage();
		OtherPackage obj4 = new OtherPackage();
		// System.out.println(obj.time);
		System.out.println(obj.time2);
		System.out.println(obj2.time5);
		System.out.println(obj3.time3);
		System.out.println(obj3.time4);
		// System.out.println(obj4.time6);
	}
}

final class D {
	// abstract void m1();
}

abstract class E {
	final void m1() {
		System.out.println("Hello World");
	}

	public void m2() {
		int local;
		System.out.println("Hello World");
		// System.out.println(local);
	}

	abstract void m3(final int a);
}

class F extends E {

	public static void main(String[] args) {
		F obj = new F();
		obj.m3(0);
	}

	@Override
	void m3( int a) {
		
	}

	@Override
	public void m2() {
		// TODO Auto-generated method stub

	}

	/*
	 * @Override public void m1() { // TODO Auto-generated method stub
	 * 
	 * }
	 */}