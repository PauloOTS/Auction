/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auction.control;

import com.auction.interfaces.AuctionClientInterface;
import com.auction.models.Auction;
import com.auction.models.Bid;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/** This class implements all the manipulations of the models on the server.
 * The implementation of this class aims to abstract the data manipulation
 * to provide easy interchange between server operations and the actual data.
 *
 * @author yudi
 */
public class AuctionDB {
	final private ArrayList<Auction> auctions;
	final private TreeMap<
		Auction, ArrayList<AuctionClientInterface>> subscribers;

	final private TreeMap<
		Integer, Auction> auc_by_id;
	final private AtomicInteger auc_id;

	public AuctionDB()
	{
		auctions = new ArrayList<>();
		auc_id = new AtomicInteger(0);
		subscribers = new TreeMap<>();
		auc_by_id = new TreeMap<>();
	}

	/**
	 * @return Return the list of the current active auctions
	 */
	public ArrayList<Auction> getAuctions()
	{
		return auctions;
	}

	private void addSubscriber(AuctionClientInterface c, Auction a)
	{
		ArrayList<AuctionClientInterface> s = subscribers.get(a);

		if(s == null){
			s = new ArrayList<>();
			subscribers.put(a, s);
		}

		s.add(c);
	}

	public void inicializeAuction(AuctionClientInterface c, Auction a)
	{
		a.setId(auc_id.get());
		auc_by_id.put(auc_id.get(), a);
		auc_id.addAndGet(1);

		auctions.add(a);

		addSubscriber(c, a);
	}

	/** Create a new Bid in the database
	 *
	 * @param c Reference to the client
	 * @param b The new bid
	 * @return true if the new bid is the new highest bid
	 * and false otherwise
	 */
	public boolean newBid(AuctionClientInterface c, Bid b)
	{
		Auction a = auc_by_id.get(b.getAuction_id());
		Bid highest = a.getHighest_bid();

		if(b.getValue() <= highest.getValue())
			return false;

		a.setHighest_bid(b);
		addSubscriber(c, a);
		return true;
	}

	public ArrayList<AuctionClientInterface> finishAuction(Auction a)
	{
		ArrayList<AuctionClientInterface> l = subscribers.get(a);

		if(l != null)
			subscribers.remove(a);

		return l;
	}
}
