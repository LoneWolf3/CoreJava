package com.sac.queue;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementing custom BlockingQueue interface . This BlockingQueue
 * implementation follows FIFO (first-in-first-out). New elements are inserted
 * at the tail of the queue, and removal elements is done at the head of the
 * queue.
 *
 * 
 */
interface BlockingQue<E> {

	/**
	 * Inserts the specified element into this queue only if space is available else
	 * waits for space to become available.
	 */
	void put(E item) throws InterruptedException;

	/**
	 * Retrieves and removes the head of this queue only if elements are available
	 * else waits for element to become available.
	 */
	E take() throws InterruptedException;
}


class LinkedBlockingQueueCustom<E> implements BlockingQue<E> {

	private List<E> queue;
	private int maxSize; // maximum number of elements queue can hold at a time.

	public LinkedBlockingQueueCustom(int maxSize) {
		this.maxSize = maxSize;
		queue = new LinkedList<E>();
	}

	/**
	 * Inserts the specified element into this queue only if space is available else
	 * waits for space to become available. After inserting element it notifies all
	 * waiting threads.
	 */
	public synchronized void put(E item) throws InterruptedException {

		// check space is available or not.
		if (queue.size() == maxSize) {
			this.wait();
		}

		// space is available, insert element and notify all waiting threads.
		queue.add(item);
		this.notifyAll();
	}

	/**
	 * Retrieves and removes the head of this queue only if elements are available
	 * else waits for element to become available. After removing element it
	 * notifies all waiting threads.
	 */
	public synchronized E take() throws InterruptedException {

		// waits element is available or not.
		if (queue.size() == 0) {
			this.wait();
		}

		// element is available, remove element and notify all waiting threads.
		this.notifyAll();
		return queue.remove(0);

	}

}

/**
 * Main class
 */
public class ArrayBlokingQueCustom {
	public static void main(String[] args) throws InterruptedException {
		BlockingQue<Integer> b = new LinkedBlockingQueueCustom<Integer>(10);
		System.out.println("put(11)");
		b.put(11);
		System.out.println("put(12)");
		b.put(12);
		System.out.println("take() > " + b.take());
		System.out.println("take() > " + b.take());

	}
}

