package com.sac.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Insert add(o) offer(o) put() remove(o) poll(o) take(o) Inspection element(o)
 * peek(o)
 * 
 * A BlockingQueue does not accept null elements. A BlockingQueue without any
 * intrinsic capacity constraints always reports a remaining capacity of
 * Integer.MAX_VALUE.
 * 
 * working : backed by an array . when an element is inserted push index is
 * increased and when element is removed take index is incremented both push and
 * take are intializes to zero
 * 
 * Fairness means when if more then one thread is waiting for consume then acess
 * is allowed to thread which is being waited for long
 * 
 * * most visible difference between ArrayBlockingQueue and LinkedBlockingQueue
 * - the former is always bounded, while the latter can be unbounded
 * 
 * ArrayBlockingQueue pre-allocates its backing array, so it doesn't allocate
 * nodes during its usage, but it immediately takes what can be a considerable
 * chunk of memory, which can be a problem if your memory is fragmented.
 * 
 * offer(E e) Inserts the specified element at the tail of this queue if it is
 * possible to do so immediately without exceeding the queue's capacity,
 * returning true upon success and false if this queue is full.
 * 
 * add(E e) Inserts the specified element at the tail of this queue if it is
 * possible to do so immediately without exceeding the queue's capacity,
 * returning true upon success and throwing an IllegalStateException if this
 * queue is full.
 * 
 * put(E e) Inserts the specified element at the tail of this queue, waiting for
 * space to become available if the queue is full.
 * =============================================================
 * 
 * peek() Retrieves, but does not remove, the head of this queue, or returns
 * null if this queue is empty.
 * 
 * poll() Retrieves and removes the head of this queue, or returns null if this
 * queue is empty.
 * 
 * take() Retrieves and removes the head of this queue, waiting if necessary
 * until an element becomes available.
 * 
 * 
 * 
 * Transfer queue this queue allows us to create programs according to the
 * producer-consumer pattern, and coordinate messages passing from producers to
 * consumers.
 */

class BQProducer extends Thread {
	private final BlockingQueue<String> queue;
	private final String name;

	public BQProducer(BlockingQueue<String> queue, String name) {
		this.queue = queue;
		this.name = name;
	}

	@Override
	public void run() {
		while (true) {
			try {
				String s = "s	";
				queue.add(s);
				System.out.println(name + "  put:" + s);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}

class BQConsumer extends Thread {
	private final BlockingQueue<String> queue;
	private final String name;

	public BQConsumer(BlockingQueue<String> queue, String name) {
		this.queue = queue;
		this.name = name;
	}

	@Override
	public void run() {
		while (true) {
			try {
				String str = this.queue.take();
				System.out.println(name + "  took: " + str);
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}

public class ArrayBlockingQue {
	public static void main(String[] args) throws InterruptedException {
		int capacity = 5;
		boolean fair = true;
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(capacity, fair);
		new BQConsumer(queue, "Consumer1").start();
		new BQProducer(queue, "Producer1").start();
	}
}
