package com.project.task01.service;

import com.project.task01.entity.Department;
import com.project.task01.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

 /*
	This service classes is to centralize and encapsulate the application's business logic,
	making it easier to manage, test, and maintain. Service classes often act as an intermediary layer between
	the presentation layer and the data access layer. They contribute to the overall organization and
	 modularization of the application. This service class is responsible to CURD operation for department
 */


@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepo departmentRepo;

//	curd operations
	public List<Department> getAllDepartments() {
		return departmentRepo.getAllDepartments();
	}

	public Department getDepartmentById(Long deptId) {
		return departmentRepo.getDepartmentById(deptId);
	}

	public String addDepartment(Department department) {
		return departmentRepo.addDepartment(department);
	}

	public String updateDepartment(Department department) {
		return departmentRepo.updateDepartment(department);
	}

	public String deleteDepartment(Long deptId) {
		return departmentRepo.deleteDepartment(deptId);
	}
}
