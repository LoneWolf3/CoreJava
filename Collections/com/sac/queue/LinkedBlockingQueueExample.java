package com.sac.queue;

import java.util.concurrent.LinkedBlockingQueue;

class TicketProducer implements Runnable {

	private LinkedBlockingQueue<Object> queue;
	private boolean running;

	public TicketProducer(LinkedBlockingQueue<Object> queue) {
		this.queue = queue;
		running = true;
	}

	// We need to check if the producer thread is
	// Still running, and this method will return
	// the state (running/stopped).
	public boolean isRunning() {
		return running;
	}

	@Override
	public void run() {

		// We are adding tickets using put() which waits
		// until it can actually insert elements if there is
		// not space in the queue.
		for (int i = 0; i < 15; i++) {
			String element = "Ticket" + i;

			try {
				queue.put(element);
				System.out.println("Ticket added: " + element);
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("TicketProducer Completed.");
		running = false;
	}

}

class TicketConsumer implements Runnable {
	private LinkedBlockingQueue<Object> queue;
	private TicketProducer ticketProducer;

	public TicketConsumer(LinkedBlockingQueue<Object> queue, TicketProducer ticketProducer) {
		this.queue = queue;
		this.ticketProducer = ticketProducer;
	}

	@Override
	public void run() {

		// As long as the producer is running,
		// we remove ticket from the queue.
		while (ticketProducer.isRunning()) {

			try {
				System.out.println("Removing Ticket: " + queue.take());

				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("TicketConsumer completed.");
	}
}

class TicketWatcher implements Runnable {

	private LinkedBlockingQueue<Object> queue;
	private TicketProducer ticketProducer;

	public TicketWatcher(LinkedBlockingQueue<Object> queue, TicketProducer ticketProducer) {
		this.queue = queue;
		this.ticketProducer = ticketProducer;
	}

	@Override
	public void run() {

		// As long as the producer is running,
		// we want to check for tickets.
		while (ticketProducer.isRunning()) {
			System.out.println("Tickets right now: " + queue);

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("TicketWatcher Completed.");
		System.out.println("Final Tickets in the queue: " + queue);
	}

}

public class LinkedBlockingQueueExample {

	public static void main(String[] args) throws InterruptedException {
		LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>(10);

		TicketProducer producer = new TicketProducer(queue);
		TicketWatcher watcher = new TicketWatcher(queue, producer);
		TicketConsumer consumer = new TicketConsumer(queue, producer);

		Thread producerThread = new Thread(producer);
		Thread watcherThread = new Thread(watcher);
		Thread consumerThread = new Thread(consumer);

		producerThread.start();
		Thread.sleep(2000);
		consumerThread.start();
		Thread.sleep(2000);
		watcherThread.start();
	}
}