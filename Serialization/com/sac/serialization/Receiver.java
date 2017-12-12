package com.sac.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * At compile time final variables are placed
 * 
 * If we change class file then run time exception as serialization version has
 * been changed for new class
 * 
 * However if add our own serialization version then jvm will not generate
 * serialization version and we will not get any exception
 * 
 * @author ssachdev
 *
 */
public class Receiver {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		FileInputStream fi = new FileInputStream("transientstatic.ser");
		ObjectInputStream oi = new ObjectInputStream(fi);
		DogExternal y = (DogExternal) oi.readObject();
		oi.close();

		System.out.println(y.s + "...." + y.i + "...." + y.j);
		/*
		 * System.out.println(y.variableOne); System.out.println(y.variableTwo);
		 * System.out.println(y.variableThree); System.out.println(y.variableFour);
		 * System.out.println(y.variableFive); System.out.println(y.variableSix);
		 * System.out.println(y.variableSeven); System.out.println(y.variableEight);
		 */
	}

}