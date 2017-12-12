package com.sac.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * The HRManger class created thre instances of TechLead's and starts them. The
 * TechLead is a simple thread which waits for 2 sec and completes recruitment.
 * The HRManager also creates a CountDownLatch object with a parameter as '3'.
 * This instance is passed to every TechLead instance. After every TechLead has
 * compeleted the recruitment techlead calls countDown() method on the shared
 * CountDownLatch object.
 * 
 * The HRManager after starting the TechLead threads waits by calling awat()
 * method on CountDownLatch instance. Only after countDown() is called 3 times
 * the HRManager can proceed further.
 * 
 * @author ssachdev
 *
 */

public class HRManager {

	public static void main(String args[]) {
		CountDownLatch countDownLatch = new CountDownLatch(3);

		TechLead techLead1 = new TechLead(countDownLatch, "first");
		TechLead techLead2 = new TechLead(countDownLatch, "second");
		TechLead techLead3 = new TechLead(countDownLatch, "third");

		techLead1.start();
		techLead2.start();
		techLead3.start();

		try {
			System.out.println("HR Manager waiting for recruitment to complete...");

			countDownLatch.await();

			System.out.println("Distribute Offer Letter");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
