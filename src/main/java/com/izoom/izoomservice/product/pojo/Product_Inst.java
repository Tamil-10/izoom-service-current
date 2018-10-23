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
@Table(name = "PRODUCT_INST")
public class Product_Inst {
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_inst_id_seq")
	@SequenceGenerator(name = "product_inst_id_seq", sequenceName = "PRODUCT_INST_SEQ")
	private Long id;
	@Column(name = "USER_ID")
	private Long user_id;
	@Column(name = "PRODUCT_ID")
	private Long product_id;
	@Column(name = "QUANTITY")
	private Long quantity;
	@Column(name = "PRICE")
	private Long price;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "ADDRESS_ID")
	private Long address_id;
	@Column(name = "ORDER_ID")
	private Long order_id;
	@Column(name = "CREATED_DATE")
	private Date created_date;
	@Column(name = "CREATED_BY")
	private String created_by;
	@Column(name = "MODIFIED_DATE")
	private Date modified_date;
	@Column(name = "MODIFIED_BY")
	private String modified_by;
	@Column(name = "ORDERED_DATE")
	private Date ordered_date;

	public Date getOrdered_date() {
		return ordered_date;
	}

	public void setOrdered_date(Date ordered_date) {
		this.ordered_date = ordered_date;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the user_id
	 */
	public Long getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id
	 *            the user_id to set
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the product_id
	 */
	public Long getProduct_id() {
		return product_id;
	}

	/**
	 * @param product_id
	 *            the product_id to set
	 */
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	/**
	 * @return the quantity
	 */
	public Long getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the price
	 */
	public Long getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Long price) {
		this.price = price;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the address_id
	 */
	public Long getAddress_id() {
		return address_id;
	}

	/**
	 * @param address_id
	 *            the address_id to set
	 */
	public void setAddress_id(Long address_id) {
		this.address_id = address_id;
	}

	/**
	 * @return the order_id
	 */
	public Long getOrder_id() {
		return order_id;
	}

	/**
	 * @param order_id
	 *            the order_id to set
	 */
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	/**
	 * @return the created_date
	 */
	public Date getCreated_date() {
		return created_date;
	}

	/**
	 * @param created_date
	 *            the created_date to set
	 */
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	/**
	 * @return the created_by
	 */
	public String getCreated_by() {
		return created_by;
	}

	/**
	 * @param created_by
	 *            the created_by to set
	 */
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	/**
	 * @return the modified_date
	 */
	public Date getModified_date() {
		return modified_date;
	}

	/**
	 * @param modified_date
	 *            the modified_date to set
	 */
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}

	/**
	 * @return the modified_by
	 */
	public String getModified_by() {
		return modified_by;
	}

	/**
	 * @param modified_by
	 *            the modified_by to set
	 */
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "product_id="+this.product_id+"::"+"status="+this.status;
	}
}
