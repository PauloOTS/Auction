package com.auction.models;

public class Product {

	private int id;
	private String desc;
	/**
	 * @brief Product constructor
	 * @param id Id of the product
	 * @param desc Description of the product
	 */
	Product(int id, String desc){
		this.id = id;
		this.desc = desc;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
