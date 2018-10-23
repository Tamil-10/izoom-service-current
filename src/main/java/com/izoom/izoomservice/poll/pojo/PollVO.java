package com.izoom.izoomservice.poll.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "POLL")
public class PollVO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poll_id_seq")
	@SequenceGenerator(name = "poll_id_seq", sequenceName = "POLL_SEQ")
	private Long id;
	@Column(name = "USER_ID")
	private Long user_id;
	@Column(name = "QUESTION")
	private String question;
	@Column(name = "ANSWER1")
	private String answer1;
	@Column(name = "ANSWER2")
	private String answer2;
	@Column(name = "ANSWER3")
	private String answer3;
	@Column(name = "ANSWER4")
	private String answer4;
	@Column(name = "PUBLISH_TIME_LIMIT")
	private Long publish_time_limit;
	@Column(name = "PUBLISH_START_TIME")
	private Date publish_start_time;
	@Column(name = "PUBLISH_END_TIME")
	private Date publish_end_time;
	@Column(name = "CREATED_BY")
	private String created_by;
	@Column(name = "CREATED_DATE")
	private Date created_date;
	@Column(name = "MODIFIED_BY")
	private String modified_by;
	@Column(name = "MODIFIED_DATE")
	private Date modified_date;
	@Column(name = "STATUS")
	private String status;
	@Transient
	private String user_name;
	@Transient
	List<String> groupId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getAnswer4() {
		return answer4;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	public Long getPublish_time_limit() {
		return publish_time_limit;
	}

	public void setPublish_time_limit(Long publish_time_limit) {
		this.publish_time_limit = publish_time_limit;
	}

	public Date getPublish_start_time() {
		return publish_start_time;
	}

	public void setPublish_start_time(Date publish_start_time) {
		this.publish_start_time = publish_start_time;
	}

	public Date getPublish_end_time() {
		return publish_end_time;
	}

	public void setPublish_end_time(Date publish_end_time) {
		this.publish_end_time = publish_end_time;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public List<String> getGroupId() {
		return groupId;
	}

	public void setGroupId(List<String> groupId) {
		this.groupId = groupId;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


}
