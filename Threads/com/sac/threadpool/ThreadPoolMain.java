package com.sac.threadpool;

/**
 * create a idle worker list equal to number of threads
 * 
 * create equal number of threadpool workers array
 * 
 * Intialize the workers: each worker will have a workerID
 * 
 * each worker will have a 1 lenght que(handbox) to hold task
 * 
 * each worker will have access to queue
 * 
 * each worker will start a thread that enters into runwork method in run method
 * we add this object into idle worker queue and remove the element from handbox
 * which initallyis empty
 * 
 * Now in excute method of thread pool we we remove the worker from queue and
 * process the task
 * 
 * In process method we add value in handbox queue so that our waiting thread
 * unblocks
 * 
 * @author ssachdev
 *
 */
public class ThreadPoolMain extends Object {

	public static Runnable makeRunnable(final String name, final long firstDelay) {

		return new Runnable() {
			public void run() {
				try {
					System.out.println(name + " starting up");
					Thread.sleep(firstDelay);
					System.out.println(name + " doing some stuff");
					Thread.sleep(2000);
					System.out.println(name + " leaving");
				} catch (InterruptedException ix) {
					System.out.println(name + " got interrupted!");
					return;
				} catch (Exception x) {
					x.printStackTrace();
				}
			}

			public String toString() {
				return name;
			}
		};
	}

	public static void main(String[] args) {
		try {
			ThreadPool pool = new ThreadPool(2);

			Runnable ra = makeRunnable("RA", 3000);
			pool.execute(ra);

			Runnable rb = makeRunnable("RB", 1000);
			pool.execute(rb);

			Runnable rc = makeRunnable("RC", 2000);
			pool.execute(rc);

			Runnable rd = makeRunnable("RD", 60000);
			pool.execute(rd);

			Runnable re = makeRunnable("RE", 1000);
			pool.execute(re);

			pool.stopRequestIdleWorkers();
			Thread.sleep(2000);
			pool.stopRequestIdleWorkers();

			Thread.sleep(5000);
			pool.stopRequestAllWorkers();
		} catch (InterruptedException ix) {
			ix.printStackTrace();
		}
	}
}