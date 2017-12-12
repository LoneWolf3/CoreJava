package com.sac.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

class InterruptTask implements Runnable {
	int id;
	boolean interruptable;
	ReentrantLock lock;

	InterruptTask(ReentrantLock lock, int id) {
		this(lock, id, true);
	}

	InterruptTask(ReentrantLock lock, int id, boolean interruptable) {
		this.lock = lock;
		this.id = id;
		this.interruptable = interruptable;
	}

	public void run() {
		print("Try lock");
		try {
			if (interruptable) {
				lock.lockInterruptibly();
			} else {
				lock.lock();
			}
		} catch (InterruptedException e) {
			print("Acquiring lock failed due to " + e);
			return;
		}
		print("Got lock(" + id + ")");
		try {
			try {
				if (id == 1) {
					Thread.sleep(3000);
				} else {
					Thread.sleep(2500);
				}
			} catch (InterruptedException e) {
				print("Sleep interrupted");
			}
		} finally {
			lock.unlock();
			print("Unlocked(" + id + ")");
		}
	}

	static void print(String p) {
		System.out.println(Thread.currentThread().getName() + ": " + p);
	}
}

public class LockInterruptiblyDemo {
	private static ReentrantLock lock = new ReentrantLock();
	private boolean interruptable;

	LockInterruptiblyDemo() {
		this(true);
	}

	LockInterruptiblyDemo(boolean interruptable) {
		this.interruptable = interruptable;
	}

	public static void main(String[] args) throws InterruptedException {
		LockInterruptiblyDemo lockInterruptable = new LockInterruptiblyDemo();
		lockInterruptable.lockAndInterrupt();
	}

	void lockAndInterrupt() throws InterruptedException {
		Thread firstThread = new Thread(new InterruptTask(lock, 1, interruptable), "Thread(1)");
		firstThread.start();
		Thread.sleep(2000);
		Thread[] others = new Thread[6];
		for (int i = 2; i < 8; i++) {
			others[i - 2] = new Thread(new InterruptTask(lock, i, interruptable), "Thread(" + i + ")");
			others[i - 2].start();
		}
		System.out.println("Interrupt threads");
		for (int i = 0; i < 6; i++) {
			Thread.sleep(500 * i / 2);
			System.out.println("Interrupt " + others[i].getName());
			others[i].interrupt();
		}
	}
}