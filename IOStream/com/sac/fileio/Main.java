package com.sac.fileio;

import java.io.File;
import java.io.IOException;

/**
 * FIle writer is to write text/char data, lowest data writer
 * 
 * File writer by default override else to append provide true in constructor
 * 
 * Write mthod takes int char and string.
 * 
 * We have to flush(gurantee that last char written to file) and close
 * 
 * @author ssachdev
 *
 */
public class Main {

	public static void main(String[] args) {
		/*
		 * Does not create physcial file only create java file object in current working
		 * directory
		 */
		File f = new File("abc.txt");
		try {
			/*
			 * This line creates a new physical file
			 */
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
