package com.sac.basics;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Object class is present is java.lang; hence without using java.lang u can not
 * write class
 * 
 * 3- Sachin sachdeva will be garbage collected
 * 
 * 16+string length is initial capacity in case we have sb("string") in cons.
 * 
 * Preference order 1)widening 2)autoBoxing 3)vargars
 * 
 * Widening followed by AB not allowed where are AB followed by widening is
 * allowed
 * 
 * parseXxx() returns the named primitive. valueOf() returns a newly created
 * wrapped object of the type that invoked the method.
 * 
 * Will throw NPE if unbox null
 * 
 * @author ssachdev
 *
 */
public class LangPackage extends Object {
	public static void main(String[] args) throws ClassNotFoundException {

		/*
		 * //Example 1 Class c = Class.forName("java.lang.Object"); Method m[] =
		 * c.getDeclaredMethods(); for (Method m1 : m) {
		 * System.out.println(m1.getName()); }
		 */

		// Example 2
		StringBuffer sb = new StringBuffer("sachin");
		StringBuffer sb2 = new StringBuffer("sachin");

		System.out.println(sb.equals(sb2));
		System.out.println(sb == sb2);
		System.out.println(sb);

		// Example 3
		String s = new String("sachin");
		String s2 = new String("sachin");
		s.concat("sachdeva");
		System.out.println(s == s2);
		System.out.println(s.equals(s2));

		ArrayList l = new ArrayList();
		l.add(1);

		int x = 10;
		m1(x);

	}

	static void m1(Long l) {
		System.out.println("long");

	}

	/*
	 * static void m1(Object l) { System.out.println("Object");
	 * 
	 * }
	 * 
	 * static void m1(long l) { System.out.println("Long");
	 * 
	 * }
	 */
}
