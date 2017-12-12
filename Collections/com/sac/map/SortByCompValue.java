package com.sac.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SortByCompValue {
	public static void main(String[] args) {
		Map<Integer, String> lang = new HashMap<Integer, String>();
		lang.put(0, "Java");
		lang.put(1, "Groovy");
		lang.put(2, "Ruby");
		lang.put(3, "Python");
		lang.put(4, "C#");
		lang.put(5, "C++");
		lang.put(6, "Perl");

		List<Integer> keys = new ArrayList<Integer>(lang.keySet());

		// Sort keys by values.
		final Map<Integer, String> langForComp = lang;
		Collections.sort(keys, new Comparator<Object>() {
			public int compare(Object left, Object right) {
				Integer leftKey = (Integer) left;
				Integer rightKey = (Integer) right;

				String leftValue = (String) langForComp.get(leftKey);
				String rightValue = (String) langForComp.get(rightKey);
				return leftValue.compareTo(rightValue);
			}
		});

		// List the key value
		for (Iterator<Integer> i = keys.iterator(); i.hasNext();) {
			Object k = i.next();
			System.out.println(k + " " + lang.get(k));
		}
	}

}
