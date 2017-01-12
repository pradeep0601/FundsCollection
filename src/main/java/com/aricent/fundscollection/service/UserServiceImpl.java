package com.aricent.fundscollection.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aricent.fundscollection.dao.UserDAO;
import com.aricent.fundscollection.model.Employee;
/**
 * 
 * @author pradeku7
 * Annotate all your service classes with @Service. All your business logic should be in Service classes.
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

	private UserDAO userDAO;
	
	@Autowired
	public UserServiceImpl(UserDAO userDAO) {
	    this.userDAO = userDAO;
	}
	@Override
	public boolean login(String userName, String password) {
		
		return userDAO.login(userName, password);
	}

	@Override
	public boolean registerEmployee(Employee employee) {
		
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		String encryptedPassword = passwordEncryptor.encryptPassword(employee.getPassWord());
		
		employee.setDob(new Date(System.currentTimeMillis()).toString());
		employee.setPassWord(encryptedPassword);
		
		return userDAO.registerEmployee(employee);
	}
	
	@Override
	public boolean isEmployeeIdUnique(long empId) {
		
		return userDAO.isEmployeeIdUnique(empId);
		
	}
}
