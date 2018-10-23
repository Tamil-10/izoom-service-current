package com.izoom.izoomservice.admin.pojo;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "ADMIN")
public class Admin {

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_id_seq")
	@SequenceGenerator(name = "admin_id_seq", sequenceName = "ADMIN_SEQ")
	private Long id;


	@Column(name = "USERNAME")
	private String username;	
	
	@Column(name = "NAME")
	private String name;	
	
	
	@Column(name = "PASSWORD")
	private String password;
	

	
	@Column(name = "FIRSTNAME")
	private String firstname;
	
	@Column(name = "YOUTUBE")
	private String youtube;
	
	@Column(name = "USERID")
	private Number userid;
	

//	@Column(name = "STATUS")
//	private String status;
//	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
