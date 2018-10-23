package com.izoom.izoomservice.poll.pojo;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "POLL_GROUP_ASSOC")
public class PollGroupVO implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private PollGroupAssoc pollGroupAssoc;

	public PollGroupAssoc getPollGroupAssoc() {
		return pollGroupAssoc;
	}

	public void setPollGroupAssoc(PollGroupAssoc pollGroupAssoc) {
		this.pollGroupAssoc = pollGroupAssoc;
	}

}
