package com.sac.Thread.cookbook;

import java.util.Random;

class MyThreadGroup extends ThreadGroup {
	public MyThreadGroup(String name) {
		super(name);
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		
		System.out.printf("The thread %s has thrown an Exception\n", t.getId());
		e.printStackTrace(System.out);
		System.out.printf("Terminating the rest of the Threads\n");
		interrupt();
	}
}

class Task implements Runnable {

	@Override
	public void run() {

		
		while (true) {
		System.out.println("Inside Runs");
		if (Thread.currentThread().isInterrupted()) {
			System.out.printf("%d : Interrupted*******************\n", Thread.currentThread().getId());
			return;
		}
			int result = "s".charAt(5);
			//System.out.printf("%s : %f\n", Thread.currentThread().getId(), result);
			
			
		}
	}
}

public class ThreadGroupException {
	public static void main(String[] args) {

		MyThreadGroup threadGroup = new MyThreadGroup("MyThreadGroup");

		Task task = new Task();

		for (int i = 0; i < 2; i++) {
			Thread t = new Thread(threadGroup, task);
			t.start();
		}
	}
}
