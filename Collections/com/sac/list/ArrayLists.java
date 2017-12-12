package com.sac.list;

import java.util.ArrayList;

public class ArrayLists {

	/**
	 * working creates a array of 10 and fill it until it reaches 75 percent of its
	 * Initial capacity once it is reached then creates a another array and copy all
	 * its content into it. * 
	 * Set vs add.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		// Collection (I) Iterable
		// AbstractCollection (I)Collection
		// AbstractList (E) AbstractCollection I List
		// ArrayList (E) AbstractList I List
		ArrayList list = new ArrayList();
		

		for (int i = 0; i < 10; i++) {
			list.add(null);
		}
		list.set(1, 555);
		list.set(2, 500);
		list.set(5, 200);
	}
}
