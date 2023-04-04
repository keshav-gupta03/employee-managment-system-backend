package com.example.springreact.service;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.springreact.exception.ResourceNotFoundException;
import com.example.springreact.model.Employee;
import com.example.springreact.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee addEmployee(Employee emp) {
		try {
			Employee employee=employeeRepository.save(emp);
			return employee;
		}
		catch(Exception e) {
			return null;
		}
		
	}
	
	@Override
	public List<Employee> ProcessEmployeeList() {
		List Employee=employeeRepository.findAll();
		return Employee;
	
	}

	@Override
	public Employee ProcessEmployeeById(Long id) {
		Employee emp = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not Exist with id : " + id));
		return emp;
	}

	@Override
	public Employee UpdateEmp(Long id, Employee empDetails) {
		Employee emp = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not Exist with id : " + id));
		emp.setFirstName(empDetails.getFirstName());
		emp.setLastName(empDetails.getLastName());
		emp.setEmailId(empDetails.getEmailId());
		emp.setDesignation(empDetails.getDesignation());
		Employee updatedEmp = employeeRepository.save(emp);
		return updatedEmp;
	}

	@Override
	public void DeleteEmployee(Long id) {
		Employee emp = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not Exist with id : " + id));
		
		employeeRepository.deleteById(id);	
	}

	@Override
	public Employee processEmployeeByEmail(String email) {
		// TODO Auto-generated method stub
		Employee emp=employeeRepository.findByEmailId(email);
		return emp;
	}

	@Override
	public Integer uploadEmployeeProfilePic(Long employeeId, String path) {
		return employeeRepository.updateEmployeeProfilePicById(employeeId, path);
	}

	@Override
	public void test1(int timeout) {
		if(timeout<0) {
			throw new IllegalArgumentException();
		}
		System.out.println("############# timeout : "+timeout);
	}
	
}
