package com.exec;

public class TestHexTo {
	 public static void main(String args[] ) {
		 TestHexTo hexto=new TestHexTo();
		 String uid="DDBBCCAA";
		 System.out.println(uid.substring(0,2));
		 String first=uid.substring(0,2);
		 System.out.println(uid.substring(2,4));
		 String sec=uid.substring(2,4);
		 System.out.println(uid.substring(4,6));
		 String thr=uid.substring(4,6);
		 System.out.println(uid.substring(6,8));
		 String four=uid.substring(6,8);
		 System.out.println(hexto.xorHex(hexto.xorHex(hexto.xorHex(first,sec),thr),four));
			 
		}
	public String xorHex(String a, String b) {
	    // TODO: Validation
	    char[] chars = new char[a.length()];
	    for (int i = 0; i < chars.length; i++) {
	        chars[i] = toHex(fromHex(a.charAt(i)) ^ fromHex(b.charAt(i)));
	    }
	    return new String(chars);
	}

	private static int fromHex(char c) {
	    if (c >= '0' && c <= '9') {
	        return c - '0';
	    }
	    if (c >= 'A' && c <= 'F') {
	        return c - 'A' + 10;
	    }
	    if (c >= 'a' && c <= 'f') {
	        return c - 'a' + 10;
	    }
	    throw new IllegalArgumentException();
	}

	private char toHex(int nybble) {
	    if (nybble < 0 || nybble > 15) {
	        throw new IllegalArgumentException();
	    }
	    return "0123456789ABCDEF".charAt(nybble);
	} 
	
}
