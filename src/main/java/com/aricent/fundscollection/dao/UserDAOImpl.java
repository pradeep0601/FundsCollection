package com.aricent.fundscollection.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Repository;

import com.aricent.fundscollection.model.Employee;
import com.aricent.fundscollection.util.Encryptor;
import com.google.gson.Gson;
/**
 * 
 * @author pradeku7
 * Annotate all your DAO classes with @Repository. All your database access logic should be in DAO classes.
 */
@Repository
public class UserDAOImpl implements UserDAO{
	
	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
        return entityManager;
    }
	
    
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
	public boolean login(Long userName, String password) {
		boolean isValid = false;
		
		Employee employee = entityManager.find(Employee.class, Long.valueOf(userName));
		
		if(employee == null){
			return isValid;
		}
		StrongPasswordEncryptor passwordEncryptor = Encryptor.getInstance();
		return passwordEncryptor.checkPassword(password, employee.getPassWord()) ? true : false;
	}

	public boolean registerEmployee(Employee emp) {
		
		Gson gson = new Gson();
		
		entityManager.persist(emp);
		
		System.out.println(gson.toJson(emp));
		
		return true;
	}


	@Override
	public boolean isEmployeeIdUnique(long empId) {
		
		boolean isUnique = true;
		
		Employee employee = entityManager.find(Employee.class, Long.valueOf(empId));
		
		if(employee == null){
			return isUnique;
		}
		return false;
	}

}
