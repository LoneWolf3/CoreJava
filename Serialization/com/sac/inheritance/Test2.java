package com.sac.inheritance;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;;

/**
 * if any instance inherit from non serialziable are then jvm ignore orignal
 * value but save default value in file but on de-serialization if jvm finds
 * parent in not serialized then jvm will execute instance variable .
 */
class Aniaml {
	int i = 10;

	public Aniaml() {
		System.out.println("Animal");
	}
}

class Dog extends Aniaml implements Serializable {

	private static final long serialVersionUID = 1L;
	int j = 20;

	public Dog() {
		System.out.println("Dog");
	}

}

// Driver class
public class Test2 {
	public static void main(String[] args) throws Exception {
	
		System.out.println("Serialization started");
		Dog d = new Dog();	
		d.i = 999;
		d.j = 888;
		FileOutputStream fos = new FileOutputStream("abc.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(d);
		oos.close();
		fos.close();
		System.out.println("Deserializatio Serialization started");

		FileInputStream fis = new FileInputStream("abc.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Dog b2 = (Dog) ois.readObject();
		ois.close();
		fis.close();

		System.out.println("i = " + b2.i);
		System.out.println("j = " + b2.j);
	}
}