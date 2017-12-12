package com.sac.sams.thread;

public class CorruptWrite extends Object {
	private String fname;
	private String lname;

	public synchronized String getNames() {
		return fname + ", " + lname;
	}

	public  void setNames(String firstName, String lastName) {
		print("entering setNames()");
		fname = firstName;
		print("first Name() - " + fname);
		// A thread might be swapped out here, and may stay
		// out for a varying amount of time. The different
		// sleep times exaggerate this.

		try {
			Thread.sleep(10000);
		} catch (InterruptedException x) {
		}

		lname = lastName;

		print("leaving setNames() - " + fname + ", " + lname);
	}

	public static void print(String msg) {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " " + msg);
	}

	public static void main(String[] args) {
		final CorruptWrite cw = new CorruptWrite();

		Runnable runA = new Runnable() {
			public void run() {
				cw.setNames("George", "Washington");
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
				print("gname=" + cw.getNames());
			}
		};

		Thread threadB = new Thread(runB, "threadB");
		threadB.start();
	}
}