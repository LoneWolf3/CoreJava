package com.sac.Thread.cookbook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * As we have already described, Conditions are being used in order for a thread
 * to be notified, when a condition is true. One fundamental example that
 * demonstrates the usage of Conditions is the producer-consumer example.
 * According to this model, a thread produces a number of items and places them
 * to a shared queue, while a thread consumes these objects, by removing them
 * from the shared queue. Important: Notice that the model supports the presence
 * of multiple producers and consumers, but in this example, we will demonstrate
 * the simple case where we have one producer and one consumer. In addition, it
 * is important to mention that the shared queue is accessed by multiple threads
 * and thus, it must be properly synchronized. Our implementation of the shared
 * queue is shown below:
 * 
 * @author ssachdev
 *
 */
public class ConditionalLocking {
	public static void main(String[] args) throws InterruptedException {
		SharedFiFoQueue sharedQueue = new SharedFiFoQueue(10);

		// Create a producer and a consumer.
		Thread producer = new ProducerCL(sharedQueue);
		Thread consumer = new ConsumerCL(sharedQueue);

		// Start both threads.
		producer.start();
		consumer.start();

		// Wait for both threads to terminate.
		producer.join();
		consumer.join();
	}
}

class SharedFiFoQueue {

	private Object[] elems = null;
	private int current = 0;
	private int placeIndex = 0;
	private int removeIndex = 0;

	private final Lock lock = new ReentrantLock();
	private final Condition isEmpty = lock.newCondition();
	private final Condition isFull = lock.newCondition();

	public SharedFiFoQueue(int capacity) {
		this.elems = new Object[capacity];
	}

	public void add(Object elem) throws InterruptedException {
		lock.lock();
		while (current >= elems.length)
			isFull.await();

		elems[placeIndex] = elem;

		// We need the modulo, in order to avoid going out of bounds.
		placeIndex = (placeIndex + 1) % elems.length;

		++current;

		// Notify the consumer that there is data available.
		isEmpty.signal();

		lock.unlock();
	}

	public Object remove() throws InterruptedException {
		Object elem = null;

		lock.lock();
		while (current <= 0)
			isEmpty.await();

		elem = elems[removeIndex];

		// We need the modulo, in order to avoid going out of bounds.
		removeIndex = (removeIndex + 1) % elems.length;

		--current;

		// Notify the producer that there is space available.
		isFull.signal();

		lock.unlock();

		return elem;
	}
}

class ProducerCL extends Thread {
	private final static String FILENAME = "input.txt";
	private final SharedFiFoQueue queue;

	public ProducerCL(SharedFiFoQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		BufferedReader rd = null;

		try {
			rd = new BufferedReader(new FileReader(FILENAME));

			String inputLine = null;
			while ((inputLine = rd.readLine()) != null) {
				String[] inputWords = inputLine.split(" ");

				for (String inputWord : inputWords)
					queue.add(inputWord);
			}

			// Terminate the execution.
			queue.add(null);
		} catch (InterruptedException ex) {
			System.err.println("An InterruptedException was caught: " + ex.getMessage());
			ex.printStackTrace();
		} catch (IOException ex) {
			System.err.println("An IOException was caught: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				if (rd != null)
					rd.close();
			} catch (IOException ex) {
				System.err.println("An IOException was caught: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
}

class ConsumerCL extends Thread {
	private final Set seenObjects = new HashSet();
	private int total = 0;
	private final SharedFiFoQueue queue;

	public ConsumerCL(SharedFiFoQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			do {
				Object obj = queue.remove();
				if (obj == null)
					break;

				if (!seenObjects.contains(obj)) {
					++total;
					seenObjects.add(obj);
				}

				System.out.println("[Consumer] Read the element: " + obj.toString());

			} while (true);
		} catch (InterruptedException ex) {
			System.err.println("An InterruptedException was caught: " + ex.getMessage());
			ex.printStackTrace();
		}

		System.out.println("\n[Consumer] " + total + " distinct words have been read...");
	}
}