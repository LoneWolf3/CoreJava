package com.sac.sams.thread;

public class BothInMethod extends Object {
	private String objID;

	public BothInMethod(String objID) {
		this.objID = objID;
	}

	public static void  doStuff(int val) {
		System.out.println("entering doStuff()");
		/*int num = val * 2 + objID.length();
		print("in doStuff() - local variable num=" + num);*/
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
		final BothInMethod bim = new BothInMethod("obj1");
	//	final BothInMethod bim2 = new BothInMethod("obj2");
		Runnable runA = new Runnable() {
			public void run() {
				BothInMethod.doStuff(3);
			}
		};

		Thread threadA = new Thread(runA, "threadA");
		threadA.start();

		
		Runnable runB = new Runnable() {
			public void run() {
				BothInMethod.doStuff(7);
			}
		};

		Thread threadB = new Thread(runB, "threadB");
		threadB.start();
	}
}