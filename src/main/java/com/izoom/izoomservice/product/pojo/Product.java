package com.izoom.izoomservice.product.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "PRODUCT")
public class Product {
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
	@SequenceGenerator(name = "product_id_seq", sequenceName = "PRODUCT_SEQ")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "GENDER_TYPE")
	private String 	gender_type;
	
	@Column(name = "AVAILABLE_QUANTITY")
	private Integer available_quantity;

	@Column(name = "PRICE")
	private Double price;

	@Column(name = "CREATED_DATE")
	private Date created_date;

	@Lob
	@Column(name = "CONTENT", length = 100000)
	private byte[] content;

	@Column(name = "CONTENT_URL")
	private String content_url;

	@Column(name = "CONTENT_TYPE")
	private String content_type;
	
	@Column(name = "STATUS")
	private String status;
	
	
	@Transient
	private MultipartFile file;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getGender_type() {
		return gender_type;
	}

	public void setGender_type(String gender_type) {
		this.gender_type = gender_type;
	}


	public Integer getAvailable_quantity() {
		return available_quantity;
	}

	public void setAvailable_quantity(Integer available_quantity) {
		this.available_quantity = available_quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getContent_url() {
		return content_url;
	}

	public void setContent_url(String content_url) {
		this.content_url = content_url;
	}

	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	/**
	 * @return the file
	 */
	public MultipartFile getFile() {
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}