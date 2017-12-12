package com.sac.executor;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledTPEExample {
	public static void main(String args[]) {
		final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

		final ScheduledFuture beeperHandler = scheduler.scheduleAtFixedRate(new Task("task 1"), 1, 4, TimeUnit.SECONDS);
		/*
		 * final ScheduledFuture beeperHandler2 = scheduler.scheduleWithFixedDelay(new
		 * Task("task 2"), 1, 4, TimeUnit.SECONDS);
		 */

		scheduler.schedule(new Runnable() {
			public void run() {
				beeperHandler.cancel(true);
				// beeperHandler2.cancel(true);
				scheduler.shutdown();
			}
		}, 30, TimeUnit.SECONDS);
	}
}

class Task implements Runnable {
	private String name;

	public Task(String n) {
		name = n;
	}

	public void run() {
		System.out.println(name + " run.. " + new Date());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}