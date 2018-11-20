package com.izoom.izoomservice.user.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.izoom.izoomservice.user.pojo.Comments;

public interface CommentRespositary extends JpaRepository<Comments,Integer>  {
	//List<User> findByLogin_type(int login_type);
}