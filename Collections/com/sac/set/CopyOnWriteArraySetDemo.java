package com.sac.set;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * CopyOnWriteArraySet is little brother of CopyOnWriteArrayList class.
 * 
 * CopyOnWriteArraySet is best suited as read-only collection whose size is
 * small enough to copy if some mutative operation happens, for example you can
 * use CopyOnWriteArraySet to store object at start-up of application and let
 * multiple application thread access them during application life time. If an
 * new condition or object comes up during that time, it can also be added into
 * this Set, with incurring cost of creating a new array.
 * 
 * One of the most important thing to know about CopyOnWriteArraySet is that it
 * is backed by CopyOnWriteArrayList, which means it also share all basic
 * properties of CopyOnWriteArrayList.
 * 
 * It does not allow remove while iteration
 */
public class CopyOnWriteArraySetDemo {
	public static void main(String args[]) {
		Publisher cricNext = new Publisher();
		SubScriber raj = new SubScriber("RAJ");
		SubScriber adom = new SubScriber("ADOM");
		cricNext.addSubscriber(raj);
		cricNext.addSubscriber(adom);
		cricNext.notifySubs("FOUR");
		cricNext.notifySubs("SIX");
	}
}

class Publisher {
	private CopyOnWriteArraySet setOfSubs = new CopyOnWriteArraySet();

	public void addSubscriber(SubScriber sub) {
		setOfSubs.add(sub);
	}

	public void notifySubs(String score) {
		Iterator itr = setOfSubs.iterator();
		while (itr.hasNext()) {
			SubScriber sub = (SubScriber) itr.next();
			setOfSubs.remove(sub);// // not allowed, throws UnsupportedOperationException

			sub.receive(score);
		}
	}
}

class SubScriber {
	private String _name;

	public SubScriber(String name) {
		this._name = name;
	}

	public void receive(String score) {
		System.out.printf("[%s] Event received : %s %n", _name, score);
	}
}
