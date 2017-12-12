package com.sac.Iterator;

/**
 * This class shows what if we do not override hash code
 * 
 * @author ssachdev
 * 
 */
public class OverridingToString {

	public static void main(String[] args) {
		OverridingToString h = new OverridingToString();
		System.out.println(h);
		
	}
	
	@Override
	public String toString() {
		return ("syso internally called toString()");
		}
}
