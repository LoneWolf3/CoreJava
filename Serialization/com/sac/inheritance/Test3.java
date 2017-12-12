package com.sac.inheritance;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * If the superclass is serializable but we don’t want the subclass to be
 * serialized
 * 
 * @author ssachdev
 *
 */
class A3 implements Serializable {
	int i;

	// parameterized constructor
	public A3(int i) {
		this.i = i;
	}

}

// subclass B
// B class doesn't implement Serializable
// interface.
class B3 extends A {
	int j;

	// parameterized constructor
	public B3(int i, int j) {
		super(i);
		this.j = j;
	}

	// By implementing writeObject method,
	// we can prevent
	// subclass from serialization
	private void writeObject(ObjectOutputStream out) throws IOException {
		throw new NotSerializableException();
	}

	// By implementing readObject method,
	// we can prevent
	// subclass from de-serialization
	private void readObject(ObjectInputStream in) throws IOException {
		throw new NotSerializableException();
	}

}

// Driver class
public class Test3 {
	public static void main(String[] args) throws Exception {
		B b1 = new B(10, 20);

		System.out.println("i = " + b1.i);
		System.out.println("j = " + b1.j);

		// Serializing B's(subclass) object

		// Saving of object in a file
		FileOutputStream fos = new FileOutputStream("abc.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		// Method for serialization of B's class object
		oos.writeObject(b1);

		// closing streams
		oos.close();
		fos.close();

		System.out.println("Object has been serialized");

		// De-Serializing B's(subclass) object

		// Reading the object from a file
		FileInputStream fis = new FileInputStream("abc.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);

		// Method for de-serialization of B's class object
		B b2 = (B) ois.readObject();

		// closing streams
		ois.close();
		fis.close();

		System.out.println("Object has been deserialized");

		System.out.println("i = " + b2.i);
		System.out.println("j = " + b2.j);
	}
}