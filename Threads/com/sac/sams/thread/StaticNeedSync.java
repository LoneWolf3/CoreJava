package com.sac.sams.thread;

public class StaticNeedSync implements Runnable {

	public  synchronized void doThings() {
		  {
			int x1 = 0;
			System.out.println("Thread is holding X1 = " + x1);
			System.out.println("Thread is printing X1 = " + x1);
		}

	}

	public void run() {
		doThings();
	}

	public static void main(String[] args) {
		StaticNeedSync obj = new StaticNeedSync();
		StaticNeedSync obj2 = new StaticNeedSync();

		Thread a = new Thread(obj);
		a.start();

		Thread b = new Thread(obj2);
		b.start();

	}
}