package com.project.task01.repo;

import com.project.task01.entity.Department;
import org.springframework.stereotype.Repository;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

 /*
		This class is responsible for execute query for GET, CREATE, RED, UPDATE and DELETE
		By encapsulating the data access logic within a repository class, the rest
		of the application can interact with a clean, domain-specific interface,
		promoting separation of concerns and maintainability
 */

@Repository
public class DepartmentRepo {

	private final Logger logger = Logger.getLogger(Department.class.getName());

	@Autowired
	private DataSource dataSource;

//	getting all departments
	public List<Department> getAllDepartments() {
		List<Department> departments = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM Departments")) {

			logger.info("Gating all Departments");
//  iterating on resultSet to get all employee
			while (resultSet.next()) {
//				creating new Department object
				Department department = new Department();
				department.setDeptId(resultSet.getLong("DEPT_ID"));
				department.setDepartmentName(resultSet.getString("DEPARTMENT_NAME"));
				department.setCreatedDate(resultSet.getDate("CREATED_DATE"));
//				add department object to departments list
				departments.add(department);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return departments;
	}

//	geting department by department id
	public Department getDepartmentById(Long deptId) {
		Department department = null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Departments WHERE DEPT_ID=?")) {

			preparedStatement.setLong(1, deptId);
			ResultSet resultSet = preparedStatement.executeQuery();

			logger.info("Gating Department By DepartmentID");

			if (resultSet.next()) {
//				creating department object
				department = new Department();
//				adding department properties to the department object which we found by id
				department.setDeptId(resultSet.getLong("DEPT_ID"));
				department.setDepartmentName(resultSet.getString("DEPARTMENT_NAME"));
				department.setCreatedDate(resultSet.getDate("CREATED_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return department;
	}

//	add department to database
	public String addDepartment(Department department) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Departments (Department_Name, Created_Date) VALUES (?, ?)")) {

			logger.info("Adding Department");

			preparedStatement.setString(1, department.getDepartmentName());
			preparedStatement.setDate(2, new java.sql.Date(department.getCreatedDate().getTime()));

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Successfully Add Department";
	}

//	update department
	public String updateDepartment(Department department) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Departments SET Department_Name=?, Created_Date=? WHERE DEPT_ID=?")) {

			logger.info("Updating Department by Department Object");

			preparedStatement.setString(1, department.getDepartmentName());
			preparedStatement.setDate(2, new java.sql.Date(department.getCreatedDate().getTime()));
			preparedStatement.setLong(3, department.getDeptId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Successfully Update Department";
	}

//	deleting department
	public String deleteDepartment(Long deptId) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Departments WHERE DEPT_ID=?")) {

			logger.info("Deleting Department");

			preparedStatement.setLong(1, deptId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Successfully Delete Department";
	}
}
