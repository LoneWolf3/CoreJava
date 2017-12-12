package com.sac.list;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * When we are using any of the modify methods – such as add() or remove() – the
 * whole content of the CopyOnWriteArrayList is copied into the new internal
 * copy.
 * 
 * Due to this simple fact, we can iterate over the list in a safe way, even
 * when concurrent modification is happening.
 * 
 * When we’re calling the iterator() method on the CopyOnWriteArrayList, we get
 * back an Iterator backed up by the immutable snapshot of the content of the
 * CopyOnWriteArrayList.
 * 
 * Its content is an exact copy of data that is inside an ArrayList from the
 * time when the Iterator was created. Even if in the meantime some other thread
 * adds or removes an element from the list, that modification is making a fresh
 * copy of the data that will be used in any further data lookup from that list.
 * 
 * The characteristics of this data structure make it particularly useful in
 * cases when we are iterating over it more often than we are modifying it. If
 * adding elements is a common operation in our scenario, then
 * CopyOnWriteArrayList won’t be a good choice – because the additional copies
 * will definitely lead to sub-par performance.
 * 
 * @author ssachdev
 *
 */
public class CopyOnWriteAryList {

	public static void main(String args[]) {

		CopyOnWriteArrayList<String> threadSafeList = new CopyOnWriteArrayList<String>();
		threadSafeList.add("Java");
		threadSafeList.add("J2EE");
		threadSafeList.add("Collection");

		// add, remove operator is not supported by CopyOnWriteArrayList iterator
		Iterator<String> failSafeIterator = threadSafeList.iterator();
		while (failSafeIterator.hasNext()) {
			System.out.printf("Read from CopyOnWriteArrayList : %s %n", failSafeIterator.next());
			failSafeIterator.remove(); // not supported in CopyOnWriteArrayList in Java
		}

	}

}
