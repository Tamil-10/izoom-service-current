package com.izoom.izoomservice.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.izoom.izoomservice.product.pojo.Product;
public interface ProductRespository extends JpaRepository<Product,Integer>  {
	List<Product> findByStatus(String status);
}
