package com.izoom.izoomservice.user.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USER_DETAILS")
public class User {
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
	@SequenceGenerator(name = "user_id_seq", sequenceName = "USER_SEQ")
	private Long id;


	@Column(name = "USERNAME")
	private String username;	
	
	@Column(name = "FIRSTNAME")
	private String firstname;	
	
	@Column(name = "LASTNAME")
	private String lastname;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "CREATED_DATE")
	private Date created_date;

	@Column(name = "LOGIN_TYPE")
	private Integer login_type;

	@Column(name = "YOUTUBE")
	private String youtube;
	
	@Column(name = "USERID")
	private Number userid;
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLogin_type() {
		return login_type;
	}

	public void setLogin_type(Integer login_type) {
		this.login_type = login_type;
	}
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	
	public String getYoutube() {
		return youtube;
	}

	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

	public Number getUserid() {
		return userid;
	}

	public void setUserid(Number userid) {
		this.userid = userid;
	}

	


}