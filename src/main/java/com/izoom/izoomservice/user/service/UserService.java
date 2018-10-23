package com.izoom.izoomservice.user.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.izoom.izoomservice.user.dao.UserDAO;
import com.izoom.izoomservice.user.pojo.User;

@RestController
@RequestMapping(value = "/api/user")
public class UserService {
	private static Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserDAO userDAO;
	
	

//	For Registering New User
	
	@RequestMapping(method = RequestMethod.POST, value = "createUser")
	public String createUser(@RequestParam(value = "username") String username,@RequestParam(value = "firstname") String firstname,@RequestParam(value = "lastname") String lastname,@RequestParam(value = "password") String password, @RequestParam(value = "login_type") Integer login_type) throws Exception {
	System.out.println("jsaf----");
		LOGGER.info("createUser enter");
		User user = new User();
		user.setCreated_date(new Date());
		user.setUsername(username);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setPassword(password);
		user.setLogin_type(login_type);
		LOGGER.info("createUser enter"+username+firstname+lastname+password+login_type);
		//userDAO.createUser(user);

			if(userDAO.createUser(user)=="success")
			{
				
				System.out.println("Logged success");
				LOGGER.info("createUser exit");
				return "success";
			}
			else
			{
				
				System.out.println("Logged Failed");
				LOGGER.info("createUser exit");
				return  "failed";
			}

		
	
	//	return "success";

	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "createSocialUser")
	public String createSocialUser(@RequestParam(value = "username") String username,@RequestParam(value = "name") String name, @RequestParam(value = "login_type") Integer login_type) throws Exception {
		LOGGER.info("createSocialUser function called");
		User user = new User();
		user.setCreated_date(new Date());
		user.setUsername(username);
		user.setFirstname(name);

		user.setLogin_type(login_type);
		LOGGER.info("passed parameters are "+username+name+login_type);
		//userDAO.createUser(user);
		
			if(userDAO.createSocialUser(user)=="success")
			{
				
				System.out.println("Logged success");
				LOGGER.info("createUser exit");
				return "success";
			}
			else
			{
				
				System.out.println("Logged Failed");
				LOGGER.info("createUser exit");
				return  "failed";
			}
		

		
	
	//	return "success";

	}
	
	
	
	
	
	
	
//	For Login
	
	
	@RequestMapping(method = RequestMethod.POST, value = "checkLogin")
	public Map<String, Object>  checkLogin(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password) throws Exception {
	System.out.println("jsaf----");
		LOGGER.info("createUser enter");
		User user = new User();
		user.setCreated_date(new Date());
		user.setUsername(username);
		user.setPassword(password);
		LOGGER.info("createUser enter"+username+password);
		//userDAO.checkLogin(user);
		LOGGER.info("createUser exit");
		Map<String, Object> userList = userDAO.checkLogin(user);
		System.out.println("userlist"+userList);
		return userList;
//		if(userDAO.checkLogin(user)=="success")
//		{
//			System.out.println("Logged success");
//			return "success";
//		}
//		else
//		{
//			
//			System.out.println("Logged Failed");
//			return  "failed";
//		}
//		
		
		
		
		

	}
	
}
