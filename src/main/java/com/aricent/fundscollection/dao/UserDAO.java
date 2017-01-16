package com.aricent.fundscollection.dao;

import com.aricent.fundscollection.model.Employee;

public interface UserDAO {

	public boolean login(Long userName, String password);
	
	public boolean registerEmployee(Employee emp);
	
	public boolean isEmployeeIdUnique(long empId);
}
