package com.aricent.fundscollection.service;

import com.aricent.fundscollection.model.Employee;

public interface UserService {

	public boolean login(Long userName, String password);
	
	public boolean registerEmployee(Employee employee);
	
	public boolean isEmployeeIdUnique(long empId);
	
}
