package com.sac.Thread.cookbook;

import java.lang.Thread.UncaughtExceptionHandler;

public class ExceptionInRun {
	public static void main(String[] args) {
		RunException r = new RunException();
		Thread t = new Thread(r);
		t.start();
		t.setUncaughtExceptionHandler(new ExceptionHandler());
		System.out.println("Code called after exception is thrown in MAIN");
	}

}

class RunException implements Runnable {

	@Override
	public void run() {
		// int numero=Integer.parseInt("TTT");

		int numero = Integer.parseInt("TTT");

		System.out.println("Code called after exception is thrown in Thread");

	}

}

class ExceptionHandler implements UncaughtExceptionHandler {
	public void uncaughtException(Thread t, Throwable e) {
		System.out.printf("An exception has been captured\n");
		System.out.printf("Thread: %s\n", t.getId());
		System.out.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
		System.out.printf("Stack Trace: \n");
		e.printStackTrace(System.out);
		System.out.printf("Thread status: %s\n", t.getState());
	}
}