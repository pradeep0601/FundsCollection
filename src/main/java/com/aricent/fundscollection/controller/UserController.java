package com.aricent.fundscollection.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aricent.fundscollection.model.Employee;
import com.aricent.fundscollection.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	private static final String HOME_PAGE = "home";
	private static final String ERROR_PAGE = "error";
	
	@Autowired
	public UserController(UserService userService) {
	    this.userService = userService;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request) {

		boolean isAuthenticated = false;
		
		if(request != null){
			String userName = request.getParameter("userName");
			String password = request.getParameter("passWord");
			
			isAuthenticated = userService.login(userName, password);
			
		}
		
		return isAuthenticated ? HOME_PAGE :  ERROR_PAGE;
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public boolean register(@RequestBody Employee emp) {

		System.out.println(emp);

		boolean result = userService.registerEmployee(emp);

		return result;
	}
	
	@RequestMapping(value = "/isUniqueValue", method = RequestMethod.POST)
	@ResponseBody
	public boolean isUniqueValue(@RequestBody long empId) {
		
		boolean isUnique = false; 

		System.out.println("Going to check in DB for the empId = " + empId);

		isUnique = userService.isEmployeeIdUnique(empId);

		return isUnique;
	}
}
