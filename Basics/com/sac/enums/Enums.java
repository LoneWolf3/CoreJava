package com.sac.enums;

/**
 * Used for group of named constants, to define our own data type it is abstract
 * class
 * 
 * Can be declared inside or out side the class but not inside the method.
 * 
 * Enum can be applied to switch statements as well.
 * 
 * Since every enum is child class of enum hence you cannot not extend enums
 * with enums
 * 
 * We can have constructor, main method or any other method in enums as well
 * 
 * Enum can not have abstract method
 * 
 * 
 * 
 * @author ssachdev
 *
 */
public class Enums {
	public static void main(String[] args) {
		// values implicit method not method in api,returns array of all values
		// Beer[] b = Beer.values();

		// Two times constructor will be called as on loading two final objects will be
		// created You can not call enum constructor
		Beer b = Beer.K_F;
		System.out.println(b.getCode());

	}
}

enum Beer {
	K_F, RC, CORNA(20);
	int price;
	private String cc;

	Beer() {
		cc = name().replace("_", "-");
		System.out.println(this.name());
		System.out.println("hi");
	}

	Beer(int price) {
		this.price = price;
	}

	String getCode() {
		return cc;
	}

}

/*
 * class Beer{ public static final Beer KF = new Beer(); public static final
 * Beer RC = new Beer();
 * 
 * }
 */