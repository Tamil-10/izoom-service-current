package com.izoom.izoomservice.product.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.izoom.izoomservice.product.dao.AddressDAO;
import com.izoom.izoomservice.product.pojo.Address;

@RestController
@RequestMapping(value = "/api/address")
public class AddressService {
	private static Logger LOGGER = LoggerFactory.getLogger(AddressService.class);
	
	@Autowired
	AddressDAO addressDAO;
	
	

//	For Registering New User
	
	@RequestMapping(method = RequestMethod.POST, value = "createAddress")
	public String createAddress(@RequestParam(value = "userId") String userId,@RequestParam(value = "name") String name,@RequestParam(value = "address") String address,@RequestParam(value = "contactnumber") Long contactnumber) throws Exception {
	System.out.println("jsaf----");
		LOGGER.info("createAddress enter");
		LOGGER.info("createAddress enter"+name+address+contactnumber);
		Address address1 = new Address();
		address1.setUserId(userId);
		address1.setCreated_date(new Date());
		address1.setName(name);
		address1.setAddress(address);
		address1.setContactnumber(contactnumber);
		
		addressDAO.createAddress(address1);
		
		LOGGER.info("createAddress exit");
		//userDAO.createUser(user);

			
	
		return "success";

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "retrieveShippingAddress/{userId}")
	public  List<Address>  retrieveShippingAddress(@PathVariable(value = "userId") String userId) throws Exception {
		LOGGER.info("retrieveShippingAddress enter");
		LOGGER.info("userId-------------"+userId);
		List<Address> addressList = null;
		try {
			addressList = addressDAO.retrieveShippingAddress(userId);
		} catch (Exception e) {
			System.out.println(e);			
		}
		LOGGER.info("retrieveShippingAddress exit");
		return addressList;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "deleteAddress/{id}")
	public void deleteAddress(@PathVariable(value = "id") long id) throws Exception {
		LOGGER.info("deleteAddress enter");
		
		try {
			LOGGER.info("id------"+id);
			 addressDAO.deleteAddress(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("deleteAddress exit");
		
	
	}
		
	
	
	
}
