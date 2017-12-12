package com.sac.fileio;

/**
 * PrintStream was the original bridge to deal with encoding
 * characters and other datatypes. If you look at the javadoc for
 * java.io.OutputStream you'll see methods only for writing two distinct data
 * types: byte and int.
 * 
 * In early versions of the JDK (1.0.x), when you wanted to write characters,
 * you could do one of two things, write bytes to an output stream (which are
 * assumed to be in the system default character set):
 * 
 * outputStream.write("foobar".getBytes()); or wrap another outputstream in a
 * PrintStream
 * 
 * @author ssachdev
 *
 */
public class Printstream {

}
