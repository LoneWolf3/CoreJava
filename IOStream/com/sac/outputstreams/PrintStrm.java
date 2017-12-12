package com.sac.outputstreams;

/**
 * In early versions of the JDK (1.0.x), when you wanted to write characters,
 * you could do one of two things, write bytes to an output stream (which are
 * assumed to be in the system default character set):
 * 
 * outputStream.write("foobar".getBytes()); or wrap another outputstream in a
 * PrintStream
 * 
 * PrintStream printStream = new PrintStream(outputStream);
 * printStream.write("foobar");
 * 
 * 
 * See the difference? PrintStream is handling the character conversion to
 * bytes, as well as encoding (the constructor call above uses the system
 * default encoding, but you could pass it as a parameter). It also provides
 * convenience methods for writing double, boolean, etc....
 * 
 * @author ssachdev
 *
 */
public class PrintStrm {
	public static void main(String[] args) {
		// println is print stream method
		System.out.println();
	}

}
