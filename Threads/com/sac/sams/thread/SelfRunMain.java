package com.sac.sams.thread;

public class SelfRunMain extends Object {
	public static void main(String[] args) {
		SelfRun sr = new SelfRun();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException x) {
		}

		sr.stopRequest();
	}
}