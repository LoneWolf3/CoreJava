package com.sac.set;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class EnumSetExample {

	enum Flag {
		UPPERCASE, REVERSE, FULL_STOP, EMPHASISE,;

		public static final EnumSet<Flag> ALL_OPTS = EnumSet.allOf(Flag.class);
	}

	public static String format(String value, EnumSet<Flag> flags) {
		if (flags.contains(Flag.UPPERCASE))
			value = value.toUpperCase();

		if (flags.contains(Flag.REVERSE))
			value = new StringBuffer(value).reverse().toString();

		if (flags.contains(Flag.FULL_STOP))
			value += ".";

		if (flags.contains(Flag.EMPHASISE))
			value = "~*~ " + value + " ~*~";

		return value;
	}

	public static void main(String[] args) {
		format("Sally", EnumSet.of(Flag.UPPERCASE)); // SALLY

		format("Sally", EnumSet.of(Flag.REVERSE)); // yllaS

		format("Sally", EnumSet.of(Flag.FULL_STOP, Flag.EMPHASISE)); // ~*~
																		// Sally.
																		// ~*~

		format("Sally", Flag.ALL_OPTS); // ~*~ YLLAS. ~*~
	}

}
