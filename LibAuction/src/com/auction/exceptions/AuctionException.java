/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auction.exceptions;

import com.auction.models.Auction;

/** Auction exception class; represents a error with an auction operation.
 * This class holds a reference of the auction in which the error ocurred.
 * @author yudi
 */
public class AuctionException extends Exception {

	private final Auction auction;

	public AuctionException(String msg, Auction auction){
		super(msg);
		this.auction = auction;
	}

	public Auction getAuction() {
		return auction;
	}
}
