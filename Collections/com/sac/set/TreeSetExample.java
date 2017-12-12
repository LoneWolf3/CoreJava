package com.sac.set;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Constructs a set backed by tree map which implements naviagable map.
 * 
 * adds a node in tree map . If already node exists dont add else add and return
 * null .
 * 
 * since each node has its own hash code hence no way to add dup values.
 * 
 * A NavigableSet implementation based on a TreeMap. The elements are ordered
 * using their natural ordering, or by a Comparator provided at set creation
 * time, depending on which constructor is used. This implementation provides
 * guaranteed log(n) time cost for the basic operations (add, remove and
 * contains).
 * 
 * Hence , whenever you are adding element to the TreeSet object , it works just
 * like HashSet , The only difference is that instead of HashMap here we have
 * TreeMap object in the constructor.
 * 
 */
public class TreeSetExample {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// creating a TreeSet
		TreeSet treeadd = new TreeSet();

		// adding in the tree set
		treeadd.add(1);
		treeadd.add(1);
		treeadd.add(13);
		treeadd.add(17);
		treeadd.add(2);
		// treeadd.add("sachin");
		System.out.println(treeadd.ceiling(4));
		System.out.println(treeadd.floor(12));
		System.out.println(treeadd.pollFirst());

		TreeSet treeSet2 = new TreeSet();
		treeSet2.add(new Pizza("VEG", 20));
		treeSet2.add(new Pizza("CHICKEN", 40));
		treeSet2.add(new Pizza("CHICKEN With Topping", 60));

		Iterator iterator = treeSet2.iterator();

		while (iterator.hasNext()) {
			System.out.println(iterator.next() + " ");
		}

	}
}

class Pizza implements Comparable<Pizza> {

	protected String type;
	private int price;

	public Pizza(String type, int price) {
		super();
		this.type = type;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Pizza [type=" + type + ", price=" + price + "]";
	}

	@Override
	public int compareTo(Pizza o) {
		return this.price - o.price;
	}
}
