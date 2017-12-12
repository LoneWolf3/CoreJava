package com.sac.serialization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class DogExternal implements Externalizable {
	int i;
	String s;
	int j;

	public DogExternal() {
		System.out.println("No args constructor");
	}

	public DogExternal(String s, int a,int b) {
		this.i = a;
		this.j = b;
		this.s = s;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(s);
		out.writeInt(i);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		s = (String) in.readObject();
		i = in.readInt();
	}

}
