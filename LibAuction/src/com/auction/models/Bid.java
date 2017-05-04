package com.auction.models;

public class Bid {

	private int id;
	private int auction_id;
	private double value;
	private User user;
	
	/**
	 * @brief Bid constructor
	 * @param id Id of the Bid
	 * @param auction_id Id of the Auction related to the bid
	 * @param value Value of the bid
	 * @param user User related the bid
	 */
	
	Bid(int id, int auction_id, double value, User user){
		this.id = id;
		this.auction_id = auction_id;
		this.value = value;
		this.user = user;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAuction_id() {
		return auction_id;
	}
	public void setAuction_id(int auction_id) {
		this.auction_id = auction_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User u) {
		this.user = u;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
}
