package com.sac.threads;

public class SynchronizationExample {
	public static void main(String[] args) {

		Account account = new Account();
		account.setBalance(0);

		Company company = new Company(account);
		Thread companyThread = new Thread(company);

		Bank bank = new Bank(account);
		Thread bankThread = new Thread(bank);

		System.out.printf("Account : Initial Balance: %f\n", account.getBalance());

		companyThread.start();
		bankThread.start();

		try {
			companyThread.join();
			bankThread.join();
			System.out.printf("Account : Final Balance: %f\n", account.getBalance());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class Account {
	private double balance;

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public synchronized void  addAmount(int amount) {
		double tmp = balance;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tmp += amount;
		balance = tmp;
	}

	public synchronized  void subtractAmount(int amount) {
		double tmp = balance;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		tmp -= amount;
		balance = tmp;
	}
}

class Bank implements Runnable {

	private Account account;

	public Bank(Account account) {
		this.account = account;
	}

	@Override
	public void run() {

		account.subtractAmount(10);

	}
}

class Company implements Runnable {

	private Account account;

	public Company(Account account) {
		this.account = account;
	}

	@Override
	public void run() {

		account.addAmount(20);

	}
}