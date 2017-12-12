package com.sac.outputstreams;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileOutputStreamExample {
	private static final String OUTPUT_FILE = "C:\\Users\\ssachdev\\Desktop\\TestFiles\\testFile.txt";

	public static void main(String[] args) throws IOException {
		String content = "Hello Java Code Geeks";

		byte[] bytes = content.getBytes();
		OutputStream out = null;
		try {
			out = new FileOutputStream(OUTPUT_FILE);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// write a byte sequence
		out.write(bytes);

		// write a single byte
		out.write(bytes[0]);

		// write sub sequence of the byte array
		out.write(bytes, 4, 10);

		// flush the outputstream
		out.flush();

	}
}
