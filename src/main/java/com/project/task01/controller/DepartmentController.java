package com.project.task01.controller;

import com.project.task01.entity.Department;
import com.project.task01.service.DepartmentService;
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
@RequestMapping("task01/department")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;

//	Creating this api to test our server is running from front end side
	@GetMapping("/test")
	private String test() {
		return "Server running";
	}

//	Getting all Departments
	@GetMapping
	public ResponseEntity<List<Department>> getAllDepartments() {
		return new ResponseEntity<>(departmentService.getAllDepartments(),
				HttpStatus.OK);
	}

//	Getting Department By Department ID
	@GetMapping("/{departId}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable Long departId) {
		return new ResponseEntity<>(departmentService.getDepartmentById(departId), HttpStatus.FOUND);
	}

//  Creating Department
	@PostMapping
	public ResponseEntity<String> addDepartment(@RequestBody Department department) {
		return new ResponseEntity<>(departmentService.addDepartment(department), HttpStatus.CREATED);
	}

//	Updating Department
	@PutMapping("/{departId}")
	public ResponseEntity<String> updateDepartmentByDepartId(@PathVariable Long departId, @RequestBody Department department) {
		department.setDeptId(departId);
		return new ResponseEntity<>(departmentService.updateDepartment(department), HttpStatus.OK);
	}

//	Deleting Department
	@DeleteMapping("/{departId}")
	public ResponseEntity<String> deleteDepartment(@PathVariable Long departId) {
		return new ResponseEntity<>(departmentService.deleteDepartment(departId), HttpStatus.OK);
	}

}
