package com.izoom.izoomservice.product.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name="PRODUCT_TYPE")
public class ProductType {
	@Id
	@Column(name = "PRO_TYPE_ID")
	private Long pro_type_id;

	@Column(name = "TYPE")
	private String type;

	public Long getPro_type_id() {
		return pro_type_id;
	}

	public void setPro_type_id(Long pro_type_id) {
		this.pro_type_id = pro_type_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
