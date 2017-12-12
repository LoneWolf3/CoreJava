package com.sac.Iterator;

public class OverridingHashCode {
	private String color;
	private String stripePattern;
	@Override
	public boolean equals(Object object) {
		boolean result = false;
		if (object == null || object.getClass() != getClass()) {
			result = false;
		} else {
			OverridingHashCode tiger = (OverridingHashCode) object;
			if (this.color == tiger.getColor()
					&& this.stripePattern == tiger.getStripePattern()) {
				result = true;
			}
		}
		return result;
	}

	// just omitted null checks
	@Override
	public int hashCode() {
		int hash = 3;
		return hash;
	}

	public static void main(String args[]) {
		OverridingHashCode bengalTiger1 = new OverridingHashCode("Yellow", "Dense");
		OverridingHashCode bengalTiger2 = new OverridingHashCode("blue", "Dense");
		

		System.out.println("bengalTiger1 hashCode: " + bengalTiger1.hashCode());
		System.out.println("bengalTiger2 hashCode: " + bengalTiger2.hashCode());
		
	}

	public String getColor() {
		return color;
	}

	public String getStripePattern() {
		return stripePattern;
	}

	public OverridingHashCode(String color, String stripePattern ) {
		this.color = color;
		this.stripePattern = stripePattern;

	}
}