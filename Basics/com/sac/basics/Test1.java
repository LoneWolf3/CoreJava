package com.sac.basics;

import java.*;
import java.sql.*;
import com.sac.pac1.*;

/**
 * we can compile one source file but we can run java class e.g we have two
 * classes class A and class B and we are compiling Test1 - please check code
 * below
 * 
 * We can run class A but not Test1 and B
 * 
 * Explicit class import and implicit class import
 * 
 * For static method method hiding concept is applicable not overriding.
 * Overloading and inheritance is applicable
 * 
 * strictfp and native go in hand as we don't know native lang follow I734
 * 
 * Adpater class to reduce number of method implementation, example we can
 * implement servlet,genric and httpservlet but we mostly use httpservlt
 * 
 * When we execute child class once one object is created and hash code of child
 * and parent is same.
 * 
 * Super and this keyword can not be used in static
 * 
 * Parent p = new c() - We should go for this approach when we don't know run
 * time object.e.g
 * 
 * 
 * At compile time final variables are placed
 *
 * 
 */

class A {
	public static void main(String[] args) {
		System.out.println("A");
	}

}

class B {
	public static void main(String[] args) {
		System.out.println("B");
	}
}