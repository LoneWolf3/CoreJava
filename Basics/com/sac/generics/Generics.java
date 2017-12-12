package com.sac.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic required to avoid typcasting
 * 
 * Polymorphism only applied on base type not on parameter type CE below.
 * 
 * ArrayList<Object> obj = new ArrayList<String>();
 * 
 * Primitves not allowed in parametre types
 * 
 * 
 * @author ssachdev
 *
 */
public class Generics {

	/*
	 * 
	 * we can not add any thing in method becuse we do not know the type of ?
	 * 
	 * We can use for extend and super here but not in class
	 * 
	 */
	void m1(ArrayList<?> al) {
		// al.add("sachin");
	}

	/*
	 * Type param in method is declared after return type syntax different from
	 * class type parameter
	 */
	<T> void m2(T obj) {

	}

	/*
	 * Generics algo:
	 * 
	 * Compile with generic. Remove Generics . Compile without generics CE below
	 * 
	 */
	/*
	 * void m3(ArrayList<String> obj) {
	 * 
	 * }
	 * 
	 * void m3(ArrayList<Integer> obj) {
	 * 
	 * }
	 */

	public void checkAnimals(List<? extends Animal> animals) {
		for (Animal a : animals) {
			a.checkup();
		}
	}

	public void checkAnimals(Animal[] animals) {
		animals[1] = new Cat();
		for (Animal a : animals) {
			a.checkup();
		}
	}

	public static void main(String[] args) {

		// Exercise
		List<? extends Object> list = new ArrayList<Dog>();
		List<? extends Animal> list2 = new ArrayList<Dog>();
		List<? super Dog> bList = new ArrayList<Animal>();

		// cannot use wildcard notation in the object creation.
		// List<?> foo = new ArrayList<? extends Animal>();

		// Integer does not extends dogs
		// List<? extends Dog> cList = new ArrayList<Integer>();

		// Dog is not super type of Animal
		// List<? super Animal> dList = new ArrayList<Dog>();

		// To prove generic is only compile time concept
		ArrayList l = new ArrayList<String>();
		l.add(true);

		/*
		 * The reason you can get away with compiling this for arrays is because there
		 * is a runtime exception (ArrayStoreException) that will prevent you from
		 * putting the wrong type of object into an array.
		 */
		Dog[] dogsArray = new Dog[2];
		dogsArray[0] = new Dog();
		Generics doc = new Generics();
		doc.checkAnimals(dogsArray);

		/*
		 * We can not put dog in animal list as within the method, that parameter is of
		 * type ArrayList<Animal>, and that means you could put any kind of Animal into
		 * it. to correct this we can use ? extends animal it wont allow us to add any
		 * thing inside method. We can use ? super Dog if we want to add it says
		 * compiler, please accept any List with a generic type that is of type Dog, or
		 * a supertype of Dog
		 */

		List<Dog> dogsList = new ArrayList<Dog>();
		dogsList.add(new Dog());
		Generics doc2 = new Generics();
		doc2.checkAnimals(dogsList);

	}

}

/*
 * Bounded type only extends can be used no implements and super.
 * 
 * In case we extends to Interface then we can either have interface directly or
 * its Implementation classes e.g Runnable. Combinations can also be used like T
 * extends Number&Runnable
 * 
 * We can not use Interface first like Ruunable&Number not allowed we can
 * extends more then one class e.g Number and Thread
 */

class Sum<T extends Number & Runnable> {
	T a, b;

	public Sum(T a, T b) {
		this.a = a;
		this.b = b;

	}

	void getSum() {

		System.out.println(a.intValue() + b.intValue());
	}
}

abstract class Animal {
	public abstract void checkup();
}

class Dog extends Animal {
	public void checkup() { // implement Dog-specific code
		System.out.println("Dog checkup");
	}
}

class Cat extends Animal {
	public void checkup() { // implement Cat-specific code
		System.out.println("Cat checkup");
	}
}
