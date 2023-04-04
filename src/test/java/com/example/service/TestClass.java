package com.example.service;

public class TestClass {

	public String m1() {
		System.out.println(TestWithStaticMethods.hello());
		return TestWithStaticMethods.hello();
	}
	
	public  static void  m2() {
		System.out.println("static method in TestClass");
	}
}
