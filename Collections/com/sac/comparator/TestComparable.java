package com.sac.comparator;

public class TestComparable<E extends Comparable<E>> implements Comparable<TestComparable<E>> {

	int id;
	String name;
	private int age;
	private long salary;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public long getSalary() {
		return salary;
	}

	public TestComparable(int id, String name, int age, int salary) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}

	/**
	 * 
	 * ascending if this.quantity – compareQuantity . compareQuantity –
	 * this.quantity is descending order.
	 */
	@Override
	public int compareTo(TestComparable<E> o) {
		return (this.id - o.id);

	}

}