package com.izoom.izoomservice.admin.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.izoom.izoomservice.admin.dao.AdminDAO;
import com.izoom.izoomservice.admin.pojo.Admin;
import com.izoom.izoomservice.admin.repository.AdminRepository;
import com.izoom.izoomservice.user.pojo.User;

@Repository
public class AdminDAO {
	public static Logger LOGGER = LoggerFactory.getLogger(AdminDAO.class);
	@Autowired
	AdminRepository adminRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	
	
	
	
	
	
	public Map<String, Object> checkLogin(Admin admin) {
		LOGGER.info("checkLogin enter");
		System.out.println("name::" + admin.getUsername());
		//userRespository.save(user);

	
		LOGGER.info("productId------"+admin.getPassword());
		Map<String, Object> userList = null;
		String prod_inst_sql = "select count(*) as total from ADMIN where USERNAME=? and PASSWORD=? ";
		int count = 0;
		try {
			count = jdbcTemplate.queryForObject(prod_inst_sql, new Object[] { admin.getUsername(), admin.getPassword() }, Integer.class);
		} catch (DataAccessException e) {
			LOGGER.info("Data Not Found");
		}
		LOGGER.info("getCount exit::" + count);
		
	
		LOGGER.info("checkLogin exit");
		
		if(count==1)			
		{	
			String retrieveUserQuery = "select id \"id\", username \"username\", password \"password\" from ADMIN where USERNAME=? and PASSWORD=?";			
			userList = jdbcTemplate.queryForMap(retrieveUserQuery, new Object[] {  admin.getUsername(),  admin.getPassword() });						
			System.out.println("userDao userlist"+userList);
			return userList;
		}else {
			System.out.println("userDao userlist"+userList);
			return userList;
		}
		
		
		
		//String total=Integer.toString(count);
		
	//	return total;
		
		}

	
	
	
	public List<Admin> userList() {
		LOGGER.info("checkLogin enter");
		
		return jdbcTemplate.query("select id,firstname from user_details",new RowMapper<Admin>(){  
		    @Override  
		    public Admin mapRow(ResultSet rs, int rownumber) throws SQLException {  
		        Admin e=new Admin();  
		        e.setId((long) rs.getInt(1));  
		        e.setName(rs.getString(2));  
		         
		        return e;  
		    }  
		    });  

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		}





	
	
	
	
	


}
