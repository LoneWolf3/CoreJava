package com.sac.map;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * A LinkedHashMap is a combination of hash table and linked list. It has a
 * predictable iteration order (a la linked list), yet the retrieval speed is
 * that of a HashMap. The order of the iteration is determined by the insertion
 * order, so you will get the key/values back in the order that they were added
 * to this Map. You have to be a bit careful here, since re-inserting a key does
 * not change the original order.
 * 
 * 
 * 
 * LinkedHashMap's Entry class adds two extra pointers before and after to form
 * a doubly linked list.
 * 
 * @author ssachdev
 * 
 */
public class LinkedHashMapTest {
	public static void main(String[] args) {

		// Map representing (Company, share price) as (key, value) pair
		LinkedHashMap<String, Double> linkedHashMap = new LinkedHashMap<String, Double>();
		linkedHashMap.put("Apple", new Double(91.98));
		linkedHashMap.put("Sony", new Double(16.76));
		linkedHashMap.put("Dell", new Double(30.47));
		linkedHashMap.put("HP", new Double(33.91));
		linkedHashMap.put("IBM", new Double(181.71));
		// Reverse order.
		ListIterator<Entry<String, Double>> iter = new ArrayList<>(
				linkedHashMap.entrySet()).listIterator(linkedHashMap.size());

		while (iter.hasPrevious()) {
			Entry<String, Double> entry = iter.previous();
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}

		// Displaying the contents of the LinkedHashMap
		System.out.println("Contents of LinkedHashMap : " + linkedHashMap);

		// One of the ways of iterating over the map
		// Notice the order of the elements is same as the order of insertion
		System.out.println("\nValues of map after iterating over it : ");
		for (String key : linkedHashMap.keySet()) {
			System.out.println(key + ":\t" + linkedHashMap.get(key));
		}

		// Getting the value for a particular key
		System.out.println("\nThe current share price of HP is : "
				+ linkedHashMap.get("HP"));

		// Getting the size of the LinkedHashMap
		System.out
				.println("\nThe size of the LinkedHashMap is : " + linkedHashMap.size());

		// Checking whether the LinkedHashMap is empty
		System.out.println("\nIs LinkedHashMap empty? : " + linkedHashMap.isEmpty());

		// Checking whether Map contains a particular key or value
		System.out.println("\nLinkedHashMap contains Sony as key? : "
				+ linkedHashMap.containsKey("Sony"));
		System.out.println("LinkedHashMap contains 999.0 as value? : "
				+ linkedHashMap.containsValue(999.0));

		// Removing a particular value
		System.out.println("\nRemove entry for Dell : " + linkedHashMap.remove("Dell"));
		System.out.println("Content of LinkedHashMap removing Dell: " + linkedHashMap);

		// Clearing the LinkedHashMap
		linkedHashMap.clear();
		System.out.println("\nContent of LinkedHashMap after clearing: " + linkedHashMap);
	}
}
