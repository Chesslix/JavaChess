package com.github.ojanka.javachess.util;

public class ArrayUtils {
	public static char[] concatenateCharArrays(char[]... arrays) {
		int length = 0;
		for (char[] arr : arrays) {
			length+=arr.length;
		}
		
		char[] newArray = new char[length];
		
		int pos = 0;
		for (char[] arr : arrays) {
			for (char c : arr) {
				newArray[pos] = c;
				pos++;
			}
		}
		
		return newArray;
	}
}
