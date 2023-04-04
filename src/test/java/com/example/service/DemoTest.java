package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


import static org.mockito.BDDMockito.given;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(MockitoJUnitRunner.class)
@PowerMockIgnore({"javax.management.*","javax.crypto.*","javax.net.ssl.*"})
@PrepareForTest(value={TestClass.class,TestWithStaticMethods.class})
public class DemoTest {
	
	
	@InjectMocks
	TestClass test;
	
	
	@Mock
	TestWithStaticMethods obj;
	
	
	TestWithStaticMethods obj1;
	
	@Before
	public  void Before() {
		  PowerMockito.mockStatic(TestWithStaticMethods.class);
	}
	
	@Test
	public void sum() {
		given(obj.hello()).willReturn("Hello");
		
		assertEquals("Hello", test.m1());
	}
}



