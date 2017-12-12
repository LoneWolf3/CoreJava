package com.sac.sams.thread;

public class CubbyHole extends Object {
	private Object slot;

	public CubbyHole() {
		slot = null; // null indicates empty
	}

	public synchronized void putIn(Object obj) throws InterruptedException {

		print("Putting value in bucket -------"+obj);

		while (slot != null) {
			print("in putIn()  occupied, about to wait()");
			wait();// wait while slot is occupied
			print("in putIn()  notified, back from wait()");
		}

		slot = obj; // put object into slot
		print("notifyAll()");
		notifyAll();// signal that slot has been filled

		
	}

	public synchronized Object takeOut() throws InterruptedException {

		print("Taking out of bucket ======================");

		while (slot == null) {
			print("Slot is empty and WAITING.....");
			wait();// wait while slot is empty
			print("Notified, and comes back from WAIT...");
		}

		Object obj = slot;
		slot = null; // mark slot as empty
		print(" notifyAll() done===========");
		notifyAll();// signal that slot is empty

		return obj;
	}

	private static void print(String msg) {
		String name = Thread.currentThread().getName();
		System.out.println(name + " " + msg);
	}
}
