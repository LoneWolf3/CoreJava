package com.sac.map;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import java.lang.*;

/**
 * 
 * EnumMap is specialized Map implementation designed and optimized for using
 * Java Enum as key.
 * 
 * HashMap and EnumMap is the probability of Collision. Since Enum is internally
 * maintained as array and they are stored in their natural order using
 * ordinal(), as shown in following code which is taken from put() method of
 * EnumMap
 * 
 * int index = ((Enum)key).ordinal(); Object oldValue = vals[index]; vals[index]
 * = maskNull(value);
 * 
 * Since EnumMap doesn't call hashCode method on keys, there is no chance of
 * collision.
 * 
 * @author ssachdev
 *
 */
public class JavaEnumMapExample {

	public enum Test {
		ONE, TWO
	}

	public static void main(String[] args) {
		EnumMap<Test, String> map1 = new EnumMap<Test, String>(Test.class);
		map1.put(Test.ONE, "1");
		map1.put(Test.TWO, "2");

		for (Entry<Test, String> entry : map1.entrySet()) {
			System.out.println(entry);
		}

		System.out.println("---------------");

		EnumMap<Test, String> map2 = map1.clone();
		map2.remove(Test.ONE);
		for (Entry<Test, String> entry : map2.entrySet()) {
			System.out.println(entry);
		}
	}
}
