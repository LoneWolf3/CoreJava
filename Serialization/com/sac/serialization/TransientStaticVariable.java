package com.sac.serialization;

import java.io.Serializable;

/**
 * 
 * when we serialize object set of all objects which are reachable from that
 * object will also be serialize
 */
public class TransientStaticVariable implements Serializable {

	private static final long serialVersionUID = 1L;

	public transient int variableOne;
	public transient int variableTwo = 2;

	public static int variableThree;
	public static int variableFour = 4;

	public transient static int variableFive;
	public transient static int variableSix = 6;

	final int variableSeven;
	final transient  int variableEight = 8;

	public TransientStaticVariable() 
	{
		variableSeven=7;
		System.out.println("Constructorr Called...");
	}

}