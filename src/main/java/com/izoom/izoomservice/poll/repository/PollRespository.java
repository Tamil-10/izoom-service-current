package com.izoom.izoomservice.poll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.izoom.izoomservice.poll.pojo.PollVO;
public interface PollRespository extends JpaRepository<PollVO,Long>  {
}
