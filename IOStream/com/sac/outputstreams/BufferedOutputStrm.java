package com.sac.outputstreams;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * With both disk and network streams, the optimal buffer size may also depend
 * on the concrete hardware in the computer. If the hard disk is anyways writing
 * a minimum of 4KB at a time, it's stupid to use less than a 4KB buffer. It is
 * also better to then use a buffer size that is a multiple of 4KB. For
 * instance, using 6KB would be stupid too.
 * 
 * @author ssachdev
 *
 */
public class BufferedOutputStrm {
	public static void main(String[] args) throws IOException {
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("textfile.txt"));
		stream.write("Hello, World!".getBytes());
		stream.write(System.lineSeparator().getBytes());
		stream.write("I am writting into a file using BufferedOutputStream".getBytes());
		stream.write(System.lineSeparator().getBytes());
		stream.close();
	}
}
