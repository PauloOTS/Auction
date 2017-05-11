/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auction.server;

import com.auction.control.AuctionDB;
import com.auction.interfaces.AuctionClientInterface;
import com.auction.exceptions.AuctionException;
import java.rmi.server.UnicastRemoteObject;
import com.auction.interfaces.AuctionServerInterface;
import com.auction.models.Auction;
import com.auction.models.Bid;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yudi
 */
public class	AuctionServerServant 
		extends UnicastRemoteObject
		implements AuctionServerInterface {

	private final AuctionDB db;

	public AuctionServerServant()
	throws RemoteException
	{
		super();

		db = new AuctionDB();
	}

	@Override
	public ArrayList<Auction> listAuctions()
	throws	RemoteException,
		AuctionException
	{
		System.out.println("listAuctions");
		return db.getAuctions();
	}

	@Override
	public void initializeAuction(AuctionClientInterface c, Auction a)
	throws	RemoteException,
		AuctionException
	{
		System.out.println("initializeAuction");

		if(a.getHighest_bid().getValue() <= 0.0){
			throw new AuctionException(
				"The initial value must be greater than zero!",
				a);
		}

		db.inicializeAuction(c, a);
	}

	@Override
	public synchronized void newBid(AuctionClientInterface c, Bid b)
	throws	RemoteException,
		AuctionException
	{
		System.out.println("newBid");
		db.newBid(c, b);

		c.auctionBidNotification(b);
	}

	@Override
	public void finishAuction(Auction a)
	throws	RemoteException,
		AuctionException
	{
		System.out.println("finishAuction");
		ArrayList<AuctionClientInterface> l = db.finishAuction(a);

		try {
			for(AuctionClientInterface c: l){
				c.auctionBidNotification(a.getHighest_bid());
			}
		} catch (RemoteException ex) {
			Logger.getLogger(
				AuctionServerServant.class.getName())
				.log(Level.SEVERE, null, ex);
		}
	}
}
