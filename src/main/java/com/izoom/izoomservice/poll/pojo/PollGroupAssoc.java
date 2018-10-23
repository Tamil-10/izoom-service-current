package com.izoom.izoomservice.poll.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PollGroupAssoc implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "POLL_ID")
	private Long poll_id;
	@Column(name = "GROUP_ID")
	private Long group_id;
	

	public Long getPoll_id() {
		return poll_id;
	}

	public void setPoll_id(Long poll_id) {
		this.poll_id = poll_id;
	}

	public Long getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Long group_id) {
		this.group_id = group_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((group_id == null) ? 0 : group_id.hashCode());
		result = prime * result + ((poll_id == null) ? 0 : poll_id.hashCode());
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
		if (!(obj instanceof PollGroupAssoc)) {
			return false;
		}
		PollGroupAssoc other = (PollGroupAssoc) obj;
		if (group_id == null) {
			if (other.group_id != null) {
				return false;
			}
		} else if (!group_id.equals(other.group_id)) {
			return false;
		}
		if (poll_id == null) {
			if (other.poll_id != null) {
				return false;
			}
		} else if (!poll_id.equals(other.poll_id)) {
			return false;
		}
		return true;
	}


}
