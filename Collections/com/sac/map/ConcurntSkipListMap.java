package com.sac.map;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * he complexity is even more when you try to make the implementation concurrent
 * (safe for concurrent use). For that reason there is no ConcurrentTreeMap in
 * java.util.concurrent. A concurrent implementation of SkipList is simpler.
 * Hence, for a Map that is ordered and concurrent,the implementators choose
 * SkipList. This interface is a type of SortedMap that provides navigation
 * methods that returns the closest match for given search targets. It has
 * methods like lowerEntry, floorEntry, ceilingEntry, higherEntry which return
 * Map
 * 
 * ConcurrentHashMap is not NavigableMap and also not a SortedMap, But
 * ConcurrentSkipListMap is both a NavigableMap and a SortedMap.
 * ConcurrentSkipListMap is a skip list and CocurrentHashMap is not.
 * 
 * In general, you will use a ConcurrentHashMap, if you must have O(1) for both
 * get and put operations, but do not care about the ordering in the collection.
 * You will use a ConcurrentSkipListMap if you need an ordered collection
 * (sorted), but can tolerate O(logn) performance for get and put.
 * 
 * @author ssachdev
 *
 */
public class ConcurntSkipListMap {
	public static void main(String[] args) {
		ConcurrentSkipListMap<String, String> concurrentSkipListMap = new ConcurrentSkipListMap<String, String>();
		concurrentSkipListMap.put("3", "Apple");
		concurrentSkipListMap.put("2", "Ball");
		concurrentSkipListMap.put("1", "Car");
		concurrentSkipListMap.put("5", "Doll");
		concurrentSkipListMap.put("4", "Elephant");

		System.out.println("ceilingEntry-2: " + concurrentSkipListMap.ceilingEntry("2"));
		NavigableSet<String> navigableSet = concurrentSkipListMap.descendingKeySet();
		System.out.println("descendingKeySet: ");
		Iterator<String> itr = navigableSet.iterator();
		while (itr.hasNext()) {
			String s = (String) itr.next();
			System.out.println(s);
		}
		System.out.println("firstEntry: " + concurrentSkipListMap.firstEntry());
		System.out.println("lastEntry: " + concurrentSkipListMap.lastEntry());
		System.out.println("pollFirstEntry: " + concurrentSkipListMap.pollFirstEntry());
		System.out.println("now firstEntry: " + concurrentSkipListMap.firstEntry());
		System.out.println("pollLastEntry: " + concurrentSkipListMap.pollLastEntry());
		System.out.println("now lastEntry: " + concurrentSkipListMap.lastEntry());
	}

}