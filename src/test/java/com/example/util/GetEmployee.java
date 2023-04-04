package com.example.util;

import com.example.springreact.model.Employee;

public class GetEmployee {
	
	public static Employee createEmployee() {
		Employee emp=Employee.builder().id(1L).firstName("keshav").lastName("gupta").emailId("keshav.gupta@infobeans.com")
				.designation("trainee software engineer").password("12345").profileImage("http:8080//passport.jpg").build();
		return emp;
	}

}
