package com.izoom.izoomservice.user.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "POST_VIDEOS")
public class Videos {
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "video_id_seq")
	@SequenceGenerator(name = "video_id_seq", sequenceName = "VIDEO_SEQ")

	private Long id;


		
	
	@Column(name = "YOUTUBE_LINK")
	private String youtube_link;	
	
	@Column(name = "NAME")
	private String name;	
	
	@Column(name = "COMMENTS")
	private String comments;	
	
		
	@Column(name = "USER_ID")
	private Number user_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getYoutube_link() {
		return youtube_link;
	}

	public void setYoutube_link(String youtube_link) {
		this.youtube_link = youtube_link;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
	
	public Number getUser_id() {
		return user_id;
	}

	public void setUser_id(Number user_id) {
		this.user_id = user_id;
	}

	
}
