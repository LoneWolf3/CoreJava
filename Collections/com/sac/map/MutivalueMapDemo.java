package com.sac.map;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MutivalueMapDemo {
	public static void main(String[] args) {
		MultiMap<String, String> mv = new MultiMap<String, String>();

		mv.put("a", "1");
		mv.put("a", "2");
		System.out.println(mv);
	}

}

class MultiMap<K, V> extends HashMap<K, V> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<K, Collection<V>> map = new HashMap<>();

	@Override
	public V put(K key, V value) {
		if (map.get(key) == null)
			map.put(key, new ArrayList<V>());

		map.get(key).add(value);
		return value;

	}

	@Override
	public String toString() {
		for (K s : map.keySet()) {
			System.out.println(map.get(s));
		}
		return null;
	}
}