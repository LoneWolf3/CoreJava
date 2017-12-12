package com.sac.Thread.cookbook;

import java.util.concurrent.locks.ReentrantReadWriteLock;

class PricesInfo {

	private double price1;
	private double price2;

	private ReentrantReadWriteLock lock;

	public PricesInfo() {
		price1 = 1.0;
		price2 = 2.0;
		lock = new ReentrantReadWriteLock();
	}

	public double getPrice1() {
		lock.readLock().lock();
		double value = price1;
		System.out.println(Thread.currentThread().getName() + " Inside Read getPrice()-1");
		System.out.println(Thread.currentThread().getName() + " Is about to sleep for 5000ms");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " Is about LEAVE***");
		lock.readLock().unlock();
		return value;
	}

	public double getPrice2() {
		lock.readLock().lock();
		double value = price2;
		System.out.println(Thread.currentThread().getName() + " Inside Read getPrice()-2");
		System.out.println(Thread.currentThread().getName() + " Is about to sleep for 5000ms");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "Is about LEAVE***");

		lock.readLock().unlock();
		return value;
	}

	public void setPrices(double price1, double price2) {
		System.out.println(Thread.currentThread().getName() + " Outside Write waiting.");
		lock.writeLock().lock();
		System.out.println(Thread.currentThread().getName() + " Inside Write SetPrice()");
		this.price1 = price1;
		this.price2 = price2;
		System.out.println(Thread.currentThread().getName() + " Inside about to leave SetPrice()***");
		lock.writeLock().unlock();
	}
}

class Reader implements Runnable {

	private PricesInfo pricesInfo;

	public Reader(PricesInfo pricesInfo) {
		this.pricesInfo = pricesInfo;
	}

	@Override
	public void run() {
		
			System.out.printf("%s: Price 1: %f\n", Thread.currentThread().getName(),
					pricesInfo.getPrice1());
			System.out.printf("%s: Price 2: %f\n", Thread.currentThread().getName(),
					pricesInfo.getPrice2());
		
	}
}

class Writer implements Runnable {

	private PricesInfo pricesInfo;

	public Writer(PricesInfo pricesInfo) {
		this.pricesInfo = pricesInfo;
	}

	@Override
	public void run() {
		
			System.out.printf("Writer: Attempt to modify the  prices.\n");
			pricesInfo.setPrices(99999.0000,88888.00000);
			System.out.printf("Writer: Prices have been modified.\n");
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
	}
}

public class ReadWriteLock {
	public static void main(String[] args) {

		PricesInfo pricesInfo = new PricesInfo();

		Reader readers[] = new Reader[3];
		Thread threadsReader[] = new Thread[3];
		for (int i = 0; i < 3; i++) {
			readers[i] = new Reader(pricesInfo);
			threadsReader[i] = new Thread(readers[i]);
		}
		for (int i = 0; i < 3; i++) {
			threadsReader[i].start();
		}
		Writer writer = new Writer(pricesInfo);
		Thread threadWriter = new Thread(writer);
		threadWriter.start();
	}
}