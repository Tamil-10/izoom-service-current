package com.izoom.izoomservice.admin.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.izoom.izoomservice.admin.pojo.Admin;


public interface AdminRepository extends JpaRepository<Admin,Integer> {
	
//	List<Admin> findByStatus(String status);

}


