package com.sac.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class IteruptExceptoin {
	public static void main(String[] args) throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		// Future<String> future = executor.submit(new SlowCallable());
		Future<String> future = executor.submit(new Interruptor(Thread.currentThread()));
		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			System.out.println("I was interrupted");
		} catch (ExecutionException e) {
			System.out.println("Execution Exception" + e.getCause());
		}
	}

	private static class Interruptor implements Callable<String> {
		private final Thread threadToInterrupt;

		Interruptor(Thread threadToInterrupt) {
			this.threadToInterrupt = threadToInterrupt;
		}

		public String call() throws Exception {
			Thread.sleep(2000);
			threadToInterrupt.interrupt();
			return "interrupted other thread";
		}
	}

	private static class SlowCallable implements Callable<String> {
		public String call() throws Exception {
			Thread.sleep(5000);
			Integer.parseInt("aa");
			return "finished";
		}
	}
}