package com.izoom.izoomservice.product.pojo;

import java.io.Serializable;

public class SearchCriteria implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private int[] type;
	private String description;
	private String created_date;
	private String sortBy;
	private int start;
	private int limit;
	private String filter;
	

	

	public int[] getType() {
		return type;
	}

	public void setType(int[] type) {
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the created_date
	 */
	public String getCreated_date() {
		return created_date;
	}

	/**
	 * @param created_date
	 *            the created_date to set
	 */
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	/**
	 * @return the sortBy
	 */
	public String getSortBy() {
		return sortBy;
	}

	/**
	 * @param sortBy
	 *            the sortBy to set
	 */
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @param limit
	 *            the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

}
