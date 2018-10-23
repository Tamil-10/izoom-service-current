package com.izoom.izoomservice.admin.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.izoom.izoomservice.admin.dao.AdminDAO;
import com.izoom.izoomservice.admin.pojo.Admin;
import com.izoom.izoomservice.product.pojo.SearchCriteria;
import com.izoom.izoomservice.user.pojo.User;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminService {
	private static Logger LOGGER = LoggerFactory.getLogger(AdminService.class);
	
	@Autowired
	AdminDAO adminDAO;
	
	


	
	
	
//	For Login
	
	
	@RequestMapping(method = RequestMethod.POST, value = "checkLogin")
	public Map<String, Object>  checkLogin(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password) throws Exception {
	System.out.println("jsaf----");
		LOGGER.info("createUser enter");
		Admin admin = new Admin();
//		admin.setCreated_date(new Date());
		admin.setUsername(username);
		admin.setPassword(password);
		LOGGER.info("createUser enter"+username+password);
		//userDAO.checkLogin(user);
		LOGGER.info("createUser exit");
		Map<String, Object> userList = adminDAO.checkLogin(admin);
		System.out.println("userlist"+userList);
		return userList;
	
		
		
		

	}
	
	
	
//	user List
	
	
	@RequestMapping(method = RequestMethod.POST, value = "userList")
	public List<Admin>  userList(@RequestBody User user) throws Exception {
	System.out.println("jsaf----");
		LOGGER.info("createUser enter");

		LOGGER.info("createUser exit");
		List<Admin> userList = adminDAO.userList();
		
		for(Admin e:userList)  
	        System.out.println("row "+e.getName());  
		
		return userList;
	
	}
	


	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "addPost")
	public String createUser(@RequestParam(value = "userid") Integer userid,@RequestParam(value = "youtube") String youtube) throws Exception {
	System.out.println("jsaf----");
		LOGGER.info("createUser enter");
		User user = new User();
		user.setCreated_date(new Date());
		user.setYoutube(youtube);
		user.setUserid(userid);
		
		LOGGER.info("createUser enter"+youtube+userid);
		//userDAO.createUser(user);

//			if(userDAO.createUser(user)=="success")
//			{
//				
//				System.out.println("Logged success");
//				LOGGER.info("createUser exit");
//				return "success";
//			}
//			else
//			{
//				
//				System.out.println("Logged Failed");
//				LOGGER.info("createUser exit");
//				return  "failed";
//			}

		
	
	return "success";

	}
	
	
	
}
