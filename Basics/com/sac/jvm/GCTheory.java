package com.sac.jvm;

import java.lang.ref.WeakReference;

/**
 * Memory leaks - Use less objects that we just created but did not use ever
 * 
 * if object has null ref. jvm runs Garbage collector which calls finalize
 * method on object and destroys
 * 
 * When system.gc is called a new garbage collector thread is invoked and acts
 * on heap.
 * 
 * Finalize method is called once and only once
 * 
 * JVM will ignore any exception in finalize method if called by GC
 * 
 * Destroy method and finalize are same in behavior and also needs to check on
 * which object garbage collector is called
 * 
 * 
 * Strong References Weak References Soft References Phantom References
 * 
 * @author ssachdev
 *
 */

public class GCTheory {
	public static void main(String[] args) {

		B b = new B();
		WeakReference<B> bRef = new WeakReference<B>(b);
		C c = new C(b);
		A a = new A(c);
		b = null;

		System.out.println("Run gc");
		Runtime.getRuntime().gc();

		System.out.println("bRef's referent:" + bRef.get());
		System.out.println("bRef's referent thru a->c->d->bRef:" + a.getC().getD().getB());

	}
}

class A {
	private C c;

	public A(C c) {
		this.c = c;
	}

	public C getC() {
		return c;
	}

	@Override
	public void finalize() {
		System.out.println("A cleaned");
	}
}

class B {

	@Override
	public void finalize() {
		System.out.println("B cleaned");
	}
}

class C {
	private D d;

	public C(B b) {
		d = new D(new WeakReference<B>(b));
	}

	public D getD() {
		return d;
	}

	@Override
	public void finalize() {
		System.out.println("C cleaned");
	}
}

class D {
	private WeakReference<B> bRef;

	public D(WeakReference<B> bRef) {
		this.bRef = bRef;
	}

	public B getB() {
		return bRef.get();
	}

	@Override
	public void finalize() {
		System.out.println("D cleaned");
	}
}
