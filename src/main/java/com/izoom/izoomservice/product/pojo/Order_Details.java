/*

package com.izoom.izoomservice.product.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_DETAILS")
public class Order_Details {
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
	@SequenceGenerator(name = "order_id_seq", sequenceName = "ORDER_SEQ")
	private Long id;


	@Column(name = "ORDERED_DATE")
	private Date ordered_date;

	



}*/