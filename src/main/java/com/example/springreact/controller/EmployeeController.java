package com.example.springreact.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.springreact.dto.FileUploadResponse;
import com.example.springreact.model.Employee;
import com.example.springreact.model.EmployeePDFExporter;
import com.example.springreact.service.EmployeeService;
import com.example.springreact.service.FileUploadService;
import com.lowagie.text.DocumentException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private FileUploadService fileUploadService; 
	
	
	// get all the employee
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List Employee=employeeService.ProcessEmployeeList();
		return ResponseEntity.ok(Employee);
	}

	// Create Employee
	@PostMapping("/employees")
	public ResponseEntity<String> createEmployee(@RequestBody Employee emp) {
		 Employee employee=employeeService.addEmployee(emp);
		 if(employee == null) {
			 
			 ResponseEntity responseEntity = new ResponseEntity<>("Employee already exists", HttpStatus.ALREADY_REPORTED);
			 return responseEntity;
		 }
		 ResponseEntity responseEntity = new ResponseEntity<>("Employee registered Successfully", HttpStatus.OK);
			
		 return responseEntity;     //ResponseEntity.ok(employee);
	}

	// Get Employee by Id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee emp=employeeService.ProcessEmployeeById(id);
		return ResponseEntity.ok(emp);
	}
	
	//Get Employee by Email
	@GetMapping("/employee/{emailId}")
	public ResponseEntity<Employee> getEmployeeByEmailId(@PathVariable String emailId){
		Employee emp=employeeService.processEmployeeByEmail(emailId);
		return ResponseEntity.ok(emp);
	}
	

	// Update Employee
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee empDetails) {
		Employee updatedEmp=employeeService.UpdateEmp(id, empDetails);
		return ResponseEntity.ok(updatedEmp);
	}
	
	
	//Delete Employee
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmp(@PathVariable Long id){
		employeeService.DeleteEmployee(id);
		Map<String,Boolean> response=new HashMap<String,Boolean>();
		response.put("Deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	//download employee information in PDF format
	@GetMapping("/employee/export/pdf/{id}")
	public void exportToPDF(@PathVariable Long id,HttpServletResponse response) throws DocumentException, IOException {
		
		Employee emp=employeeService.ProcessEmployeeById(id);
        System.out.println(emp);
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Employee " + emp.getFirstName()+ " "+ emp.getLastName() + ".pdf";
        response.setHeader(headerKey, headerValue);
        
        EmployeePDFExporter exporter = new EmployeePDFExporter(emp);
        exporter.export(response);
	}
	
	
	//Upload image file
	@CrossOrigin(origins = "http://localhost:8080")
	@PostMapping("upload-file")
	public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("id") Long employeeId){
		FileUploadResponse response = new FileUploadResponse();
		try {
			//validation of  image file
			if(file.isEmpty()) {
				response.setMessage("Request must contain file");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}
			if(!file.getContentType().equals("image/jpeg")) {
				response.setMessage("only jpeg images is allowed");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
			}
			
			//upload file
			String path=fileUploadService.uploadFile(file);
			
			//update path
			Integer uploadedData = employeeService.uploadEmployeeProfilePic(employeeId, path);
			if(!path.equals("") && uploadedData>0) {
				response.setMessage("Profile uploaded succesfully");
				response.setProfileImagePath(path);
				return ResponseEntity.ok(response);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		response.setMessage("something went wrong !");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	}
	


