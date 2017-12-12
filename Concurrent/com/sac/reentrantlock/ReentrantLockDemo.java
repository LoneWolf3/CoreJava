package com.sac.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Why is it named as Reentrant?: ReentrantLock allows the threads to enter into
 * lock more than one times even if the thread is already holding lock. When
 * first time thread enters into lock, a hold count is set to one. Before unlock
 * thread can re-enter into lock again and every time hold count is incremented
 * by one. For every unlock request, hold count is decremented by one and when
 * hold count is 0, it is unlocked.
 * 
 * Reentrant Lock was made to enhance intrinsic locking capabilities used by
 * synchronized . here is one more way to implement locking/synchronizing i.e.
 * using Explicit Locks. From Java 1.5 java.util.concurrent.locks used by
 * rentrant lock uses explicit locking
 * 
 * There are many reasons to use explicit reasons. One of them is tryLock()
 * method which helps to prevent the deadlock scenarion. I ll we discussing this
 * in later blogs. But for now I am going to use a example to show ‘How can we
 * ensure the thread scheduling fairness between threads using Explicit Locks‘.
 * 
 * lock(): When a thread calls lock() method, thread will get lock only if no
 * other thread is already holding the lock on the resource. If no other thread
 * is already holding the lock, the current thread gets the lock and control
 * returns from lock() method immediately. After getting lock, hold count
 * becomes one and if thread again requests for lock, and gets success, hold
 * count will be incremented by one. If the lock is held by another thread,
 * current thread will be blocked until it gets lock successfully. If the hold
 * count for current thread is greater than 0, it means it is holding the lock.
 * 
 * tryLock(): When the thread calls tryLock() on the resource then if the
 * resource is available, thread acquires the lock and tryLock() returns true
 * and hold count is incremented by 1, no matter that other threads are waiting
 * for lock
 * 
 * When the current thread calls lockInterruptibly() and the resource is free,
 * this thread acquires lock and the hold count is incremented by one and
 * returns immediately. If the resource is already held by any other thread,
 * then it will wait until it gets lock or any other thread interrupts it. It
 * means if current thread is waiting for lock but mean while any other thread
 * reaches to acquire lock, then the previous one will be interrupted and
 * returns immediately without acquiring lock
 * 
 * @author ssachdev
 *
 */
public class ReentrantLockDemo {
	final ReentrantLock reentrantLock = new ReentrantLock();

	public static void main(String[] args) {
		new ReentrantLockDemo().m1();

	}

	void m1() {

		Runnable runA = new Runnable() {
			public void run() {
				add(2);
			}
		};

		Thread threadA = new Thread(runA, "threadA");
		threadA.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException x) {
		}

		Runnable runB = new Runnable() {
			public void run() {
				add(1);
			}
		};

		Thread threadB = new Thread(runB, "threadB");
		threadB.start();
	}

	public void add(int value) {
		try {
			if (reentrantLock.tryLock(10, TimeUnit.SECONDS)) {
				System.out.println(Thread.currentThread().getName() + ": Lock acquired.");
				System.out.println("Performing task...");
			}
		}

		catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println(Thread.currentThread().getName() + ": Lock released.");
			reentrantLock.unlock();
		}
	}
}
