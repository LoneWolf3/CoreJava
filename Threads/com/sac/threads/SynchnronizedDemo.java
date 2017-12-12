package com.sac.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchnronizedDemo extends Object {
	private String objID;

	public SynchnronizedDemo(String objID) {
		this.objID = objID;
	}

	public static void doStuff(int val) {
		System.out.println("entering doStuff()");
		/*
		 * int num = val * 2 + objID.length();
		 * print("in doStuff() - local variable num=" + num);
		 */
		System.out.println("leaving doStuff()");
	}

	public void print(String msg) {
		threadPrint("objID=" + objID + " - " + msg);
	}

	public static void threadPrint(String msg) {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " " + msg);
	}

	public static void main(String[] args) {
		final AA obj = new AA();
		Runnable runA = new Runnable() {
			public void run() {
				obj.doStuff();
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
				obj.doStuff();
			}
		};

		Thread threadB = new Thread(runB, "threadB");
		threadB.start();
	}

}

class AA {
	// ReentrantLock lock = new ReentrantLock();
	Lock lock = new ReentrantLock();

	void doStuff() {
		// synchronized (this) {
		lock.lock();
		dostuff2();
		lock.unlock();
		// }
	}

	void dostuff2() {
		{
			synchronized (this) {
				lock.lock();
				System.out.println("inside do stuff 2");
				lock.unlock();

			}
		}
	}
}