package com.sac.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Take this example. Starting thread pool size is 1, core pool size is 5, max
 * pool size is 10 and the queue is 100.
 * 
 * As requests come in, threads will be created up to 5 and then tasks will be
 * added to the queue until it reaches 100. When the queue is full new threads
 * will be created up to maxPoolSize. Once all the threads are in use and the
 * queue is full tasks will be rejected. Max pool is core pool + extra thread
 * 
 * All the task will be qued in que until que's length is full and if the length
 * is full last task is going to excecute by max pool
 * 
 * In our example we have 7 task , 1 core pool 2 max means 1 core thread and one
 * extra thread and 4 quesize
 * 
 * The difference is that execute doesn't return a Future, so you can't wait for
 * the completion of the Runnable and get any exception it throws using that.
 * 
 * 
 * 
 * Executor is the core interface which is an abstraction for parallel
 * execution. It separates task from execution, this is different from
 * java.lang.Thread class which combines both task and its execution. Since
 * creating, starting, and running a thread is a time-consuming and expensive
 * operation, many Java applications create a pool of thread at start-up and
 * leverage that for executing the task in parallel until Java introduced the
 * built-in thread pool
 * 
 * On the other hand, ExecutorService is an extension of Executor interface and
 * provides a facility for returning a Future object and terminate, or shut down
 * the thread pool. Once the shutdown is called, the thread pool will not accept
 * new task but complete any pending task. It also provides a submit() method
 * which extends Executor.execute() method and returns a Future.
 * 
 * 
 * Third one Executors is a utility class similar to Collections, which provides
 * factory methods to create different types of thread pools e.g. fixed and
 * cached thread pools. Let's see some more difference between these three
 * classes.
 * 
 * 
 * 
 * shutdownNow :
 * 
 * Attempts to stop all actively executing tasks, halts the processing of
 * waiting tasks, and returns a list of the tasks that were awaiting execution.
 * These tasks are drained (removed) from the task queue upon return from this
 * method.
 * 
 * This method does not wait for actively executing tasks to terminate. Use
 * awaitTermination to do that.
 * 
 * There are no guarantees beyond best-effort attempts to stop processing
 * actively executing tasks. This implementation cancels tasks via
 * Thread.interrupt(), so any task that fails to respond to interrupts may never
 * terminate
 * 
 * shutdown:
 * 
 * Initiates an orderly shutdown in which previously submitted tasks are
 * executed, but no new tasks will be accepted. Invocation has no additional
 * effect if already shut down.
 * 
 * This method does not wait for previously submitted tasks to complete
 * execution. Use awaitTermination to do that.
 * 
 * awaitTermination:
 * 
 * Blocks until all tasks have completed execution after a shutdown request, or
 * the timeout occurs, or the current thread is interrupted, whichever happens
 * first.
 * 
 * @author ssachdev
 *
 */
public class MyThreadPoolExecutor {
	static int runnableResult = 0;

	public static void main(String args[]) throws InterruptedException {
		System.out.println(Runtime.getRuntime().availableProcessors());
		// RejectedExecutionHandler implementation
		RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
		// Get the ThreadFactory implementation to use
		ThreadFactory threadFactory = Executors.defaultThreadFactory();
		// creating the ThreadPoolExecutor
		ExecutorService executorPool = new ThreadPoolExecutor(1, 1, 2, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(4), threadFactory, rejectionHandler);
		((ThreadPoolExecutor) executorPool).prestartAllCoreThreads();

		/*
		 * for (int i = 0; i < 7; i++) { executorPool.execute(new SumTaskRunnable(i)); }
		 */

		Future<Integer> future = null;
		for (int i = 0; i < 10; i++) {
			future = executorPool.submit(new SumCallable(10));
		}

		if (future.isDone()) {
			System.out.println("Done");

		}

		if (!future.isDone()) {
			// wait indefinitely for future task to complete try
			{
				try {
					System.out.println("FutureTask1 output=" + future.get());
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		Thread.sleep(30000);
		// shut down the pool
		executorPool.shutdown();

		// shut down the monitor thread
		Thread.sleep(5000);

	}
}

class SumTaskRunnable implements Runnable {
	int i;

	public SumTaskRunnable(int i) {
		this.i = i;// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {

		System.out.println("Inside runnable for task " + i);
	}

}

class SumCallable implements Callable<Integer> {
	private int num = 0;

	public SumCallable(int num) {
		this.num = num;
	}

	@Override
	public Integer call() throws Exception {
		int result = 0;
		for (int i = 1; i <= num; i++) {
			result += i;
		}
		return result;
	}
}

class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println(r.toString() + " is rejected");
	}

}