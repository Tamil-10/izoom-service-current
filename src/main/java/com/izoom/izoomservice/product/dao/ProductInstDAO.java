
package com.izoom.izoomservice.product.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.izoom.izoomservice.product.pojo.Product_Inst;

import com.izoom.izoomservice.product.repository.ProductInstRespository;

@Repository
public class ProductInstDAO {

	private static Logger LOGGER = LoggerFactory.getLogger(ProductInstDAO.class);

	@Autowired
	ProductInstRespository productInstRespository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Product_Inst addProduct(Product_Inst product_Inst) {
		LOGGER.info("addProduct enter::" + product_Inst);
		System.out.println("getProduct_id::" + product_Inst.getProduct_id());
		System.out.println("getUser_id::  getUser_id::" + product_Inst.getUser_id());
		String prod_inst_sql = "select * from product_inst where product_id=? and status=? and user_id=?";
		List<Product_Inst> _prod_Inst_list = ((List<Product_Inst>) jdbcTemplate.query(prod_inst_sql, (rs, rowNum) -> {
			Product_Inst tempProduct_Inst = new Product_Inst();
			tempProduct_Inst.setId(rs.getLong("ID"));
			tempProduct_Inst.setQuantity(rs.getLong("QUANTITY"));
			return tempProduct_Inst;
		}, new Object[] { product_Inst.getProduct_id(), "Draft", product_Inst.getUser_id() }));

		if (_prod_Inst_list != null && !_prod_Inst_list.isEmpty()) {
			product_Inst.setQuantity(_prod_Inst_list.get(0).getQuantity() + product_Inst.getQuantity());
			product_Inst.setId(_prod_Inst_list.get(0).getId());
			LOGGER.info("_prod_Inst_prod_Inst_prod_Inst:::" + _prod_Inst_list.get(0));
		}
		productInstRespository.save(product_Inst);
		LOGGER.info("addProduct exit::" + product_Inst);

		return product_Inst;
	}

	public int getCartCount(String userId) {
		LOGGER.info("getCartCount enter::" + userId);
		String prod_inst_sql = "select sum(quantity)QTY from product_inst where user_id=? and status=?";
		int count = 0;
		try {
			count = jdbcTemplate.queryForObject(prod_inst_sql, new Object[] { userId, "Draft" }, Integer.class);
			System.out.println("dfsd-----"+count);
		} catch (DataAccessException | NullPointerException  e) {
			LOGGER.info("Data Not Found");
		}
		LOGGER.info("getCartCount exit::" + count);
		return count;
	}
//	   public int getCartCount(String userId) {
//	        LOGGER.info("getCartCount enter::" + userId);
//	        String prod_inst_sql = "select count(*) as total from product_inst where quantity=? and status=?;";
//	        int count = 0;
//	        try {
//	            count = jdbcTemplate.queryForObject(prod_inst_sql, new Object[] { "2", "Draft" }, Integer.class);
//	            System.out.println("dfsd-----"+count);
//	        } catch (DataAccessException e) {
//	            LOGGER.info("Data Not Found");
//	        }
//	        LOGGER.info("getCartCount exit::" + count);
//	        return count;
//	    }
	public List<Map<String, Object>> getOrderSummary(String userId) {
		LOGGER.info("getOrderSummary enter::" + userId);
		String prod_inst_sql = "select pi.id \"id\", pi.product_id \"product_id\", pi.quantity \"quantity\" , p.name \"name\",p.description \"description\", p.price \"price\",p.content \"content\",p.content_type \"content_type\" from product_inst pi, product p where pi.user_id=? and pi.status=? and pi.product_id= p.id";
		List<Map<String, Object>> result = null;
		try {
			result = jdbcTemplate.queryForList(prod_inst_sql, new Object[] { userId, "Draft" });
		} catch (DataAccessException e) {
			LOGGER.info("Data Not Found");
			e.printStackTrace();
		}
		LOGGER.info("getOrderSummary exit::" + result);
		return result;
	}
	public void updateCart(long product_id, long quantity, long userId) throws Exception {
		LOGGER.info("updateCart enter");
		try {
			LOGGER.info("product_id::::" + product_id);
			LOGGER.info("quantity::::" + quantity);
			LOGGER.info("quantity::::" + userId);
			
			
			jdbcTemplate.update("update PRODUCT_INST set quantity = ? where product_id=? and user_id=? and status='Draft'", new Object[] { quantity, product_id, userId });
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("product id:::" + product_id);
		LOGGER.info("updateCart exit");
		
	}
	public void deleteCartItem(long product_id, long userId) throws Exception {
		LOGGER.info("deleteCartItem enter");
		try {
			LOGGER.info("product_id::::" + product_id);
			LOGGER.info("product_id::::" + userId);
			
			jdbcTemplate.update("delete from PRODUCT_INST where product_id = ? and user_id=? and status='Draft'", new Object[] {  product_id, userId });
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("product id:::" + product_id);
		LOGGER.info("deleteCartItem exit");
		
	}
	
	public Product_Inst updateProduct(Product_Inst product_Inst) {
		LOGGER.info("updateProduct enter::" + product_Inst);
		System.out.println("getAddress_id::" + product_Inst.getAddress_id());
		System.out.println("getUser_id::" + product_Inst.getUser_id());
	  	int id = jdbcTemplate.queryForObject("select ORDER_SEQ.nextval from dual", Integer.class);
	  	LOGGER.info("ordered date ::" + product_Inst.getOrdered_date());
		jdbcTemplate.update("update PRODUCT_INST set status=?, address_id=?, order_id=?, ordered_date=?  where user_id=? and status='Draft' ", new Object[] { product_Inst.getStatus(), product_Inst.getAddress_id(), id,product_Inst.getOrdered_date(), product_Inst.getUser_id() });
		
		LOGGER.info("updateProduct exit::" + product_Inst);

		return product_Inst;
	}
	
	public List<Map<String, Object>> retrieveOrders(String userId) {
		LOGGER.info("retrieveOrders enter::" + userId);
		String prod_inst_sql = "select pi.id \"id\", pi.product_id \"product_id\", pi.quantity \"quantity\", pi.status \"status\", p.name \"name\",p.description \"description\", p.price \"price\",p.content \"content\",p.content_type \"content_type\" from product_inst pi, product p where pi.user_id=? and pi.status=? and pi.product_id= p.id";
		List<Map<String, Object>> result = null;
		try {
			result = jdbcTemplate.queryForList(prod_inst_sql, new Object[] { userId, "Ordered" });
		} catch (DataAccessException e) {
			LOGGER.info("Data Not Found");
			e.printStackTrace();
		}
		LOGGER.info("retrieveOrders exit::" + result);
		return result;
	}
	


}
