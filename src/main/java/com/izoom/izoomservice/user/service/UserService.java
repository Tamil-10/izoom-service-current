package com.izoom.izoomservice.user.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.izoom.izoomservice.product.pojo.Address;
import com.izoom.izoomservice.user.dao.UserDAO;
import com.izoom.izoomservice.user.pojo.User;
import com.izoom.izoomservice.user.pojo.Videos;
import com.izoom.izoomservice.user.pojo.Comments;
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
	
	
	// @RequestMapping(method = RequestMethod.POST, value = "createFb")
	// public String createFb()  throws Exception {
	// 	LOGGER.info("createfb function called");
	// 	userDAO.createFb();
	// 	return "success";
	// }
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
	
	
	
	
//	To get Posted Video Details
	
	@RequestMapping(method = RequestMethod.POST, value = "getVideos/{id}")
	public  List<Videos>  getVideos(@PathVariable(value = "id") Integer id ) throws Exception {
	System.out.println("jsaf----");
		LOGGER.info("createUser enter");
		User user = new User();
		user.setCreated_date(new Date());
		List<Videos> videosList = null;

		LOGGER.info("createUser exit");
		videosList = userDAO.getVideos(id);
		System.out.println("userlist"+videosList);
		return videosList;

		

	}
	

	
	
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "getComments/{id}")
	public List<Map<String, Object>> getComments(@PathVariable(value = "id") String youtube_link) throws Exception {
		LOGGER.info("getOrderSummary enter");
		List<Map<String, Object>> result = null;
		try {
			result = userDAO.getComments(youtube_link);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("getOrderSummary exit");
		return result;
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "getCommentCount/{id}")
	public List<Map<String, Object>> getCommentCount(@PathVariable(value = "id") String youtube_link) throws Exception {
		LOGGER.info("getOrderSummary enter");
		List<Map<String, Object>> result = null;
		try {
			result = userDAO.getCommentCount(youtube_link);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("getOrderSummary exit");
		return result;
	}
	
	
	
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "createComment")
	public String createSocialUser(@RequestParam(value = "name") String name,@RequestParam(value = "comment") String comments, @RequestParam(value = "youtube") String youtube_link) throws Exception {
		LOGGER.info("createSocialUser function called");
		Comments comment = new Comments();
		
		comment.setName(name);
		comment.setComments(comments);

		comment.setYoutube_link(youtube_link);
		LOGGER.info("passed parameters are "+comment+name+youtube_link);
		//userDAO.createUser(user);
		
			if(userDAO.createComment(comment)=="success")
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
	
	
	

	
}
