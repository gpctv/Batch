package com.stan.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class TestMD5 {
	private MessageDigest md5;
	private byte[] digest;
	final private StringBuffer buffer;
	public TestMD5() throws NoSuchAlgorithmException{
		buffer=new StringBuffer();
		this.md5=MessageDigest.getInstance("MD5");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String password;
		Scanner scanner = new Scanner(System.in);
		System.out.println("key password:");
		password=scanner.nextLine();
		System.out.println("you key the password:"+password);
		
		try {
			TestMD5 md5=new TestMD5();
			String result=md5.encrytor(password);
			System.out.println(result);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 加密
	 * @param password
	 * @return
	 */
	public String encrytor(String password) {
		// TODO Auto-generated method stub
		md5.update(password.getBytes());
		setDigest();
		setBuffer();
		
		return buffer.toString();
	}
	/**
	 * 設定digest
	 */
	private void setDigest(){
		digest= md5.digest();
	}
	/**
	 * 設定密碼字串buffer
	 */
    private void setBuffer(){
    	for(int i=0;i<digest.length;i++){
    		 byte b = digest[i];
		      int value = (b & 0x7F) + (b < 0 ? 128 : 0);
		    buffer.append(value < 16 ? "0" : "");
		    buffer.append(Integer.toHexString(value));
    	}
    }
	

}
