package com.sac.writer;

import java.io.*;

/**
 * The Java Reader (java.io.Reader) and Java Writer class (java.io.Writer) in
 * Java IO work much like the InputStream and OutputStream with the exception
 * that Reader and Writer are character based. They are intended for reading and
 * writing text. The InputStream and OutputStream are byte based
 * 
 * @author ssachdev
 *
 */
public class BufferedWriterExample {
	public static void main(String[] args) throws Exception {
		FileWriter writer = new FileWriter("111.txt");
		BufferedWriter buffer = new BufferedWriter(writer);
		PrintWriter pw =  new PrintWriter(buffer);
		pw.write("A");
		pw.flush();
		pw.close();
		System.out.println("Success");
	}
}