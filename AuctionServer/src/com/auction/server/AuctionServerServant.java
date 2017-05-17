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
import java.util.Timer;
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

		Timer t = new Timer();
		t.schedule(	new AuctionTimeout(this, a),
				a.getDuration_sec()*1000);

	}

	@Override
	public synchronized void newBid(AuctionClientInterface c, Bid b)
	throws	RemoteException,
		AuctionException
	{
		System.out.println("newBid");
		db.newBid(c, b);

		ArrayList<AuctionClientInterface> l = db.getSubscribers(
					db.getAuction(b.getAuction_id()));

		if(l != null){
			for(AuctionClientInterface cref : l)
				cref.auctionBidNotification(b);
		}
	}

	public void auctionTimeout(Auction a)
	{
		System.out.println("auctionTimeout");
		ArrayList<AuctionClientInterface> l = db.auctionTimeout(a);

		if(l != null){
			try {
				for(AuctionClientInterface c: l){
					c.auctionClosedNotification(a);
				}
			} catch (RemoteException ex) { 
				Logger.getLogger(
					AuctionServerServant.class.getName())
					.log(Level.SEVERE, null, ex);
			}
		}
	}

	@Override
	public void finishAuction(int id, Auction a)
	throws	RemoteException,
		AuctionException
	{
		System.out.println("finishAuction");
		ArrayList<AuctionClientInterface> l = db.finishAuction(id, a);

		try {
			for(AuctionClientInterface c: l){
				c.auctionClosedNotification(a);
			}
		} catch (RemoteException ex) {
			Logger.getLogger(
				AuctionServerServant.class.getName())
				.log(Level.SEVERE, null, ex);
		}
	}
}
