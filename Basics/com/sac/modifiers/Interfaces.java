package com.sac.modifiers;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
n.
 * 
 * @author ssachdev
 *
 */
interface A {
	public abstract Object m1() throws IOException;

}

class C implements A {
 
	@Override
	public String m1() {
		return null;

	}

	abstract class D implements A {

		@Override
		public String m1() throws FileNotFoundException{
			// TODO Auto-generated method stub
			return null;

		}

		public class MainApp {

		}
	}
}