package com.sac.threads;

public class ExtrendsDemo extends Thread {

	public static void main(String[] args) {
		Thread t = new Thread();
		t.start();
		ExtrendsDemo obj =new  ExtrendsDemo();
		try {
			obj.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("Inside rum");
	}
}
