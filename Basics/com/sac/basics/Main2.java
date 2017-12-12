package com.sac.basics;

public class Main2 {

	public static void main(String[] args) {

		String s = "OTDRCFG-1-9-8,OTDRCFG:CUMEVRLGMAX-OTS,0.00,NA,NEND,NA,15-MIN,12-08,15-30,1,,,,NONE,,";
		String record[] = s.split(",", 20);
		System.out.println(record.length);
		System.out.println(record[1].indexOf(':') != -1);
		System.out.println(record[9]);
	}

}
