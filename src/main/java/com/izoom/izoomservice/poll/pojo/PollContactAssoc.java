package com.izoom.izoomservice.poll.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PollContactAssoc implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "POLL_ID")
	private Long poll_id;
	@Column(name = "RESPONDER_ID")
	private Long responser_id;

	public Long getPoll_id() {
		return poll_id;
	}

	public void setPoll_id(Long poll_id) {
		this.poll_id = poll_id;
	}

	public Long getResponser_id() {
		return responser_id;
	}

	public void setResponser_id(Long responser_id) {
		this.responser_id = responser_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((poll_id == null) ? 0 : poll_id.hashCode());
		result = prime * result + ((responser_id == null) ? 0 : responser_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PollContactAssoc)) {
			return false;
		}
		PollContactAssoc other = (PollContactAssoc) obj;
		if (poll_id == null) {
			if (other.poll_id != null) {
				return false;
			}
		} else if (!poll_id.equals(other.poll_id)) {
			return false;
		}
		if (responser_id == null) {
			if (other.responser_id != null) {
				return false;
			}
		} else if (!responser_id.equals(other.responser_id)) {
			return false;
		}
		return true;
	}
}
