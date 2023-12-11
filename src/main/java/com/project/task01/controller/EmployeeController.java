package com.project.task01.controller;

import com.project.task01.entity.Employee;
import com.project.task01.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 /*
		This is Department Controller, Here we got all apies
		I make this class as a RestController.
		This annotation combines the @Controller and @ResponseBody annotations.
		This annotation is a convenient way to create RESTful web services in Spring
		by combining the @Controller and @ResponseBody annotations.
		It eliminates the need to annotate each method with @ResponseBody explicitly
		and is commonly used when the goal is to create APIs that return data in
		a format such as JSON or XML.
		Here i follow REST API for design these api
 */


@RestController
@RequestMapping("task01/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

//  Getting All Employees
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
	}

//  Getting Employee By Employee ID
	@GetMapping("/{employeeId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long employeeId) {
		return new ResponseEntity<>(employeeService.getEmployeeById(employeeId), HttpStatus.FOUND) ;
	}

//  Adding Employee
	@PostMapping
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED) ;
	}

//  Updating Employee
	@PutMapping("/{employeeId}")
	public ResponseEntity<String> updateEmployee(@PathVariable Long employeeId, @RequestBody Employee employee) {
		employee.setId(employeeId);
		return new ResponseEntity<>(employeeService.updateEmployee(employee), HttpStatus.OK);
	}

//	Deleting employee
	@DeleteMapping("/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId) {
		return new ResponseEntity<>(employeeService.deleteEmployee(employeeId), HttpStatus.OK);
	}
}
