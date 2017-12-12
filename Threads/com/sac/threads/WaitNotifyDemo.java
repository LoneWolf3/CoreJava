package com.sac.threads;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

class EventStorage {
	private int maxSize;
	private List<Date> storage;

	public EventStorage() {
		maxSize = 2;
		storage = new LinkedList<Date>();
	}

	public synchronized void set() {
		while (storage.size() == maxSize) {
			try {
				System.out.println("Prodcuer going to wait.");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		((LinkedList<Date>) storage).offer(new Date());
		System.out.println("Element Added to Link List at position " + (storage.size()-1));
		notifyAll();
	}

	public synchronized void get() {
		while (storage.size() == 0) {
			try {
				System.out.println("Consumer going to wait.");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Element " + ((LinkedList<Date>) storage).poll()
				+ " get from LinkList from position " + storage.size());
		notifyAll();
	}
}

class Producer implements Runnable {

	private EventStorage storage;

	public Producer(EventStorage storage) {
		this.storage = storage;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			storage.set();
		}
	}
}

class Consumer implements Runnable {

	private EventStorage storage;

	public Consumer(EventStorage storage) {
		this.storage = storage;
	}

	@Override
	public void run() {
		for (int i = 0; i < 2; i++) {
			storage.get();
		}
	}
}

public class WaitNotifyDemo {
	public static void main(String[] args) {

		EventStorage storage = new EventStorage();

		Producer producer = new Producer(storage);
		Thread thread1 = new Thread(producer);

		Consumer consumer = new Consumer(storage);
		Thread thread2 = new Thread(consumer);

		thread2.start();
		thread1.start();

	}
}