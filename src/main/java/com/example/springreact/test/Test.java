package com.example.springreact.test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Random;

import com.example.springreact.service.Calculator;


public class Test {
	String keshav= new String(null, Charset.defaultCharset());
	 
	public static void main(String[] args) throws UnsupportedEncodingException {
		String s=null;
		if(s==null) {
			System.out.println("s is null");
		} 
		else if(s.isEmpty()) {
			System.out.println("s is empty");
		}
	}
	synchronized public void m1() {
		String a=genrateOTP();
		System.out.print(a);
	}
	public static synchronized  void setSnoop() {
		
	}
	
	public String genrateOTP() {
		Random r = new Random();
		int a=r.nextInt();
		return String.valueOf(a);
	}
}
