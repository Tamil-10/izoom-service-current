package com.izoom.izoomservice.poll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.izoom.izoomservice.poll.pojo.PollContactAssoc;
import com.izoom.izoomservice.poll.pojo.PollVoteVO;

public interface PollAnswerRespository extends JpaRepository<PollVoteVO, PollContactAssoc> {

}
