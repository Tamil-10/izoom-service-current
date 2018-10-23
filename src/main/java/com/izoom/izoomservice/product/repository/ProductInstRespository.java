package com.izoom.izoomservice.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.izoom.izoomservice.product.pojo.Product_Inst;
public interface ProductInstRespository extends JpaRepository<Product_Inst,Long>  {

}
