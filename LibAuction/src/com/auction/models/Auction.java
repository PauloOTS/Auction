package com.auction.models;

public class Auction {

	private int id;
	private User auctioneer;
	private Product product;
	private Bid highest_bid;
	
	/**
	 * @brief Auction class constructor
	 * @param id Id of the auction
	 * @param auctioneer The auctioneer user
	 * @param product The product in the auction
	 * @param highest_bid Bid of the current winner
	 **/
	
	Auction(int id, User auctioneer, Product product, Bid highest_bid){
		this.id = id;
		this.auctioneer = auctioneer;
		this.product = product;
		this.highest_bid = highest_bid;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public User getAuctioneer() {
		return auctioneer;
	}
	public void setAuctioneer(User auctioneer) {
		this.auctioneer = auctioneer;
	}
	public Bid getHighest_bid() {
		return highest_bid;
	}
	public void setHighest_bid(Bid highest_bid) {
		this.highest_bid = highest_bid;
	}	

}
