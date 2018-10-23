package com.izoom.izoomservice.poll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.izoom.izoomservice.poll.pojo.PollGroupAssoc;
import com.izoom.izoomservice.poll.pojo.PollGroupVO;
public interface PollGroupRespository extends JpaRepository<PollGroupVO,PollGroupAssoc>  {
}
