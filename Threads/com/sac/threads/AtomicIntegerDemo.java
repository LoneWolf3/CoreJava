package com.sac.threads;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {

	public static void main(String[] args) {
		int counter = 0;
		for (int i = 0; i < 10; i++) {
			Runnable runnable = new WorkerThread(counter);
			Thread t = new Thread(runnable);
			t.start();
		}

	}

}

class WorkerThread implements Runnable {
	// change this to atmoic integer to solve the problem
	static int count;

	WorkerThread(int counter) {
		count = counter;
	}

	public void run() {
		int value = increment();
		System.out.println(value);
	}

	int increment() {
		int a = count + 1;
		return a;
	}
}