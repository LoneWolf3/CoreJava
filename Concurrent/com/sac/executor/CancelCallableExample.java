package com.sac.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Sleep method has IsIntruptiable flag implementation so you will not able to
 * cancel the task until u have IsIntrupted in check.
 * 
 * @author ssachdev
 *
 */
public class CancelCallableExample {
	private static class Abc implements Callable<String> {

		public String call() throws Exception {

			try {
				int i = 0;
				while (i < 100 && !Thread.currentThread().isInterrupted()) {
					System.out.println(i++);
				}

			} catch (Exception e) {
				System.out.println(e);
			}
			return "Task Done";
		}
	}

	public static void main(String[] args) throws InterruptedException {

		ExecutorService executor = Executors.newFixedThreadPool(2);
		Future<String> future = executor.submit(new Abc());
		Thread.sleep(100);
		// true to interrupt and false to let complete task but sets iscancel to true.
		future.cancel(false);
		/*
		 * If it is not interrupting it will simply tell the future that is is
		 * cancelled. You can check that via isCancelled() but nothing happens if you
		 * don't check that manually.
		 */ System.out.println(future.isCancelled());
		System.out.println("hi");
		// executor.shutdown();
		// executor.shutdownNow();
		executor.awaitTermination(5, TimeUnit.SECONDS);
		System.out.println("hi");

	}
}
