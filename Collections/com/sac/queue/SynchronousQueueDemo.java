package com.sac.queue;

import java.util.concurrent.SynchronousQueue;

class PutRunnable<T> implements Runnable {
	private T value;
	private SynchronousQueue<T> syncQ;

	PutRunnable(SynchronousQueue<T> syncQ, T value) {
		this.syncQ = syncQ;
		this.value = value;
	}

	public void run() {
		try {
			print("Put " + value);
			syncQ.put(value);
			print("Returned from put");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void print(String s) {
		System.out.println(Thread.currentThread().getName() + ":" + s);
	}

}

class TakerRunnable<T> implements Runnable {
	private T value;
	private SynchronousQueue<T> syncQ;

	TakerRunnable(SynchronousQueue<T> syncQ) {
		this.syncQ = syncQ;
	}

	public void run() {
		try {
			print("Retrieve using take");
			value = syncQ.take();
			print("take() returned " + value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public T getValue() {
		return value;
	}

	public static void print(String s) {
		System.out.println(Thread.currentThread().getName() + ":" + s);
	}

}

public class SynchronousQueueDemo {
	public static void main(String[] args) throws InterruptedException {
		SynchronousQueue<String> sq = new SynchronousQueue<String>();
		Thread takerThread1 = new Thread(new TakerRunnable<String>(sq), "TakerThread1");
		takerThread1.start();

		Thread takerThread2 = new Thread(new TakerRunnable<String>(sq), "TakerThread2");
		takerThread2.start();

		Thread putThread1 = new Thread(new PutRunnable<String>(sq, "One"), "PutThread1");
		putThread1.start();

		Thread putThread2 = new Thread(new PutRunnable<String>(sq, "Two"), "PutThread2");
		putThread2.start();
	}
}