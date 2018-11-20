package com.izoom.izoomservice.product.dao;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.izoom.izoomservice.admin.pojo.Admin;
import com.izoom.izoomservice.product.pojo.Product;
import com.izoom.izoomservice.product.pojo.SearchCriteria;
import com.izoom.izoomservice.product.repository.ProductRespository;


@Repository
public class ProductDAO {

	private static Logger LOGGER = LoggerFactory.getLogger(ProductDAO.class);

	@Autowired
	ProductRespository productRespository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Value("${productpagelimit}")
	String limit;

	public String productconfiguration(Product product) {
		LOGGER.info("productconfiguration enter");
		System.out.println("name::" + product.getName());
		productRespository.save(product);
		try {
			String[] proTypeId = product.getType().split(",[ ]*");			
		    for(int i=0; i < proTypeId.length;i++){
		    	LOGGER.info("type id---------"+proTypeId[i]);
		    	int id = jdbcTemplate.queryForObject("select PRO_TYPE_SEQ.nextval from dual", Integer.class);
				LOGGER.info("id---------"+id);
				jdbcTemplate.update("INSERT INTO PRO_TYPE_ASC (ID, PRO_ID, PRO_TYPE_ID) VALUES(?, ?, ?)", new Object[] {id, product.getId(), proTypeId[i] });						      
		    }		  
		    
		    String[] genTypeId = product.getGender_type().split(",[ ]*");
		    for(int i=0; i < genTypeId.length; i++) {
		    	LOGGER.info("gender id------"+genTypeId[i]);
		    	int id = jdbcTemplate.queryForObject("select GEN_TYPE_SEQ.nextval from dual", Integer.class);
		    	LOGGER.info("seq id--------"+id);
		    	jdbcTemplate.update("INSERT INTO GEN_TYPE_ASC (ID, PRO_ID, GEN_TYPE_ID) VALUES(?, ?, ?)", new Object[] {id, product.getId(), genTypeId[i] });
		    }

		}
		catch (Exception e) {
			System.out.println(e);
		}

	
		LOGGER.info("productId------"+product.getId());
		LOGGER.info("productconfiguration exit");
		return "success";
	}

	public List<Product> retrieveProductList(SearchCriteria searchCriteria) {
		LOGGER.info("retrieveProductList enter");
		List<Product> productList = null;
		try {
		//	String getFilter = searchCriteria.getFilter();
			
			int lmt = searchCriteria.getLimit() < 1 ? 5 : searchCriteria.getLimit();
			String sortBy = searchCriteria.getSortBy();
			int start = searchCriteria.getStart();
			Sort sort = new Sort(new Sort.Order(Direction.ASC, sortBy != null ? sortBy : "id"));
			Pageable pageable = new PageRequest(start, lmt, sort);
			productList = new ArrayList<>();
			productRespository.findByStatus("Active").forEach(productList::add);
			productList.forEach(product -> System.out.println(product.getName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("retrieveProductList exit");
		return productList;
	}
	public List<Map<String, Object>> retrieveProductsByFilter(String filterId, int count) {
		LOGGER.info("retrieveProductsByFilter enter::");
		/*Integer getFilter = Integer.parseInt(searchCriteria.getFilter());*/
		LOGGER.info("filter value----------"+filterId);		
		
		if(count==1) {
		String retrieveProductsByFilterQuery = "select p.id \"id\", p.name \"name\", p.price \"price\", p.available_quantity \"available_quantity\", p.description \"description\", p.content \"content\", p.content_url \"content_url\", p.content_type \"content_type\", p.status \"status\", pta.pro_type_id \"pro_type_id\" from  pro_type_asc pta , product p where p.id = pta.pro_id and pta.pro_type_id=? and p.status=?";
		List<Map<String, Object>> productList = null;
		try {
			productList = jdbcTemplate.queryForList(retrieveProductsByFilterQuery, new Object[] {filterId, "Active" });			
		} catch (Exception e) {
			System.out.println(e);
			
		}
		LOGGER.info("retrieveProductsByFilter exit::"+productList);
		return productList;
		}
		else {
			String retrieveProductsByFilterQuery = "select p.id \"id\", p.name \"name\", p.price \"price\", p.available_quantity \"available_quantity\", p.description \"description\", p.content \"content\", p.content_url \"content_url\", p.content_type \"content_type\", p.status \"status\", gta.gen_type_id \"gen_type_id\", pta.pro_type_id \"pro_type_id\" from gen_type_asc gta, pro_type_asc pta , product p where p.id = gta.pro_id and p.id = pta.pro_id and pta.pro_type_id=? and p.status=?";
			List<Map<String, Object>> productList = null;
			try {
				productList = jdbcTemplate.queryForList(retrieveProductsByFilterQuery, new Object[] {filterId, "Active" });			
			} catch (Exception e) {
				System.out.println(e);
				
			}
			LOGGER.info("retrieveProductsByFilter exit::"+productList);
			return productList;
		}
		
		
		
	}		

	public Product delete(Product product) throws Exception {
		LOGGER.info("delete enter");
		try {
			LOGGER.info("poll_id::::" + product.getId());
			jdbcTemplate.update("update PRODUCT set status = 'Deleted' where id=?", new Object[] { product.getId() });
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("poll id:::" + product.getId());
		LOGGER.info("delete exit");
		return product;
	}
	
	
	public List<Map<String, Object>> contentList(Integer productId) {
		LOGGER.info("getOrderSummary enter::" + productId);
		String prod_inst_sql = "select content \"content\",content_type \"content_type\",price \"price\" from product where id="+productId;
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
	
	
	
	
	
	
/*	public List<Product> contentList(Integer productId) {
		LOGGER.info("checkLogin enter");
		
		return jdbcTemplate.query("select content,content_type from product where id="+productId,new RowMapper<Product>(){  
		    @Override  
		    public Product mapRow(ResultSet rs, int rownumber) throws SQLException {  
		    	Product e=new Product();  
		        e.setContent_type(rs.getString(2));  
		        e.setContent(((ResultSet) rs).getByte(1));  
		         
		        return e;  
		    }  
		    });  

	
	
	}*/
	

}
