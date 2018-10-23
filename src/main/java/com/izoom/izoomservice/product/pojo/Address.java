
package com.izoom.izoomservice.product.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS")
public class Address {
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_seq")
	@SequenceGenerator(name = "address_id_seq", sequenceName = "ADDRESS_SEQ")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "CONTACTNUMBER")
	private Long contactnumber;

	@Column(name = "CREATED_DATE")
	private Date created_date;

	@Column(name = "USERID")
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreated_date() {
		return created_date;
	}
		
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(Long contactnumber) {
		this.contactnumber = contactnumber;
	}



}