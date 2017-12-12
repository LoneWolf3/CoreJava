package com.sac.jvm;

import java.lang.reflect.Method;

/**
 * javac command produces byte code.
 * 
 * Byte code is used and loaded into JVM .
 * 
 * JVM has interpreter that converts that byte code into underlying machine
 * language.
 * 
 * Since different machine have different way to interpret byte code hence jvm
 * is machine dependent.
 * 
 * JVM is responsible to load and run java class files.
 * =====================================
 * 
 * Parts of JVM
 * 
 * 1)Class loader subsystem
 * 
 * Loading
 * 
 * -Bootstrap class loader (Load all classes presenet in bootstrap path - e.g.
 * jdk/jre/lib rt.jar)
 * 
 * -extension class loader child of Bootstrap(Load all classes presenet in
 * extension path - e.g. jdk/jre/lib/ext )
 * 
 * -application class loader child of extension internally uses environmental
 * variable cp
 * 
 * JVM will ask application class loader to load which delegates request to
 * extension which delegates to bootstrap loader.
 * 
 * Bootstrap will search in bootstrap path if not found then gives to extension
 * loader which search in extension path if not found then application class
 * loader if not found then class not found error
 * 
 * Linkin
 * 
 * - Verify the byte code if not verify error subclass of linking error.
 * 
 * -prepare JVM initialize class level variable and assign default.
 * 
 * -resolution-symbol ref replaced with orignal ref. already in method area.
 * 
 * Initialization
 * 
 * - Original values will be assigned to static variables and static block is
 * executed.
 * 
 * 
 * What are benefits of customized class loader.
 * 
 * 2)JVM memory (MA - HA(YG-TG-PG) - Stack - PC register - Native method stack)
 * 
 * 3)Execution engine will read from JVM memory and use JNI to get native
 * libraries and whith the help of inbuild interprtor converts the byte code to machine specific code.
 * 
 * Loading - reading student.class file and store data in MA (data = FQname
 * class, FQname parentclass,method-constrct,modifier,constant pool Info , all
 * static variable)
 * 
 * 
 * As soon has class file info is store in MA - JVM will create class Class
 * object to represent student.class file in HA and only one class object is
 * created on Heap no matter programer creater how many objects
 *
 * Local variable will be stored in stack area
 * 
 * 
 * =======================Flow======================
 * 
 *
 * @author ssachdev
 *
 */
public class Jvm {

	public static void main(String[] args) throws ClassNotFoundException {
		/*
		 * Class c = Class.forName("com.sac.jvm.Student"); Method m[] =
		 * c.getDeclaredMethods(); for (Method m1 : m) {
		 * System.out.println(m1.getName()); }
		 */

		Student s = new Student();

		Class c = s.getClass();

		System.out.println(s.hashCode());
		System.out.println(c.hashCode());
		System.out.println(c.getClassLoader());

		Runtime r = Runtime.getRuntime();
		System.out.println(r.maxMemory());
		System.out.println(r.freeMemory());

	}
}

class Student {
	int rollNo;

	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	String name;
}