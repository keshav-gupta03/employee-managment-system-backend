package com.example.springreact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.springreact.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long > {
	public Employee findByEmailId(String email);
	
	@Modifying
	@Transactional
	@Query(value="update employee set profile_image=:profilePic where id=:employeeId",nativeQuery = true)
	public int updateEmployeeProfilePicById(@Param("employeeId") Long employeeId,@Param("profilePic") String path);
}
