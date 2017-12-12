package com.sac.basics;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Java is pass by Value - copy of reference variable in passed method .
 * 
 * @author ssachdev
 *
 */
public class ReferenceTest {
	public static void main(String[] args) {

		// primititves
		int firstValue = 10;
		System.out.println("Before process " + firstValue);
		process(firstValue);
		System.out.println("After process " + firstValue);
		// objects
		VehicleProcessor processor = new VehicleProcessor();
		Vehicle vehicle = new Vehicle("A");
		System.out.println(vehicle.hashCode());
		System.out.println("Before calling process() " + vehicle);
		processor.process(vehicle);
		System.out.println("After calling process()" + vehicle);
		processor.processWithReferenceChange(vehicle);
		System.out.println("After calling reference-change method " + vehicle);
		// Atomic Integer
		AtomicInteger ai = new AtomicInteger(10);
		System.out.println("Before process " + ai);
		process(ai);
		System.out.println("After process " + ai);

	}

	public static void process(int value) {
		value = 50;
		System.out.println("Changed value within method (value = " + value + ")");
	}

	public static void process(AtomicInteger value) {
		value.set(50);
		System.out.println("Changed value within method (value = " + value + ")");
	}
}

class Vehicle {
	private String name;

	public Vehicle(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}

class VehicleProcessor {
	public void process(Vehicle vehicle) {
		vehicle.setName("B");
		System.out.println(vehicle.hashCode());
		System.out.println("Leaving method with name = " + vehicle);
	}

	public void processWithReferenceChange(Vehicle vehicle) {
		vehicle = new Vehicle("C");
		System.out.println("Leaving method with name " + vehicle);
	}
}