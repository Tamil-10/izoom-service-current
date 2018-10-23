package com.izoom.izoomservice.product.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.izoom.izoomservice.product.pojo.Address;

public interface AddressRespository extends JpaRepository<Address,Integer>  {
	
}
