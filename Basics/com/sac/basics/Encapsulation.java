package com.sac.basics;

/**
 * 
 * Encapsulation is defined as the wrapping up of data under a single unit. It
 * is the mechanism that binds together code and the data it manipulates.
 * 
 * 
 * 
 * Benefits of Encapsulation The fields of a class can be made read-only or
 * write-only.
 * 
 * A class can have total control over what is stored in its fields. Now
 * consider this question: Is the value of right always going to be onethird the
 * value of left? It looks like it will, until you realize that users of the Foo
 * class don’t need to use the setLeft() method! They can simply go straight to
 * the instance variables and change them to any arbitrary int value
 * 
 * @author ssachdev
 *
 */
public class Encapsulation {

}

class Foo {
	public int left = 9;
	public int right = 3;

	public void setLeft(int leftNum) {
		left = leftNum;
		right = leftNum / 3;
	}
	// lots of complex test code here
}
