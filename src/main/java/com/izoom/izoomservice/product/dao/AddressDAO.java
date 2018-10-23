package com.izoom.izoomservice.product.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.izoom.izoomservice.poll.pojo.PollVO;
import com.izoom.izoomservice.product.pojo.Address;
import com.izoom.izoomservice.product.repository.AddressRespository;
import com.izoom.izoomservice.user.pojo.User;
import com.izoom.izoomservice.user.repository.UserRespositary;

@Repository
public class AddressDAO {
	public static Logger LOGGER = LoggerFactory.getLogger(AddressDAO.class);
	@Autowired
	AddressRespository addressRespository;

	@Autowired
	JdbcTemplate jdbcTemplate;


	public String createAddress(Address address) {
		LOGGER.info("createAddress enter");
		
		addressRespository.save(address);
		LOGGER.info("createAddress exit");
	return "success";
		

	}
	


	public List<Address> retrieveShippingAddress(String userId) {
		LOGGER.info("retrieveShippingAddress enter::");
		
		LOGGER.info("userId value----------"+userId);								
		List<Address> addressList = new ArrayList<>();
		try {
			String addressSql = "select * from ADDRESS where userId=?";
			addressList.addAll((List<Address>) jdbcTemplate.query(addressSql, (rs, rowNum) -> {
				Address poll = new Address();
				poll.setId(rs.getLong("ID"));
				poll.setName(rs.getString("NAME"));
				poll.setAddress(rs.getString("ADDRESS"));
				poll.setContactnumber(rs.getLong("CONTACTNUMBER"));
		
				return poll;
			}, new Object[] { userId }));

			addressList.forEach(poll -> System.out.println(poll.getName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("retrieveShippingAddress exit::" + addressList);
		return addressList;
	}	
	public void deleteAddress(long id) throws Exception {
		LOGGER.info("deleteAddress enter");
		try {
			LOGGER.info("id::::" + id);
			
			jdbcTemplate.update("delete from ADDRESS where id = ?", new Object[] {  id });
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("product id:::" + id);
		LOGGER.info("deleteAddress exit");
		
	}
	
}
