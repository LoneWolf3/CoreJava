package com.sac.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * In the last chapter Java CountDownLatch Example we saw how a master or main
 * thread waits till the worker threads finish their work. CyclicBarrier class
 * also is a flavour of CountdownLatch with slight change. Lets continue the
 * same example as CountDownLatch. A organization has to recruit 3 Java
 * Developers. And so the HR Manager asks 3 Tech Leads to interview the
 * candidates. In CountDownLatch example the HR Manager wanted to distribute the
 * offer letter to all the 3 candidates that is the reason we made him to wait.
 * Here the HR manager wants the Tech Leads to give the offer letter once they
 * have selected the candidate. But the Tech Leads decide among themselves that
 * they will give the offer letter to their respective candidate only when all
 * interviews are done.
 * 
 * @author ssachdev
 *
 */
public class HRManager {

	public static void main(String args[]) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

		TechLead techLead1 = new TechLead(cyclicBarrier, "John TL");
		TechLead techLead2 = new TechLead(cyclicBarrier, "Doe TL");
		TechLead techLead3 = new TechLead(cyclicBarrier, "Mark TL");

		techLead1.start();
		techLead2.start();
		techLead3.start();

		System.out.println("No work for HR manager");

	}

}