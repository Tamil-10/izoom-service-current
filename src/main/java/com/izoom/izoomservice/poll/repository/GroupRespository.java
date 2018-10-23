package com.izoom.izoomservice.poll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.izoom.izoomservice.poll.pojo.GroupVO;
public interface GroupRespository extends JpaRepository<GroupVO,Integer>  {

}
