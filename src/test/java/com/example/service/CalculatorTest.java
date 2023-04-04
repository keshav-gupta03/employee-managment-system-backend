package com.example.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.example.springreact.service.Calculator;

@RunWith(PowerMockRunner.class)
public class CalculatorTest {
	
	Calculator c = new Calculator();
	@Test
	public void test_add() {
		
		int sum = c.add(1,1);
		System.out.println("Sum : " + sum);
		assertEquals(2,sum);
	}
	
	
	

	@Test
	public void test_hello() {
		assertEquals("Hello World",Calculator.helloWorld());
		
	}
}
