package com.izoom.izoomservice.product.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.izoom.izoomservice.product.dao.ProductInstDAO;
import com.izoom.izoomservice.product.pojo.Product_Inst;

@RestController
@RequestMapping(value = "/api/productinst")
public class ProductInstService {
	private static Logger LOGGER = LoggerFactory.getLogger(ProductInstService.class);
	@Autowired
	ProductInstDAO productInstDAO;


	@RequestMapping(method = RequestMethod.POST, value = "addProduct")
	public Product_Inst addProduct(@RequestBody Product_Inst product_Inst) throws Exception {
		LOGGER.info("addProduct enter");
		Product_Inst result = null;
		try {
			product_Inst.setCreated_date(new Date());
			result = productInstDAO.addProduct(product_Inst);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("addProduct exit");
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "getCartCount/{userId}")
	public int getCartCount(@PathVariable(value = "userId") String userId) throws Exception {
		LOGGER.info("getCartCount enter");
		int result = 0;
		try {
			result = productInstDAO.getCartCount(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("getCartCount exit");
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "getOrderSummary/{userId}")
	public List<Map<String, Object>> getOrderSummary(@PathVariable(value = "userId") String userId) throws Exception {
		LOGGER.info("getOrderSummary enter");
		List<Map<String, Object>> result = null;
		try {
			result = productInstDAO.getOrderSummary(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("getOrderSummary exit");
		return result;
	}
	@RequestMapping(method = RequestMethod.POST, value = "updateCart/{product_id}/{quantity}/{userId}")
	public void updateCart(@PathVariable(value = "product_id") long product_id, @PathVariable(value = "quantity") long quantity, @PathVariable(value = "userId") long userId ) throws Exception {
		LOGGER.info("updateCart enter");
		
		try {
			 productInstDAO.updateCart(product_id, quantity, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("updateCart exit");
		
	
	}
	@RequestMapping(method = RequestMethod.POST, value = "deleteCartItem/{product_id}/{userId}")
	public void deleteCartItem(@PathVariable(value = "product_id") long product_id, @PathVariable(value = "userId") long userId) throws Exception {
		LOGGER.info("deleteCartItem enter");
		
		try {
			 productInstDAO.deleteCartItem(product_id,userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("deleteCartItem exit");
		
	
	}

	@RequestMapping(method = RequestMethod.POST, value = "updateProduct")
	public Product_Inst updateProduct(@RequestBody Product_Inst product_Inst) throws Exception {
		LOGGER.info("updateProduct enter");
		Product_Inst result = null;
		try {
			product_Inst.setOrdered_date(new Date());
			result = productInstDAO.updateProduct(product_Inst);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("updateProduct exit");
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "retrieveOrders/{userId}")
	public List<Map<String, Object>> retrieveOrders(@PathVariable(value = "userId") String userId) throws Exception {
		LOGGER.info("retriveOrders enter");
		List<Map<String, Object>> result = null;
		try {
			result = productInstDAO.retrieveOrders(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("retriveOrders exit");
		return result;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "retrieveAdminOrders")
	public List<Map<String, Object>> retrieveAdminOrders() throws Exception {
		LOGGER.info("retriveOrders enter");
		List<Map<String, Object>> result = null;
		try {
			result = productInstDAO.retrieveAdminOrders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("retriveOrders exit");
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "retrieveOrdersById/{orderId}")
	public List<Map<String, Object>> retrieveOrdersById(@PathVariable(value = "orderId") String orderId) throws Exception {
		LOGGER.info("retriveOrders enter");
		List<Map<String, Object>> result = null;
		try {
			result = productInstDAO.retrieveOrdersById(orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("retriveOrders exit");
		return result;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "updateStatus/{orderid}/{status}")
	public String updateStatus(@PathVariable(value = "orderid") List<Long> orderid, @PathVariable(value = "status") String status) throws Exception {
		LOGGER.info("updateStatus enter");
		System.out.println("dsf"+orderid+status);
		try {
			 productInstDAO.updateStatus(orderid, status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("updateStatus exit");
		
		return "String";
	
	}
	
}
