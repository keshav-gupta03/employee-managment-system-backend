 package com.example.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import com.example.springreact.exception.ResourceNotFoundException;
import com.example.springreact.model.Employee;
import com.example.springreact.repository.EmployeeRepository;
import com.example.springreact.service.EmployeeServiceImpl;
import com.example.util.GetEmployee;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(MockitoJUnitRunner.class)
@PowerMockIgnore({"javax.management.*","javax.crypto.*","javax.net.ssl.*"})
public class EmployeeServiceTests {
	
	 

	public EmployeeServiceTests() {}

	@Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeServiceImpl employeeServiceMock;
    
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    public Employee employee;
    
    @Before
    public void setup(){
//        employeeRepository = Mockito.mock(EmployeeRepository.class);
//        employeeService = new EmployeeServiceImpl();
        employee = GetEmployee.createEmployee();
    }
    
    
    
    // JUnit test for saveEmployee method
    @Test
    public void addEmployee_test(){
    	
//         when(employeeRepository.findByEmailId(employee.getEmailId())).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);

        // when -  action or the behavior that we are going test
        Employee savedEmployee = employeeService.addEmployee(employee);
        // then - verify the output
//        assertThat(savedEmployee).isNotNull();
        assertEquals(employee.getDesignation(), savedEmployee.getDesignation());
        assertEquals(employee.getEmailId(), savedEmployee.getEmailId());
        assertEquals(employee.getFirstName(), savedEmployee.getFirstName());
        assertEquals(employee.getId(), savedEmployee.getId());
        assertEquals(employee.getLastName(), savedEmployee.getLastName());
        assertEquals(employee.getPassword(), savedEmployee.getPassword());
        assertEquals(employee.getProfileImage(), savedEmployee.getProfileImage());
    }
    
 // JUnit test for saveEmployee method
    @Test
    public void addEmployee_testThrows(){
    	
//         when(employeeRepository.findByEmailId(employee.getEmailId())).thenReturn(employee);
         when(employeeRepository.save(any())).thenReturn(new ResourceNotFoundException("some exception"));

        // when -  action or the behavior that we are going test
         employeeService.addEmployee(employee);
       
    }
    
    
    
    
    //Junit test for getAllEmployees method
    //@Test
    public void ProcessEmployeeList_Test() {
    	Employee employee1=GetEmployee.createEmployee();    	
    	//given(employeeRepository.findAll()).willReturn(List.of(employee,employee1));
    	
    	List<Employee> employeeList=employeeService.ProcessEmployeeList();
    
    	assertNotNull(employeeList);
    	assertEquals(2, employeeList.size());
    }
    
    
    //JUnit test for getEmployee by id
    @Test
    public void ProcessEmployeeById_test() {
    	
    	Employee emp=GetEmployee.createEmployee();
    	given(employeeRepository.findById(1L)).willReturn(Optional.of(emp));
    	
    	
    	Employee employee=employeeService.ProcessEmployeeById(emp.getId());
    	
    	assertEquals(1L,employee.getId());
    	assertNotNull(employee);
    }
    
    
  //JUnit test for getEmployee by id
    @Test(expected = ResourceNotFoundException.class)
    public void processEmployeeById_testThrows() {
    	
    	
    	when(employeeRepository.findById(anyLong())).thenThrow(new ResourceNotFoundException("Employee not Exist with id : 1L"));
    	employeeService.ProcessEmployeeById(1L);

        	
    	
    }
    
    
    // JUnit test for UpdateEmp
    @Test
    public void UpdateEmp_test() {
    	Optional<Employee> employeeFromDB=Optional.ofNullable(GetEmployee.createEmployee());
        when(employeeRepository.findById(anyLong())).thenReturn(employeeFromDB);
        when(employeeRepository.save(employeeFromDB.get())).thenReturn(employeeFromDB.get());
        employeeService.UpdateEmp(1l, employee);
        Employee updatedEmployee = employeeFromDB.get();
        assertEquals(employee.getFirstName(), updatedEmployee.getFirstName());
        assertEquals(employee.getLastName(), updatedEmployee.getLastName());
        assertEquals(employee.getEmailId(), updatedEmployee.getEmailId());
        assertEquals(employee.getDesignation(), updatedEmployee.getDesignation());
    	
    }
    
    
    //JUnit test for DeleteEmp
    @Test
    public void DeleteEmployee_test() {
    	Long employeeId=1L;
    	Optional<Employee> employee=Optional.ofNullable(GetEmployee.createEmployee());
       when(employeeRepository.findById(anyLong())).thenReturn(employee);
       willDoNothing().given(employeeRepository).deleteById(employeeId);
       
       employeeService.DeleteEmployee(employeeId);
       
       verify(employeeRepository, times(1)).deleteById(employeeId);

    }
    
    //JUnit test for processEmployeeByEmail
    @Test
    public void processEmployeeByEmail_test() {
    	Employee employee=GetEmployee.createEmployee();
    	given(employeeRepository.findByEmailId(employee.getEmailId())).willReturn(employee);
    	
    	Employee emp=employeeService.processEmployeeByEmail(employee.getEmailId());
    	
    	assertEquals(employee.getEmailId(), emp.getEmailId());
    }
    
    //JUnit test for uploadEmployeeProfilePic
    @Test
    public void uploadEmployeeProfilePic_test() {
    	Employee emp=GetEmployee.createEmployee();
    	Integer expected = 1;
    	when(employeeRepository.updateEmployeeProfilePicById(anyLong(), anyString())).thenReturn(1);
    	Integer rowsAfftected = employeeService.uploadEmployeeProfilePic(1l, "http://localhost:8080");
    	assertEquals(expected, rowsAfftected);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void test1_test() {
    	doThrow(new IllegalArgumentException()).when(employeeServiceMock).test1(1);
    	employeeService.test1(-1);
    }
    

}
