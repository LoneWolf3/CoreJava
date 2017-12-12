package com.sac.sams.thread;

class DaemonThread implements Runnable {
	public void run() {
		System.out.println("entering run()");

		try {
			System.out.println("in run() - currentThread()="
					+ Thread.currentThread());

			while (true) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException x) {
				}

				System.out.println("in run() - woke up again");
			}
		} finally {
			System.out.println("leaving run()");
		}
	}
}

public class DaemonThreadMain  {
	public static void main(String[] args) {
		System.out.println("entering main()");
		Thread t = new Thread(new DaemonThread());
		t.setDaemon(false);
		t.start();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException x) {
		}

		System.out.println("leaving main()");
	}
}