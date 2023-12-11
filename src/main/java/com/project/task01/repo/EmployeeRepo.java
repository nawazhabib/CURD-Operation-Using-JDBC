package com.project.task01.repo;

import com.project.task01.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
public class EmployeeRepo {

	private final Logger logger = Logger.getLogger(Employee.class.getName());

	@Autowired
	private DataSource dataSource;

//	getting all employees
	public List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();
				Statement statement = dataSource.getConnection().createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM Employee")) {

			logger.info("Gating All Employees");

//			iterating resultSet to get all employee
			while (resultSet.next()) {
//				creating employee object
				Employee employee = new Employee();
//				adding all properties on employee object
				employee.setId(resultSet.getLong("ID"));
				employee.setName(resultSet.getString("NAME"));
				employee.setDepartmentID(resultSet.getLong("DEPARTMENT_ID"));
				employee.setDesignation(resultSet.getString("DESIGNATION"));
				employee.setSalary(resultSet.getInt("SALARY"));
				employee.setBirthDate(resultSet.getDate("BIRTH_DATE"));
				employee.setJoinDate(resultSet.getDate("JOIN_DATE"));
				employee.setStatus(resultSet.getString("STATUS"));
				employee.setCreatedDate(resultSet.getDate("CREATED_DATE"));
				employee.setUpdateDate(resultSet.getDate("UPDATE_DATE"));
//				adding employee to employee list
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return employees;
	}


//	gating employee by employee id
	public Employee getEmployeeById(Long employeeId) {
		Employee employee = null;
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Employee WHERE ID=?")) {

			preparedStatement.setLong(1, employeeId);
			ResultSet resultSet = preparedStatement.executeQuery();

			logger.info("Gating Employee By EmployeeID");

			if (resultSet.next()) {
//				creating employee object
				employee = new Employee();
				employee.setId(resultSet.getLong("ID"));
				employee.setName(resultSet.getString("Name"));
				employee.setDepartmentID(resultSet.getLong("DEPARTMENT_ID"));
				employee.setDesignation(resultSet.getString("DESIGNATION"));
				employee.setSalary(resultSet.getInt("SALARY"));
				employee.setBirthDate(resultSet.getDate("BIRTH_DATE"));
				employee.setJoinDate(resultSet.getDate("JOIN_DATE"));
				employee.setStatus(resultSet.getString("STATUS"));
				employee.setCreatedDate(resultSet.getDate("CREATED_DATE"));
				employee.setUpdateDate(resultSet.getDate("UPDATE_DATE"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

//	adding new employee
	public String addEmployee(Employee employee) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Employee (NAME, DEPARTMENT_ID, DESIGNATION, SALARY, BIRTH_DATE, JOIN_DATE, STATUS, CREATED_DATE, UPDATE_DATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

			logger.info("Adding Employee");

			preparedStatement.setString(1, employee.getName());
			preparedStatement.setLong(2, employee.getDepartmentID());
			preparedStatement.setString(3, employee.getDesignation());
			preparedStatement.setInt(4, employee.getSalary());
			preparedStatement.setDate(5, employee.getBirthDate());
			preparedStatement.setDate(6, employee.getJoinDate());
			preparedStatement.setString(7, employee.getStatus());
			preparedStatement.setDate(8, employee.getCreatedDate());
			preparedStatement.setDate(9, employee.getUpdateDate());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "Successfully Add Employee";
	}

//	updating employee
	public String updateEmployee(Employee employee) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Employee SET NAME=?, DEPARTMENT_ID=?, DESIGNATION=?, SALARY=?, BIRTH_DATE=?, JOIN_DATE=?, STATUS=?, CREATED_DATE=?, UPDATE_DATE=? WHERE ID=?")) {

			logger.info("Updating Employee By Employee Object");

			preparedStatement.setString(1, employee.getName());
			preparedStatement.setLong(2, employee.getDepartmentID());
			preparedStatement.setString(3, employee.getDesignation());
			preparedStatement.setInt(4, employee.getSalary());
			preparedStatement.setDate(5, employee.getBirthDate());
			preparedStatement.setDate(6, employee.getJoinDate());
			preparedStatement.setString(7, employee.getStatus());
			preparedStatement.setDate(8, employee.getCreatedDate());
			preparedStatement.setDate(9, employee.getUpdateDate());
			preparedStatement.setLong(10, employee.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "Successfully Update";
	}

//	deleting employee
	public String deleteEmployee(Long employeeId) {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Employee WHERE ID=?")) {

			logger.info("Deleting Employee By EmployeeID");

			preparedStatement.setLong(1, employeeId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Successfully Deleted";
	}
}
