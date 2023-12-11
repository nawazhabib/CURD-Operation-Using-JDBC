package com.project.task01;

import com.project.task01.component.CreateTables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Task01Application implements CommandLineRunner {
	@Autowired
	CreateTables createTables;
	public static void main(String[] args) {
		SpringApplication.run(Task01Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		calling create table method from create tables class
		createTables.createTables();
	}
}
