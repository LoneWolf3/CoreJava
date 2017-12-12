package com.sac.threads;

class Context {

	private String name = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* getters and setters here */

}

class B {

	private Context c = null;

	public Context get() {
		return c;
	}

	public void set(Context c) {
		this.c = c;
	}

}

class A {

	//change it to thread local so that every thread has its own varible
	public static final B userThreadLocal = new B();

	public void set(Context user) {
		userThreadLocal.set(user);
	}

	public Context get() {
		return (Context) userThreadLocal.get();
	}
}

public class ThreadLocalDemo {

	public static void main(String args[]) {
		Task obj = new Task();
		Thread threadOne = new Thread(obj);
		threadOne.start();

		Thread threadTwo = new Thread(obj);
		threadTwo.start();
	}

}

class Task implements Runnable {

	@Override
	public void run() {
		Context context = new Context();
		context.setName(Thread.currentThread().getName());
		A obj = new A();
		obj.set(context);
		Context context11 = obj.get();
		System.out.println(context11.getName());
	}

}