package com.izoom.izoomservice.user.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.izoom.izoomservice.product.pojo.Address;
import com.izoom.izoomservice.user.dao.UserDAO;
import com.izoom.izoomservice.user.pojo.User;
import com.izoom.izoomservice.user.pojo.Videos;
import com.izoom.izoomservice.user.pojo.Comments;
import com.izoom.izoomservice.user.repository.UserRespositary;
import com.izoom.izoomservice.user.repository.CommentRespositary;


@Repository
public class UserDAO {
	public static Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);
	@Autowired
	UserRespositary userRespository;

	@Autowired
	CommentRespositary commentRespository;

	
	
	@Autowired
	JdbcTemplate jdbcTemplate;


	public String createUser(User user) {
		LOGGER.info("create user enter");
		System.out.println("name::" + user.getUsername());

		
		String prod_inst_sql = "select count(*) as total from USER_DETAILS where USERNAME=? and LOGIN_TYPE=1";
		int count = 0;
		try {
			count = jdbcTemplate.queryForObject(prod_inst_sql, new Object[] { user.getUsername()}, Integer.class);
		} catch (DataAccessException e) {
			LOGGER.info("Data Not Found");
		}
		LOGGER.info("productId------"+user.getFirstname());
		LOGGER.info("create user count.. "+count);
		
		if(count==0)
		{
			userRespository.save(user);
			return "success";
		}
		else
		{
			return "failure";
		}

		

	}

	

	public String createSocialUser(User user) {
		LOGGER.info("create Social User");
		System.out.println("Socialname::" + user.getUsername());
		
		
			String prod_inst_sql = "select count(*) as total from USER_DETAILS where USERNAME=? and LOGIN_TYPE=2";
			int count = 0;
			try {
			//	System.out.println(jdbcTemplate.queryForObject(prod_inst_sql, new Object[] { user.getUsername()}, Integer.class));
				count = jdbcTemplate.queryForObject(prod_inst_sql, new Object[] { user.getUsername()}, Integer.class);
			} catch (DataAccessException e) {
				LOGGER.info("Data Not Found");
			}
			LOGGER.info("productId------"+user.getFirstname());
			LOGGER.info("create Social User count.. "+count);
			
			if(count==0)
			{
				userRespository.save(user);
				return "success";
			}
			else
			{
				return "failure";
			}
		
	}
	
	
	


//	Login Validation
	
	public Map<String, Object> checkLogin(User user) {
		LOGGER.info("checkLogin enter");
		System.out.println("name::" + user.getUsername());
		//userRespository.save(user);

	
		LOGGER.info("productId------"+user.getPassword());
		Map<String, Object> userList = null;
		String prod_inst_sql = "select count(*) as total from USER_DETAILS where USERNAME=? and PASSWORD=? ";
		int count = 0;
		try {
			count = jdbcTemplate.queryForObject(prod_inst_sql, new Object[] { user.getUsername(), user.getPassword() }, Integer.class);
		} catch (DataAccessException e) {
			LOGGER.info("Data Not Found");
		}
		LOGGER.info("getCount exit::" + count);
		
	
		LOGGER.info("checkLogin exit");
		
		if(count==1)			
		{	
			String retrieveUserQuery = "select id \"id\", firstname \"firstname\", lastname \"lastname\", username \"username\", password \"password\", created_date \"created_date\", login_type \"login_type\" from USER_DETAILS where USERNAME=? and PASSWORD=?";			
			userList = jdbcTemplate.queryForMap(retrieveUserQuery, new Object[] {  user.getUsername(),  user.getPassword() });						
			System.out.println("userDao userlist"+userList);
			return userList;
		}else {
			System.out.println("userDao userlist"+userList);
			return userList;
		}
		
		
		
		//String total=Integer.toString(count);
		
	//	return total;
		
		}

	
	
	
//	Get Video Details
	public List<Videos> getVideos(Integer id) {
		LOGGER.info("checkLogin enter.."+id );
//		System.out.println("name::" + user.getUsername());

		List<Videos> addressList = new ArrayList<>();		//String prod_inst_sql = "select YOUTUBE_LINK from  POST_VIDEOS where USER_ID=103";
	//	int userid=103;
		try {
			
		String addressSql = "select  * from POST_VIDEOS where USER_ID=103";
			addressList.addAll((List<Videos>) jdbcTemplate.query(addressSql, (rs, rowNum) -> {
				Videos poll = new Videos();
				poll.setYoutube_link(rs.getString("YOUTUBE_LINK"));
				poll.setUser_id(rs.getInt("USER_ID"));
				poll.setId(rs.getLong("ID"));

		
				return poll;
			}, new Object[] {  }));

								
		} catch (DataAccessException e) {
			LOGGER.info("Data Not Found"+e);
			
		}
		return addressList;
		
		}
	
	
	
	
	

	
	public List<Map<String, Object>> getComments(String youtube_link) {
		LOGGER.info("getOrderSummary enter::" + youtube_link);
		String prod_inst_sql = "select name,comments from comment_section where youtube_link='"+youtube_link+"'";
		List<Map<String, Object>> result = null;
		try {
			result = jdbcTemplate.queryForList(prod_inst_sql, new Object[] {});
		} catch (DataAccessException e) {
			LOGGER.info("Data Not Found");
			e.printStackTrace();
		}
		LOGGER.info("getOrderSummary exit::" + result);
		return result;
	}
	
	
	
	public List<Map<String, Object>> getCommentCount(String youtube_link) {
		LOGGER.info("getOrderSummary enter::" + youtube_link);
		String prod_inst_sql = "select count(*) from comment_section where youtube_link='"+youtube_link+"'";
		List<Map<String, Object>> result = null;
		try {
			result = jdbcTemplate.queryForList(prod_inst_sql, new Object[] {});
		} catch (DataAccessException e) {
			LOGGER.info("Data Not Found");
			e.printStackTrace();
		}
		LOGGER.info("getOrderSummary exit::" + result);
		return result;
	}
	
	
	
//	Add New Comments
	
	public String createComment(Comments comment) {
		LOGGER.info("create Social User");
		System.out.println("Socialname::" + comment.getName());
		/*String prod_inst_sql = "insert into COMMENT_SECTION (name,comments,youtube_link) VALUES('"+comment.getName()+"','"+comment.getComments()+"','"+comment.getYoutube_link()+"')";
		
		jdbcTemplate.queryForList(prod_inst_sql, new Object[] {});

		
	*/	
		
		
		commentRespository.save(comment);
		return "success";
	
	
	
	
	}
	
	
	
	
	
	
	
}
	
	

