package com.sac.outputstreams;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * A byte is 8 bits (binary data).
 * 
 * A byte array is an array of bytes (tautology FTW!).
 * 
 * You could use a byte array to store a collection of binary data, for example,
 * the contents of a file. The downside to this is that the entire file contents
 * must be loaded into memory.
 * 
 * In it data is written into in memory byte[] that can be furhter used to write
 * on multiple files
 *
 */
public class ByteArrayOutputstreamEx {
	public static void main(String[] args) throws IOException {
		FileOutputStream f1 = new FileOutputStream("f1.txt");
		FileOutputStream f2 = new FileOutputStream("f2.txt");
		ByteArrayOutputStream bout = new ByteArrayOutputStream(1);
		while (true) {
		byte[] b = "a".getBytes();
		bout.write(b);
		}
		/*bout.writeTo(f1);
		bout.writeTo(f2);*/
	}
}
