package com.izoom.izoomservice.user.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.izoom.izoomservice.user.pojo.User;


public interface UserRespositary extends JpaRepository<User,Integer>  {
	//List<User> findByLogin_type(int login_type);
}
