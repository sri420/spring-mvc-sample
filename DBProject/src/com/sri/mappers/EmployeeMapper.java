package com.sri.mappers;

import java.util.List;

import com.sri.domain.Employee;

public interface EmployeeMapper {

	List<Employee> findAllEmployees();
	List<Employee> findByNameOrAll(Integer name);
	
	Employee findEmployeeById(Integer id);
	
	}

