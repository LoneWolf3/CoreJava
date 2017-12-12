package com.sac.inner.classes;

public class Test {

}



class Outer<X> {

	interface Inner<Y> {
	}
}

class User {
	Outer.Inner<String>[] gengens = new Outer.Inner<String>[10];

}