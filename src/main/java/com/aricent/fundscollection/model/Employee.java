package com.aricent.fundscollection.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import org.springframework.stereotype.Component;


/**
 * The persistent class for the EMPLOYEE database table.
 * 
 */
@Component
@Entity
@Table(name="EMPLOYEE")
public class Employee implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public Employee() {
		System.out.println(" Initializing Employee()... ");
	}

	public Employee(String firstName, String lastName, long contact, String email, Date dob, String gender, long userName, String passWord) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.contact = contact;
		this.email = email;
		this.dob = dob;
		this.gender = gender;
		this.userName = userName;
		this.passWord = passWord;
	}

	String firstName;
	String lastName;
	long contact;
	String email;
	Date dob;
	String gender;
	@Id
	long userName;
	String passWord;

		
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public long getContact() {
		return this.contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDob() {
		return dob;
	}
	
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public long getUserName() {
		return userName;
	}
	
	public void setUserName(long userName) {
		this.userName = userName;
	}
	
	public String getPassWord() {
		return passWord;
	}
	
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	@Override
	public String toString() {
		return "Employee [FirstName=" + firstName + ", " +
						 "LastName=" + lastName + ", " +
						 "email=" + email + ", " +
						 "DOB=" + dob + ", " +
						 "Gender=" + gender + ", " + 
						 "userName=" + userName + ", " + 
						 "password=" + passWord + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (contact ^ (contact >>> 32));
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (userName ^ (userName >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (contact != other.contact)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (userName != other.userName)
			return false;
		return true;
	}
	
	

}
