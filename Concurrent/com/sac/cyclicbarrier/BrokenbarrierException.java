package com.sac.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * Consider a generalized example, when we go to a temple, we can see multiple
 * queues of people who bought a 50/- and 100/- rupee ticket. When a VIP comes,
 * both of the queues are paused at a point, after the VIP's darshan is over,
 * both the queues are resumed. Consider, here, both the queues as 2 threads,
 * and the VIP is another thread for which the other two threads has to wait.
 * 
 * When the reset() method is called, a BrokenBarrierException is raised in the
 * FiftyClass' run(). So, FiftyClass comes out of waiting state. They, simply
 * say, we can't wait and go on seeing the god. Here is the output.
 * 
 * 
 * @author ssachdev
 *
 */
public class BrokenbarrierException {
	public static void main(String args[]) {
		// No.of threads to wait
		// In general terms, no.of queues to wait
		// here 2
		CyclicBarrier cb = new CyclicBarrier(2, new GoVIP());

		// Create two threads,
		// a 50/- line and a 100/- line
		new FiftyClass(cb);
		new HundredClass(cb);
	}
}

class FiftyClass extends Thread {
	CyclicBarrier cb;

	public FiftyClass(CyclicBarrier cb) {
		// Know that FiftyCLass has to wait, so is the cyclic barrier
		// This object is used to call the await()
		this.cb = cb;

		// Start the thread
		// In general terms, let the 50/- queue move
		start();
	}

	public void run() {
		// They are just 2.5 km away from the
		// god
		System.out.println("2.5 Km to go");

		// An InterruptedException is raised.
		// In general, sometimes the situation goes
		// uncontrollable due to heavy rush
		try {
			cb.await();
		} catch (Exception e) {
			// Then, say, we can't wait
			System.out.println("We can't wait ");
		}

		// They completed their task!!
		System.out.println("Hurray! We saw our GOD! +50");
	}
}

class HundredClass extends Thread {
	CyclicBarrier cb;

	public HundredClass(CyclicBarrier cb) {
		// Take the CyclicBarrier object
		this.cb = cb;

		// Let the 100/- queue move
		start();
	}

	public void run() {
		// They are just 2 km to go, because
		// they paid a double amount!
		System.out.println("2 Km to go");
		try {
			cb.reset();
			// Slow down, guys! Let the VIP go!
			cb.await();
		} catch (Exception e) {
			System.out.println("We can't wait");
		}

		// Great, you got it for 100!
		System.out.println("Hurray! We saw our GOD! +100");
	}

}

class GoVIP implements Runnable {
	public void run() {
		// See
		System.out.println("Seeing");

		// Don't forget to say thanks to those
		// 50/- and 100/- queue people!! :)
		System.out.println("Thank you all");
	}
}