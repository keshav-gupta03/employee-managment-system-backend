package com.example.springreact.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.springreact.model.Employee;

public interface EmployeeService {
	public List<Employee> ProcessEmployeeList();
	public Employee addEmployee(Employee emp);
	public Employee ProcessEmployeeById(Long id);
	public Employee UpdateEmp(Long id,Employee emp);
	public void DeleteEmployee(Long id);
	public Employee processEmployeeByEmail(String email);
	public Integer uploadEmployeeProfilePic(Long employeeId,String path);
	public void test1(int timeout);
}
