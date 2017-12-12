package com.sac.queue;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * There are some mail client which sends a mail to recipients after certain
 * delay. Consider the scenario,after clicking on the send mail button you have
 * realized that you missed some important content to write / forgot to add
 * someone important in the mail chain. So here each outgoing mail can be added
 * in DelayQueue with a specific delay time, after expiration the mail will be
 * be originally sent to recipients. Let us check the example:
 * 
 * In the implementation class Email of the Delayed interface, we have to
 * implement the getDelay and the compareTo methods.
 * 
 * Email.java
 * 
 * @author ssachdev
 *
 */
class Email implements Delayed {

	private String receipient;
	private String mailBody;
	private long startTime;

	public Email(String receipient, String body, long delay) {
		this.receipient = receipient;
		this.mailBody = body;
		this.startTime = System.currentTimeMillis() + delay;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		long diff = startTime - System.currentTimeMillis();
		return unit.convert(diff, TimeUnit.MILLISECONDS);
	}

	@Override
	public int compareTo(Delayed o) {
		if (this.startTime < ((Email) o).startTime) {
			return -1;
		}
		if (this.startTime > ((Email) o).startTime) {
			return 1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "Email [receipient=" + receipient + ", mailBody=" + mailBody + ", startTime=" + startTime + "]";
	}
}

/***
 * In DelayQueueProducer, we are creating a Email object with its attributes and
 * pushed it to our DelayQueue.
 */

class DelayQueueProducer {

	private BlockingQueue queue;
	private final Random random = new Random();

	private static final String emailBody = "Email body text with delay :: ";

	public DelayQueueProducer(BlockingQueue queue) {
		super();
		this.queue = queue;
	}

	private Thread producerThread = new Thread(new Runnable() {
		@Override
		public void run() {
			while (true) {
				try {

					// Put Random delay for each email to send.
					int delay = 10000;
					String receipient = UUID.randomUUID().toString() + "@gmail.com";
					Email email = new Email(receipient, emailBody + delay, delay);

					System.out.printf("Put emial in a DelayQueue = %s%n", email);
					queue.put(email);
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}, "Producer Thread");

	public void start() {
		this.producerThread.start();
	}
}

/***
 * In DelayQueueConsumer, it tries to find the email that has expired the delay
 * and takes them from the queue and send the email to the recipient list,if it
 * could not, it waits until an element will be put and expired.
 */

class DelayQueueConsumer {

	private String name;
	private BlockingQueue queue;

	public DelayQueueConsumer(String name, BlockingQueue queue) {
		super();
		this.name = name;
		this.queue = queue;
	}

	private Thread consumerThread = new Thread(new Runnable() {
		@Override
		public void run() {
			while (true) {
				try {
					// Take the email out from the DelayQueue object and send the mail
					Email emial = (Email) queue.take();
					System.out.printf("[%s] - Sending mail when delay is over = %s%n", Thread.currentThread().getName(),
							emial);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	});

	public void start() {
		this.consumerThread.setName(name);
		this.consumerThread.start();
	}

}

/***
 * DelayQueueTest.java :
 * 
 * Finally, we are running the DelayQueueTest where we are creating a new
 * DelayQueue of Email Object and starting a new DelayQueueProducer then we let
 * our new DelayQueueConsumer to take and print the expired emails.
 */

public class DelayQueueTest {

	public static void main(String[] args) throws InterruptedException {
		// Creates an instance of blocking queue using the DelayQueue.
		BlockingQueue queue = new DelayQueue();

		// Starting DelayQueue Producer to push some delayed objects to the queue
		new DelayQueueProducer(queue).start();

		// Starting DelayQueue Consumer to take the expired delayed objects from the
		// queue
		new DelayQueueConsumer("Consumer Thread-1", queue).start();
	}
}