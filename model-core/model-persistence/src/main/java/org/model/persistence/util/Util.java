package org.model.persistence.util;

import java.util.Random;

public class Util {
	
	public String RandomAlphaNumeric_key(Integer casas){
		char[] chars = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random  random = new Random();
		for (int i = 0; i < casas; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String output = sb.toString();				
		return output.toUpperCase();
	}

}
