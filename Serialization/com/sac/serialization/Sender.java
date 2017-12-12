package com.sac.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * At compile time final variables are placed
 * 
 * If you want to save total object go with serialization if partial information
 * is needs to be saved then you should go for externalization
 * 
 * Transient variable saves default values.
 * 
 * Public no args constructor is required for externalization
 * 
 * @author ssachdev
 *
 */
public class Sender {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TransientStaticVariable obj = new TransientStaticVariable();

		// DogSerial obj = new DogSerial();
		DogExternal obj = new DogExternal("sachin", 10,20);

		FileOutputStream fileOutputStream = new FileOutputStream("transientstatic.ser");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(obj);
		objectOutputStream.flush();
		objectOutputStream.close();

	}

}