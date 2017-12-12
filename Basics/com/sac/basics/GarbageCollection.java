package com.sac.basics;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Now look more closely at the first line of foo(), which allocates a new
 * Integer object. B1ehind the scenes, the JVM first attempts to find enough
 * heap space for this object — approximately 12 bytes on a 32-bit JVM. If able
 * to allocate the space, it then calls the constructor, which parses the passed
 * string and initializes the newly-allocated object. Finally, the JVM stores a
 * pointer to that object in the variable baz.
 * 
 * That's the “happy path.” There are several not-so-happy paths, and the one
 * that we care about is when the new operator can't find those 12 bytes for the
 * object. In that case, before giving up and throwing an OutOfMemoryError, it
 * invokes the garbage collector in an attempt to make room.
 * 
 * 
 * first three phase of the object is "strongly reachable,,"
 * 
 * softly reachable The object is the referent of a SoftReference, and there are
 * no strong references to it. The garbage collector will attempt to preserve
 * the object as long as possible, but will collect it before throwing an
 * OutOfMemoryError.
 * 
 * weakly reachable The object is the referent of a WeakReference, and there are
 * no strong or soft references to it. The garbage collector is free to collect
 * the object at any time, with no attempt to preserve it. In practice, the
 * object will be collected during a major collection, but may survive a minor
 * collection.
 * 
 * phantom reachable The object is the referent of a PhantomReference, and it
 * has already been selected for collection and its finalizer (if any) has run.
 * The term “reachable” is really a misnomer in this case, as there's no way for
 * you to access the actual object.Phantom references allow the application to
 * learn when an object is no longer used, so that the application can clean up
 * the object's non-memory resources. Unlike finalizers, however, the object
 * itself has already been collected by the time the application learns this.
 * Implementing a Connection Pool with Phantom References
 * 
 * As seen once the object was ready to be released it was added to the
 * reference queue. So the reference queue is like a callback to our java
 * program, telling us that a particular object is released from its reference
 * and is not available to our code anymore.
 * 
 * Life cycle of object created->Initialized->Inuse->unreachable->finalize
 * 
 * @author ssachdev
 *
 */
public class GarbageCollection {
	public static void main(String[] args) throws InterruptedException {
		/*
		 * A1 strong = new A1("a"); // a strong object
		 * 
		 * ReferenceQueue<A1> que = new ReferenceQueue<A1>();// the ReferenceQueue
		 * WeakReference<A1> savePointWRefernce = new WeakReference<A1>(strong, que);
		 * 
		 * System.out.println("A1 created as a weak ref " + savePointWRefernce);
		 * System.gc(); System.out.println("Any weak references in Q ? " + (que.poll()
		 * != null));
		 * 
		 * System.out.println("Removing Soft Reference"); strong = null; // the only
		 * strong reference has been removed. The heap // object is now only weakly
		 * reachable
		 * 
		 * System.out.println("Now to call gc...Again"); System.gc(); // the object will
		 * be cleared here - finalize will be called.
		 * 
		 * System.out.println("Any  references in Q ? " + (que.remove() != null));
		 * System.out .println("Does the weak reference still hold the heap object ? " +
		 * (savePointWRefernce.get() != null));
		 * System.out.println("Is the weak reference added to the ReferenceQ  ? " +
		 * ((savePointWRefernce.isEnqueued())));
		 */

		// Ask the user how many Widget objects they want to create
		// Remember each Widget takes a little over 1MB

		int count = 500;

		Map<Integer, WeakReference<Widget>> weakWidgets = new HashMap<Integer, WeakReference<Widget>>();

		System.out.println("Creating " + count + " widgets as weak references.");

		for (int i = 0; i < count; i++) {
			weakWidgets.put(i, new WeakReference<Widget>(new Widget(i)));
		}

		// Here's how we access items from a WeakReference
		for (int i = 0; i < count; i++) {
			WeakReference<Widget> weakRef = weakWidgets.get(i);
			Widget ww = weakRef.get();
			// Find out if the WeakWidget is still there or has it been GC'd
			if (ww == null) {
				System.out.println("Oops WeakWidget " + i + " was garbage collected.");
			} else {
				System.out.println("Awesome WeakWidget " + i + " is still around. Let's use it");
			}
		}

	}

	public static void foo(String bar) {
		Integer baz = new Integer(bar);
	}
}

class Widget {

	private byte buff[];
	private int id;

	public Widget(int id) {
		// Each Widget object takes approximately 1MB
		this.buff = new byte[1024 * 1000];
		this.id = id;
	}
}
