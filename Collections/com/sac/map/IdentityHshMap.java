package com.sac.map;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * dentityHashMap as name suggests uses the equality operator(==) for comparing
 * the keys. So when you put any Key Value pair in it the Key Object is compared
 * using == operator.
 * 
 * k1.equals(k2) instead of equality operator.
 * 
 * When you run the above code the result will be
 * 
 * Identity Map KeySet Size :: 2Hash Map KeySet Size :: 1 The Keysize of
 * Identity Map is 2 because here a and new String(“a”) are considered two
 * different Object. The comparison is done using == operator.
 * 
 * For HashMap the keySize is 1 because K1.equals(K2) returns true for all three
 * Keys and hence it keep on removing the old value and updating it with the new
 * one.
 * 
 * These both Maps will behave in same manner if they are used for Keys which
 * are user defined Object and doesn’t overrides equals method.
 * 
 * A typical use of this class is topology-preserving object graph
 * transformations, such as serialization or deep-copying. To perform such a
 * transformation, a program must maintain a "node table" that keeps track of
 * all the object references that have already been processed. The node table
 * must not equate distinct objects even if they happen to be equal. Another
 * typical use of this class is to maintain proxy objects. For example, a
 * debugging facility might wish to maintain a proxy object for each object in
 * the program being debugged.
 * 
 * @author ssachdev
 *
 */
public class IdentityHshMap {
	public static void main(String[] args) {

		// Created HashMap and IdentityHashMap objects

		Map<String, String> hashmapObject = new HashMap<String, String>();
		Map<String, String> identityObject = new IdentityHashMap<String, String>();

		// Putting keys and values in HashMap and IdentityHashMap Object

		hashmapObject.put(new String("key"), "Google");
		hashmapObject.put(new String("key"), "Facebook");

		identityObject.put(new String("identityKey"), "Google");
		identityObject.put(new String("identityKey"), "Facebook");

		// Print HashMap and IdentityHashMap Size : After adding keys

		System.out.println("HashMap after adding key :" + hashmapObject);
		System.out.println("IdentityHashMap after adding key :" + identityObject);

	}
}
