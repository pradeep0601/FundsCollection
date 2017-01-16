package com.aricent.fundscollection.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.aricent.fundscollection.model.Employee;
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
		
		return employee.getPassWord().equals(password) ? true : false;
	}

	public boolean registerEmployee(Employee emp) {
		
		Gson gson = new Gson();
		
		/*try 
		{
			gson.toJson(emp,new FileWriter("test.json"));
		}
		catch (JsonIOException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}*/
		
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
