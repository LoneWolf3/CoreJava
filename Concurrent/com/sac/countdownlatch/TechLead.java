package com.sac.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class TechLead extends Thread {

	CountDownLatch countDownLatch;

	public TechLead(CountDownLatch countDownLatch, String name) {
		super(name);
		this.countDownLatch = countDownLatch;

	}

	@Override
	public void run() {
		try {

			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName() + " : recruted");

		countDownLatch.countDown();
	}

}