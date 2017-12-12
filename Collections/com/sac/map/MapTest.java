package com.sac.map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;


public class MapTest {

	/**
	 * What is Initial Capacity of HashMap? HashMap capacity is number of
	 * buckets in hashmap at given time. Initial capacity is the capacity of
	 * HashMap when it is created.
	 * 
	 * What is Load Factor? Load Factor is a measure which defines how much
	 * HashMap should be full before the capacity of HashMap is automatically
	 * increased. Rehashing in Java HashMap and Race condition
	 * 
	 * http://www.javacodegeeks.com/2014/03/how-hashmap-works-in-java.html
	 * 
	 * Clone is only shallow copy
	 * 
	 * Use array as key in hash map Sort keys in hash map.<use either treemap or
	 * get keys and use collection.sort>
	 * 
	 * 
	 *
	 * what-happens-when-a-duplicate-key-is-put-into-a-hashmap race conditon in
	 * hash map ConcurrentHashMap,EnumMap,LinkedHashMap,TreeMap,WeakHashMap
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * shallow vs deepcopy HashMap hash = new HashMap(); HashMap hash2 =
		 * null; SetInt obj = new SetInt(); hash.put("one", obj);
		 * 
		 * //hash2 = (HashMap) hash.clone(); hash2 = (HashMap) deepCopy(hash);
		 * obj.setI(2);
		 * 
		 * System.out.println(((SetInt) hash.get("one")).getI());
		 * System.out.println(((SetInt) hash2.get("one")).getI());
		 * 
		 * }
		 * 
		 * public static Map<String, SetInt> deepCopy(Map<String, SetInt>
		 * original) {
		 * 
		 * Map copy = new HashMap(); for (Entry<String, SetInt> entry :
		 * original.entrySet()) { SetInt obj = new SetInt();
		 * 
		 * copy.put(entry.getKey(), obj); } return copy; }
		 */

		// using array as key in hash map.
		/*
		 * Map m = new HashMap(); int a[] = { 1, 2, 3 }; int b[] = { 1, 2, 3 };
		 * System.out.println(a.hashCode()); System.out.println(b.hashCode());
		 * System.out.println(Arrays.hashCode(a));
		 * System.out.println(Arrays.hashCode(b)); m.put(Arrays.hashCode(a),
		 * "1,2,3"); m.put(b, "1,2,34"); System.out.println(m.size());
		 */

		/*
		 * //Duplicate keys test. Map mymap = new HashMap();
		 * mymap.put("1","one"); mymap.put("1","not one");
		 * mymap.put("1","surely not one"); //the following line is case 2 for
		 * duplicate //mymap.put("1","one"); System.out.println(mymap.get("1"));
		 */

		// multithreading in maps.
		final ConcurrentHashMap m = new ConcurrentHashMap<>(2);
		
	
	
	}

}

class SetInt {
	int i = 10;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}
}
