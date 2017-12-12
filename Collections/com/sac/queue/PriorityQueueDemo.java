package com.sac.queue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueDemo {
	public static void main(String[] args) {
		StudentComparator studentComparator = new StudentComparator();
		// Adding elements in the queue
		PriorityQueue<Student> priorityQueue = new PriorityQueue<Student>(10, studentComparator);
		priorityQueue.offer(new Student("John Kena", 26));
		priorityQueue.offer(new Student("Dija Pone", 24));
		priorityQueue.offer(new Student("Ninja Panda", 27));
		priorityQueue.offer(new Student("Ninja AK", 18));
		priorityQueue.offer(new Student("Ninja CC", 25));
		priorityQueue.offer(new Student("Ninja F6F8", 25));
		priorityQueue.offer(new Student("Andy Druffens", 18));

		// Removing the Student Objects from the queue
		System.out.println(priorityQueue.poll());
		System.out.println(priorityQueue.poll());
		System.out.println(priorityQueue.poll());
		System.out.println(priorityQueue.poll());
		System.out.println(priorityQueue.poll());
		System.out.println(priorityQueue.poll());
		System.out.println(priorityQueue.poll());
	}
}

class StudentComparator implements Comparator<Student> {
	@Override
	public int compare(Student s1, Student s2) {
		return s2.age - s1.age;
	}
}

class Student {
	String name;
	int age;

	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student name is : " + name + " and age is : " + age;
	}
}