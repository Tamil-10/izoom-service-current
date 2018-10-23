package com.izoom.izoomservice.poll.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "POLL_ANSWER")
public class PollVoteVO implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private PollContactAssoc pollContactAssoc;
	@Column(name = "ANSWER")
	private String answer;
	@Column(name = "LIKES")
	private String likes;
	@Column(name = "DISLIKES")
	private String dislikes;
	@Column(name = "COMMENTS")
	private String comments;
	@Column(name = "CREATED_BY")
	private String created_by;
	@Column(name = "CREATED_DATE")
	private Date created_date;
	@Column(name = "MODIFIED_BY")
	private String modified_by;
	@Column(name = "MODIFIED_DATE")
	private Date modified_date;
	@Transient
	private String user_id;
	@Transient
	private String user_name;

	public PollContactAssoc getPollContactAssoc() {
		return pollContactAssoc;
	}

	public void setPollContactAssoc(PollContactAssoc pollContactAssoc) {
		this.pollContactAssoc = pollContactAssoc;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public String getModified_by() {
		return modified_by;
	}

	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	public Date getModified_date() {
		return modified_date;
	}

	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public String getDislikes() {
		return dislikes;
	}

	public void setDislikes(String dislikes) {
		this.dislikes = dislikes;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
