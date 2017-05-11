/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auction.exceptions;

import com.auction.models.Auction;
import java.rmi.RemoteException;

/** Auction exception class; represents a error with an auction operation.
 * This class holds a reference of the auction in which the error ocurred.
 */
public class AuctionException extends RemoteException{

	private final Auction auction;

	public AuctionException(String msg, Auction auction){
		super(msg);
		this.auction = auction;
		System.out.println("Exceptions created with message: " + msg);
	}

	public Auction getAuction() {
		return auction;
	}
}
