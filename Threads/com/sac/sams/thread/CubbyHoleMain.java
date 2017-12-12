package com.sac.sams.thread;

public class CubbyHoleMain extends Object {
	private static void print(String msg) {
		String name = Thread.currentThread().getName();
		System.out.println(name + " " + msg);
	}

	public static void main(String[] args) {
		final CubbyHole ch = new CubbyHole();

		Runnable runA = new Runnable() {
			public void run() {
				try {
					String str;
					Thread.sleep(1000);

					str = "1";
					ch.putIn(str);
				//	print("in run() - just put in ‘" + str + "‘");
					
					str = "2";
					ch.putIn(str);
				//	print("in run() - just put in ‘" + str + "‘");
				
					str = "3";
					ch.putIn(str);
				//	print("in run() - just put in ‘" + str + "‘");
				} catch (InterruptedException x) {
					x.printStackTrace();
				}
			}
		};

		Runnable runB = new Runnable() {
			public void run() {
				try {
					Object obj;
					Thread.sleep(500);
					obj = ch.takeOut();
					print("in run() - just took out ‘" + obj + "‘");

					Thread.sleep(500);

					obj = ch.takeOut();
					print("in run() - just took out ‘" + obj + "‘");
					Thread.sleep(500);
					obj = ch.takeOut();
					print("in run() - just took out ‘" + obj + "‘");
				} catch (InterruptedException x) {
					x.printStackTrace();
				}
			}
		};

		Thread threadA = new Thread(runA, "Thread A");
		threadA.start();

		Thread threadB = new Thread(runB, "Thread B");
		threadB.start();
	}
}