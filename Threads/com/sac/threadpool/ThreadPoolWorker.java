 
package com.sac.threadpool;

import com.sac.sams.thread.Queue;

public class ThreadPoolWorker  {
	private static int nextWorkerID = 0;

	private Queue idleWorkersQue;
	private int workerID;
	private Queue handoffBox;

	private Thread internalThread;
	private volatile boolean noStopRequested;

	public ThreadPoolWorker(Queue idleWorkersQue) {
		this.idleWorkersQue = idleWorkersQue;

		workerID = getNextWorkerID();
		handoffBox = new Queue(1); // only one slot

		// just before returning, the thread should be created.
		noStopRequested = true;

		Runnable r = new Runnable() {
			public void run() {
				try {
					runWork();
				} catch (Exception x) {
					// in case ANY exception slips through
					x.printStackTrace();
				}
			}
		};

		internalThread = new Thread(r);
		internalThread.start();
	}

	public static synchronized int getNextWorkerID() {
		// notice sync’d at the class level to ensure uniqueness
		int id = nextWorkerID;
		nextWorkerID++;
		return id;
	}

	public void process(Runnable target) throws InterruptedException {
		handoffBox.add(target);
	}

	private void runWork() {
		while (noStopRequested) {
			try {
				System.out.println("workerID=" + workerID + ", ready for work");
				// Worker is ready work. This will never block
				// because the idleWorker FIFO queue has
				// enough capacity for all the workers.
				idleWorkersQue.add(this);

				// wait here until the server adds a request
				Runnable r = (Runnable) handoffBox.remove();

				System.out.println("workerID=" + workerID
						+ ", starting execution of new Runnable " + r);
				runIt(r); // catches all exceptions
			} catch (InterruptedException x) {
				Thread.currentThread().interrupt(); // reassert
			}
		}
	}

	private void runIt(Runnable r) {
		try {
			r.run();
		} catch (Exception runex) {
			// catch any and all exceptions
			System.err.println("Uncaught exception fell through from run()");
			runex.printStackTrace();
		} finally {
			// Clear the interrupted flag (in case it comes back
			// set) so that if the loop goes again, the
			// handoffBox.remove() does not mistakenly
			// throw an InterruptedException.
			Thread.currentThread();
		}
	}

	public void stopRequest() {
		System.out
				.println("workerID=" + workerID + ", stopRequest() received.");
		noStopRequested = false;
		internalThread.interrupt();
	}

	public boolean isAlive() {
		return internalThread.isAlive();
	}
}