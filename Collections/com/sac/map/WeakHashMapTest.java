package com.sac.map;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Hash table based implementation of the Map interface, with weak keys. An
 * entry in a WeakHashMap will automatically be removed when its key is no
 * longer in ordinary use. More precisely, the presence of a mapping for a given
 * key will not prevent the key from being discarded by the garbage collector,
 * that is, made finalizable, finalized, and then reclaimed. When a key has been
 * discarded its entry is effectively removed from the map, so this class
 * behaves somewhat differently from other Map implementations. Both null values
 * and the null key are supported. This class has performance characteristics
 * similar to those of the HashMap class, and has the same efficiency parameters
 * of initial capacity and load factor.
 * 
 * can't use weak hash map as cache.
 * 
 * 
 * http://stackoverflow.com/questions/11173814/how-does-a-weak-hash-map-know-to-
 * garbage-collect-an-object.
 * 
 * @author ssachdev
 * 
 */

public class WeakHashMapTest {
	public static void main(String[] args) {
		Map<String, String> hashMap = new HashMap<String, String>();

		Map<String, String> weakHashMap = new WeakHashMap<String, String>();

		String keyHashMap = new String("keyHashMap");
		String keyWeakHashMap = new String("keyWeakHashMap");

		hashMap.put(keyHashMap, "Ankita");
		weakHashMap.put(keyWeakHashMap, "Atul");
		System.gc();
		System.out.println("Before: hash map value:" + hashMap.get("keyHashMap")
				+ " and weak hash map value:" + weakHashMap.get("keyWeakHashMap"));

		keyHashMap = null;
		keyWeakHashMap = null;

		System.gc();

		System.out.println("After: hash map value:" + hashMap.get("keyHashMap")
				+ " and weak hash map value:" + weakHashMap.get("keyWeakHashMap"));
	}
}