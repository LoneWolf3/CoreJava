package com.sac.Iterator;


public class OverridingEqulas {

	public static void main(String[] args) {
		Obj one = new Obj(9);
		Obj two = new Obj(8);
		Obj three = new Obj(10);
		if (three.equals(two)) {
			System.out.println("one and two are equal");
		}
		if (one == two) {
			System.out.println("one and two are equal");
		} else {
			System.out.println("one and two are not equal by == ");
		}
	}
}

class Obj {
	private int ObjValue;

	Obj(int val) {
		ObjValue = val;
	}

	public int getMoofValue() {
		return ObjValue;
	}

	@Override
	public String toString() {
		return "value is " + ObjValue;
	}

	@Override
	public boolean equals(Object o) {
		if ((o instanceof Obj) && (((Obj) o).getMoofValue() == this.ObjValue)) {
			return true;
		} else {
			return false;
		}
	}
}
