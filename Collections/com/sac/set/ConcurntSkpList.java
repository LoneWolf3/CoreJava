package com.sac.set;

import java.util.concurrent.ConcurrentSkipListSet;

/**
 * ConcurrentSkipListSet is used in concurrent threading environment.
 * ConcurrentSkipListSet maintains the behavior same as TreeSet. The elements in
 * ConcurrentSkipListSet can be accessed simultaneously and element's integrity
 * will be maintained. ConcurrentSkipListSet does not affect the performance.
 * All access is lock free. And we do not need to worry for synchronization.
 * 
 * 
 * Sets managed by the ConcurrentSkipListSet class can be safely modified by
 * multiple threads at the same time, with no need to use locks. If a set
 * managed by one of the other set classes may be updated by more than one
 * thread, you will need locks to prevent multiple threads from updating the set
 * at the same time. You can use the ConcurrentSkipListSet class to avoid
 * threads having to wait for locks.
 * 
 * I'm assuming the JDK guys went with a skip list here because the
 * implementation was well-known and because making it lock-free was simple and
 * portable (using CAS).
 * 
 * @author ssachdev
 *
 */
public class ConcurntSkpList {
	ConcurrentSkipListSet<String> csls = new ConcurrentSkipListSet<String>();

	class AddThread implements Runnable {
		@Override
		public void run() {
			// adds specified element in the set
			csls.add("A");
			csls.add("B");

			// returns the first element
			String s1 = csls.first();

			System.out.println(s1);
			// returns the last element.
			String s2 = csls.last();

			System.out.println(s2);
		}
	}

	class SubThread implements Runnable {
		@Override
		public void run() {
			// removes the specified element from the set.
			csls.remove("A");
		}
	}

	public static void main(String... args) {
		ConcurntSkpList ob = new ConcurntSkpList();
		new Thread(ob.new AddThread()).start();
		new Thread(ob.new SubThread()).start();
	}
}
