package com.aricent.fundscollection.model;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Component
public class MyAlbum implements Serializable {
	
	private static final long serialVersionUID = 1L;

	String name;
	
	String description;
	
	CommonsMultipartFile file;
	
	public MyAlbum() {
		System.out.println(" Initializing Album()... ");
	}

	public MyAlbum(String description, String name, CommonsMultipartFile file) {
		super();
		this.description = description;
		this.name = name;
		this.file = file;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}

	

}