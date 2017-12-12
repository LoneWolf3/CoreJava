package com.sac.basics;

import java.util.*;
import java.sql.*;

/**
 * Using import we need not to use fully qualified name of class.
 * 
 * Two same package will result in ambiguity error if they have same class
 * 
 * Presidence explicit import - > CurrentWorking Direc ->implicit import
 * 
 * If we u * compile time will be more but execution time will be same.
 * 
 * import statements are lazily loaded
 */
public class ImportTest {
	public static void main(String[] args) {
		Date d = new Date();
	}
}

class Date {

}