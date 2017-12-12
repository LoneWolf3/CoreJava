package com.sac.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A light wrapper around the {@link ThreadPoolExecutor}. It allows for you to
 * pause execution and resume execution when ready. It is very handy for games
 * that need to pause.
 *
 * @author Matthew A. Johnston (warmwaffles)
 */
public class PausableThreadPoolExecutor extends ThreadPoolExecutor {
	private boolean isPaused;
	private ReentrantLock lock;
	private Condition condition;

	/**
	 * @param corePoolSize
	 *            The size of the pool
	 * @param maximumPoolSize
	 *            The maximum size of the pool
	 * @param keepAliveTime
	 *            The amount of time you wish to keep a single task alive
	 * @param unit
	 *            The unit of time that the keep alive time represents
	 * @param workQueue
	 *            The queue that holds your tasks
	 * @see {@link ThreadPoolExecutor#ThreadPoolExecutor(int, int, long, TimeUnit, BlockingQueue)}
	 */
	public PausableThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		lock = new ReentrantLock();
		condition = lock.newCondition();
	}

	/**
	 * @param thread
	 *            The thread being executed
	 * @param runnable
	 *            The runnable task
	 * @see {@link ThreadPoolExecutor#beforeExecute(Thread, Runnable)}
	 */
	@Override
	protected void beforeExecute(Thread thread, Runnable runnable) {
		super.beforeExecute(thread, runnable);
		lock.lock();
		try {
			while (isPaused)
				condition.await();
		} catch (InterruptedException ie) {
			thread.interrupt();
		} finally {
			lock.unlock();
		}
	}

	public boolean isRunning() {
		return !isPaused;
	}

	public boolean isPaused() {
		return isPaused;
	}

	/**
	 * Pause the execution
	 */
	public void pause() {
		lock.lock();
		try {
			isPaused = true;
		} finally {
			lock.unlock();
		}
	}

	/**
	 * Resume pool execution
	 */
	public void resume() {
		lock.lock();
		try {
			isPaused = false;
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}
}

/**
 * A thin wrapper around a thread pool executor that only exposes partially what
 * the executor is doing. This is so that we don't make a mistake somewhere
 * along the way and jack something up.
 *
 * @author Matthew A. Johnston (warmwaffles)
 */
class Scheduler {
	private PausableThreadPoolExecutor executor;
	private LinkedBlockingQueue<Runnable> queue;

	public Scheduler() {
		int processors = Runtime.getRuntime().availableProcessors();
		queue = new LinkedBlockingQueue<Runnable>();
		executor = new PausableThreadPoolExecutor(processors, 10, 10, TimeUnit.SECONDS, queue);
	}

	public void schedule(Runnable runnable) {
		executor.execute(runnable);
	}

	public void pause() {
		executor.pause();
	}

	public void resume() {
		executor.resume();
	}

	public void clear() {
		queue.clear();
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}
}
