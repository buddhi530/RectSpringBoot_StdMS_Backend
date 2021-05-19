package com.example.demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourcenotFoundexception;
import com.example.demo.model.Employee;
import com.example.demo.repostory.EmployeRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeRepository employerepository;
	
	
	//get all emp
		@GetMapping("/employees")
	public List<Employee> getAllemployee()
	{
		return employerepository.findAll();
	}
		
	//create employee rest api	
		@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee)
	{
		return employerepository.save(employee);
	}
		
		//get employee id using rest api
		@GetMapping("/employees/{id}")
	public 	ResponseEntity<Employee> getEmployeebyId(@PathVariable Integer id)
	{
		Employee employee = employerepository.findById(id).orElse(null);
		// .orElseThrow(() -> new MeetingDoesNotExistException(""error occur"));
		return ResponseEntity.ok(employee);
	}
		
		
		//update employee rest api
		@PutMapping("/employees/{id}")
		public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id,  @RequestBody Employee employeedetails)
		{
			
			Employee employee = employerepository.findById(id).orElse(null);
			employee.setName(employeedetails.getName());
			employee.setEmail(employeedetails.getEmail());
			employee.setAddress(employeedetails.getAddress());
			
			Employee updateemployee = employerepository.save(employee);
			return ResponseEntity.ok(updateemployee);
			
			
		}
		
		
		//delete employee rest api
		@DeleteMapping("/employees/{id}")
		public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Integer id)
		{
				Employee employee = employerepository.findById(id).orElse(null);
				
				employerepository.delete(employee);
				Map<String, Boolean> response = new HashMap<>();
				response.put("deleted",Boolean.TRUE);
				return ResponseEntity.ok(response);
			
		}
		
		
	
}
