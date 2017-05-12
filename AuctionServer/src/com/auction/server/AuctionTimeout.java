/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auction.server;

import com.auction.models.Auction;
import java.util.TimerTask;

/**
 *
 * @author yudi
 */
public class AuctionTimeout extends TimerTask{

	final private Auction auction;
	final private AuctionServerServant server;

	public AuctionTimeout(AuctionServerServant server, Auction auction){
		this.server = server;
		this.auction = auction;
	}

	@Override
	public void run() {
		System.out.println("AuctionTimeout!");
		server.auctionTimeout(auction);
	}
}
