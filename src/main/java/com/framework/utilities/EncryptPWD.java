package com.framework.utilities;

import org.apache.commons.codec.binary.Base64;

public class EncryptPWD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		encodeBytes("Rogers");
	}

	public static String encodeBytes(String input) {
		byte[] encodedBytes = Base64.encodeBase64(input.getBytes());
		String encodedString = new String(encodedBytes);
		System.out.println(encodedString);
		return encodedString;
	}
	public static String decodeBytes(String encodedBytes) {
		byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
		String decodedString = new String(decodedBytes);
		System.out.println(decodedString);
		return new String(decodedString);
	}
}
