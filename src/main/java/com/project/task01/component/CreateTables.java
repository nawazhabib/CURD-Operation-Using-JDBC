package com.project.task01.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;


/*
	This class is responsible for create Departments and Employee Table
	This class method was called on main method so that we create table when we
	run our project at first time.
*/

@Component
public class CreateTables {

	@Autowired
	private DataSource dataSource;

	private final String CREATE_DEPARTMENTS_TABLE = "CREATE TABLE IF NOT EXISTS Departments (" +
			"DEPT_ID BIGINT PRIMARY KEY AUTO_INCREMENT, " +
			"DEPARTMENT_NAME VARCHAR(255) NOT NULL, " +
			"CREATED_DATE DATE NOT NULL)";

	private final String CREATE_EMPLOYEE_TABLE = "CREATE TABLE IF NOT EXISTS Employee (" +
			"ID BIGINT PRIMARY KEY AUTO_INCREMENT, " +
			"NAME VARCHAR(255) NOT NULL, " +
			"DEPARTMENT_ID BIGINT, " +
			"DESIGNATION VARCHAR(255), " +
			"SALARY DOUBLE, " +
			"BIRTH_DATE DATE, " +
			"JOIN_DATE DATE, " +
			"STATUS VARCHAR(50), " +
			"CREATED_DATE DATE NOT NULL, " +
			"UPDATE_DATE DATE, " +
			"FOREIGN KEY (DEPARTMENT_ID) REFERENCES Departments(DEPT_ID))";

 /*
		This createTables method is responsible for creating our tables
		I connect with database in try block
		Here I follow try with resource methodology
 */
	public void createTables() {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement createDepartmentsTable = connection.prepareStatement(CREATE_DEPARTMENTS_TABLE);
				PreparedStatement createEmployeeTable = connection.prepareStatement(CREATE_EMPLOYEE_TABLE)) {

			createDepartmentsTable.executeUpdate();
			createEmployeeTable.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
