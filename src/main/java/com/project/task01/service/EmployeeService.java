package com.project.task01.service;

import com.project.task01.entity.Employee;
import com.project.task01.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

 /*
	This service classes is to centralize and encapsulate the application's business logic,
	making it easier to manage, test, and maintain. Service classes often act as an intermediary layer between
	the presentation layer and the data access layer. They contribute to the overall organization and
	 modularization of the application. This service class is responsible to CURD operation for employee
 */


@Service
public class EmployeeService {
	@Autowired
	EmployeeRepo employeeRepo;

	public List<Employee> getAllEmployees() {
		return employeeRepo.getAllEmployees();
	}

	public Employee getEmployeeById(Long employeeId) {
		return employeeRepo.getEmployeeById(employeeId);
	}

	public String addEmployee(Employee employee) {
		return employeeRepo.addEmployee(employee);
	}

	public String updateEmployee(Employee employee) {
		return employeeRepo.updateEmployee(employee);
	}

	public String deleteEmployee(Long employeeId) {
		return employeeRepo.deleteEmployee(employeeId);
	}
}
