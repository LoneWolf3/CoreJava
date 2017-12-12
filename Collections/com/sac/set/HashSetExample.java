package com.sac.set;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 1)Constructs a new Map, in hash set constructor
 * 
 * 2)The backing HashMap instance has default initial capacity (16) and load
 * factor (0.75).
 * 
 * 3)Add method internally put the hash set element as key of hash map and we
 * know that each key of hash map is unique
 * 
 * @author ssachdev
 * 
 */
public class HashSetExample {
	public static void main(String args[]) {
		ArrayList l = new ArrayList();
		l.add("1");
		l.add("2");
		l.add("3");
		l.add("4");
		ArrayList l1 = new ArrayList();
		l1.add("1");
		l1.add("2");
		l1.add("3");
			// create hash set
		HashSet newset = new HashSet();

		// populate hash set
		newset.add("Learning");
		newset.add("Easy");
		newset.add("Simply");
		/**Meyhods**/
		newset.add(-1);
		newset.addAll(l);
		newset.removeAll(l);
		newset.clone();
		boolean b = newset.containsAll(l1);
		Object [] a =newset.toArray();
		 System.out.println(b);
		System.out.println(newset.size());
		System.out.println(l);
		// create an iterator
		Iterator iterator = newset.iterator();

		// check values
		while (iterator.hasNext()) {
			String s =  (String) iterator.next();
		}
	}
}