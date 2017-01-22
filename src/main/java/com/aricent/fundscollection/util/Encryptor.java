package com.aricent.fundscollection.util;

import org.jasypt.util.password.StrongPasswordEncryptor;

public final class Encryptor {
	
	 //create an object of SingleObject
	   private static StrongPasswordEncryptor instance = new StrongPasswordEncryptor();

	   //make the constructor private so that this class cannot be
	   //instantiated
	   private Encryptor(){}

	   //Get the only object available
	   public static StrongPasswordEncryptor getInstance(){
	      return instance;
	   }
}
