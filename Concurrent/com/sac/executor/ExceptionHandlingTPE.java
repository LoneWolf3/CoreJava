package com.sac.executor;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExceptionHandlingTPE {
	public static void main(String[] args) {

		/*
		 * // Approach 1 run method can not throw exception hence it is never caught and
		 * is // handled by JVM only
		 * 
		 * ExecutorService threadPool = Executors.newFixedThreadPool(10); try {
		 * threadPool.execute(new MyTask()); } catch (Exception e) {
		 * System.out.println("hiiii"); }
		 */

		// Approach 2 - Use legacy exception handler method of thread class via
		// threadfactory
		/*
		 * ThreadFactory factory = new MyThreadFactory(new MyExceptionHandler());
		 * ExecutorService threadPool = Executors.newFixedThreadPool(10, factory);
		 * threadPool.execute(new T2()); threadPool.execute(new T2());
		 */
		// Approach 3

		/*
		 * ThreadPoolExecutor ex = new ExtendedExecutor1(1, 1, 60, TimeUnit.SECONDS, new
		 * LinkedBlockingQueue<Runnable>()); ex.submit(new T2());
		 */

		// Approach4

		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		Future<?> f = threadPool.submit(new T2());
		try {
			System.out.println(f.get());
		} catch (ExecutionException e) {
			System.out.println("Execution exception occured in callable");
		} catch (InterruptedException e) {
			System.out.println("I was interrupted");
		}

	}
}

class T1 implements Runnable {
	@Override
	public void run() {
		try {
			System.out.println("My task is started running...");

			anotherMethod();

		} catch (Throwable t) {
			System.err.println("Uncaught exception is detected! " + t);

		}
	}

	private void anotherMethod() {
		throw new ArithmeticException();
	}
}

class T2 implements Runnable {
	@Override
	public void run() {

		System.out.println("My task is started running...");

		throw new ArithmeticException();

	}

}

class MyThreadFactory implements ThreadFactory {
	private static final ThreadFactory defaultFactory = Executors.defaultThreadFactory();
	private final Thread.UncaughtExceptionHandler handler;

	public MyThreadFactory(Thread.UncaughtExceptionHandler handler) {
		this.handler = handler;
	}

	@Override
	public Thread newThread(Runnable run) {
		Thread thread = defaultFactory.newThread(run);
		thread.setUncaughtExceptionHandler(handler);
		return thread;
	}
}

// Approach 2
class MyExceptionHandler implements UncaughtExceptionHandler {
	@Override
	public void uncaughtException(Thread thread, Throwable t) {
		System.err.println("Uncaught exception is detected! " + t + " st: " + Arrays.toString(t.getStackTrace()));
		// ... Handle the exception
	}
}

// Approach 3
class ExtendedExecutor1 extends ThreadPoolExecutor {
	public ExtendedExecutor1(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {

		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
		// If submit() method is called instead of execute()
		if (t == null && r instanceof Future<?>) {
			try {
				Object result = ((Future<?>) r).get();
			} catch (CancellationException e) {
				t = e;
			} catch (ExecutionException e) {
				t = e.getCause();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		if (t != null) {
			// Exception occurred
			System.err.println("Uncaught exception is detected! " + t + " st: " + Arrays.toString(t.getStackTrace()));
			// ... Handle the exception
			// Restart the runnable again
			execute(r);
		}
		// ... Perform cleanup actions
	}

}

final class MyTask implements Runnable {
	@Override
	public void run() {
		System.out.println("My task is started running...");
		// ...
		throw new ArithmeticException(); // uncatched exception
		// ...
	}
}
