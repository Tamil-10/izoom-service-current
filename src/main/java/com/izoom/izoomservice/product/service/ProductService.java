package com.izoom.izoomservice.product.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PathVariable;

import com.izoom.izoomservice.admin.pojo.Admin;
import com.izoom.izoomservice.product.dao.ProductDAO;
import com.izoom.izoomservice.product.pojo.Product;
import com.izoom.izoomservice.product.pojo.SearchCriteria;
import com.izoom.izoomservice.user.pojo.User;
import com.izoom.izoomservice.util.IZoomUtils;

@RestController
@RequestMapping(value = "/api/product")
public class ProductService {
	private static Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
	@Autowired
	ProductDAO productDAO;

	@Value("${server.productimage.url}")
	String content_url;

	@Value("${productimageurl}")
	String productimageurl;

	@Value("${productdata}")
	String productdata;
	

	@RequestMapping(method = RequestMethod.POST, value = "productConfiguration")
	public String productConfiguration(@RequestParam("file") MultipartFile file, @RequestParam("type") String type,
			 @RequestParam("gender_type") String gender_type,
			@RequestParam("name") String name, @RequestParam("description") String description,
			@RequestParam("available_quantity") String available_quantity, @RequestParam("price") String price, @RequestParam("status") String status) {
		LOGGER.info("productconfiguration enter");
		Product product = new Product();
		product.setFile(file);
		product.setCreated_date(new Date());
		product.setContent_type(product.getFile().getContentType());
		product.setAvailable_quantity(Integer.parseInt(available_quantity.trim()));
		product.setDescription(description);
		product.setName(name);
		product.setPrice(Double.parseDouble(price.trim()));
		product.setType(type);
		product.setGender_type(gender_type);
		LOGGER.info("gender_type---------------"+gender_type);
		LOGGER.info("type---------------"+type);
		System.out.println("type---------------"+type);
		product.setStatus(status);

		if (productdata.equalsIgnoreCase("1")) {
			try {
				byte[] targetArray = new byte[product.getFile().getInputStream().available()];
				product.getFile().getInputStream().read(targetArray);
				product.setContent(targetArray);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (productimageurl.equalsIgnoreCase("1")) {
			product.setContent_url(createFile(product.getName(), product.getFile()));
		}
		productDAO.productconfiguration(product);
		
		LOGGER.info("productconfiguration exit");
		return "success";
	}

	@RequestMapping(method = RequestMethod.POST, value = "retrieveProductList")
	public List<Product> retrieveProductList(@RequestBody SearchCriteria searchCriteria) {
		
		LOGGER.info("retrieveProductList enter");
		//SearchCriteria searchCriteria = new SearchCriteria();
		List<Product> productList = productDAO.retrieveProductList(searchCriteria);
		if (productdata.equalsIgnoreCase("0") && productimageurl.equalsIgnoreCase("1")) {
			productList.forEach(prod -> {
				prod.setContent(readFile(prod.getContent_url()));
			});
		}
		LOGGER.info("retrieveProductList exit");
		return productList;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "retrieveProductsByFilter/{filterId}")
	public List<Map<String, Object>> retrieveProductsByFilter(@PathVariable(value = "filterId") String filterId) throws Exception {
		LOGGER.info("retrieveProductsByFilter enter");
		LOGGER.info("filterId enter"+filterId);
		List<Map<String, Object>> productList = null;
		try {
			productList = productDAO.retrieveProductsByFilter(filterId);
		} catch (Exception e) {
			System.out.println(e);			
		}
		LOGGER.info("retrieveProductsByFilter exit");
		return productList;
	}
		

	@RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
	public Product deleteProduct(@RequestBody Product product) throws Exception {
		LOGGER.info("deleteProduct enter");
		Product result = null;
		try {
			result = productDAO.delete(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("deleteProduct exit");
		return result;
	}

	private String createFile(String productName, MultipartFile productImage) {
		String imagePath = content_url + "\\" + productName.trim() + "_" + System.currentTimeMillis() + "."
				+ IZoomUtils.getImageType(productImage.getContentType());
		new File(content_url).mkdir();
		File targetFile = new File(imagePath);
		try (OutputStream outStream = new FileOutputStream(targetFile)) {
			outStream.write(productImage.getBytes());
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imagePath;
	}
		
	private byte[] readFile(String content_url) {
		LOGGER.info("readFile enter::" + content_url);
		File targetFile = new File(content_url);
		try (InputStream targetStream = new FileInputStream(targetFile)) {
			LOGGER.info("readFile exit");
			return StreamUtils.copyToByteArray(targetStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "getContentType/{productId}")
	public List<Map<String, Object>> getContentType(@PathVariable(value = "productId") Integer productId) throws Exception {
		LOGGER.info("getOrderSummary enter");
		List<Map<String, Object>> result = null;
		try {
			result = productDAO.contentList(productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("getOrderSummary exit");
		return result;
	}
	
	
	
	
	
	
	
/*	@RequestMapping(method = RequestMethod.POST, value = "create")
	public void create(@RequestParam(value = "username") String username,@RequestParam(value = "firstname") String firstname,@RequestParam(value = "lastname") String lastname,@RequestParam(value = "password") String password) throws Exception {
	System.out.println("jsaf----");
		LOGGER.info("deleteProduct enter");
		Product product = new Product();
		product.setCreated_date(new Date());
		product.setUsername(username);
		product.setFirstname(firstname);
		product.setLastname(lastname);
		product.setPassword(password);
		LOGGER.info("deleteProduct enter"+username+firstname+lastname+password);
	}*/

	


}
