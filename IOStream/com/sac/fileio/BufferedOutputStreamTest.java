package com.sac.fileio;


import java.io.BufferedOutputStream;

public class BufferedOutputStreamTest {
	public static void main(String args[]) throws Exception {

		BufferedOutputStream bout = new BufferedOutputStream(System.out);
		String s = "Welcome to javaTpoint.";
		byte b[] = s.getBytes();
		bout.write(b);
		bout.close();
	}
}
