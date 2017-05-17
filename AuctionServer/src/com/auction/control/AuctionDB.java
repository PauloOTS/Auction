/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auction.control;

import com.auction.interfaces.AuctionClientInterface;
import com.auction.exceptions.AuctionException;
import com.auction.models.Auction;
import com.auction.models.Bid;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
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
        final private AtomicInteger client_id;

	public AuctionDB()
	{
		auctions = new ArrayList<>();
		auc_id = new AtomicInteger(0);
                client_id = new AtomicInteger(0);
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

	/**
	  * adds a subscriber `c` to an Auction `a`
	  */
	private void addSubscriber(AuctionClientInterface c, Auction a)
	{
		ArrayList<AuctionClientInterface> s = subscribers.get(a);

		if(s == null){
			s = new ArrayList<>();
			subscribers.put(a, s);
		}

		s.add(c);
	}

	/** Inicialize an Auction `a` and subscribe the client `c` in 
	 * in the auction
	 *
	 * @param c reference to the client
	 * @param a the Auction made by `c`
	 * @throws java.rmi.RemoteException
	  */
	public void inicializeAuction(AuctionClientInterface c, Auction a)
		throws RemoteException
	{
		a.setId(auc_id.get());
		auc_by_id.put(auc_id.get(), a);
		auc_id.addAndGet(1);
                
                if (a.getAuctioneer().getId() == -1){
                    c.setID(client_id.get());
		    a.getAuctioneer().setId(client_id.get());
                    client_id.addAndGet(1);
                }
                
		auctions.add(a);

		addSubscriber(c, a);
	}

	/** Create a new Bid in the database
	 *
	 * @param c Reference to the client
	 * @param b The new bid
	 * 
	 * @throws java.rmi.RemoteException
	 * @throws com.auction.exceptions.AuctionException
	 */
	public void newBid(AuctionClientInterface c, Bid b)
		throws	RemoteException,
			AuctionException
	{
		synchronized(this){
			Auction a = auc_by_id.get(b.getAuction_id());

			if(a == null){
				throw new AuctionException(
					"Auction finished or does not exist",
					null);
			}

			Bid highest = a.getHighest_bid();

			if (b.getUser().getId() == -1){
			    c.setID(client_id.get());
			    b.getUser().setId(client_id.get());
			    client_id.addAndGet(1);
			}
			
			System.out.println("bid user id: " + b.getUser().getId());
			System.out.println("auctioneer: " + a.getAuctioneer().getId());
			if(b.getValue() <= highest.getValue()){
				throw new AuctionException(
					"The value of bid must be greater than the " +
					"current highest bid.",
					a);
			}else if(b.getUser().getId() == a.getAuctioneer().getId()){
				throw new AuctionException(
					"The auctioneer and the bid owner must be " +
					"diferent users!",
					a
				);
			}

			a.setHighest_bid(b);
			addSubscriber(c, a);

		}
	}

	public Auction getAuction(int id)
	{
		return auc_by_id.get(id);
	}

	public ArrayList<AuctionClientInterface> getSubscribers(Auction a)
	{
		return subscribers.get(a);
	}

	/**
	 * Removes the Auction from the list of auctions available
	 * @param a 
	 */
        private void removeAuction(Auction a)
        {
            Iterator<Auction> it = auctions.iterator();
	    synchronized(this){
		    auc_by_id.remove(a.getId());
		    while (it.hasNext()) {
			if (it.next().getId() == a.getId()){
			   it.remove();
			   break;
			}
		    }
	    }
        }
        

	/** When timeout removes `a` from the list if the `a` whas not
	 * finished.
	 * 
	 * @param a
	 * @return 
	 */
	public ArrayList<AuctionClientInterface> auctionTimeout(Auction a)
	{
		ArrayList<AuctionClientInterface> l = subscribers.get(a);

		if(l != null){
			subscribers.remove(a);
			removeAuction(a);
		}

		return l;
	}

	/**
	 * Finishes the Auction `a` and check if the client that made the auction
	 * `a` is equal to the client who send the finishAuction request.
	 * @param id ID from the client
	 * @param a
	 * @return A list to the client references who subscribe to this auction
	 * @throws AuctionException 
	 */
	public ArrayList<AuctionClientInterface> finishAuction(int id, Auction a)
		throws AuctionException
	{
            
                if (id != a.getAuctioneer().getId()){
                    throw new AuctionException(
			    "You don't have prmission to close Auction", a);
                }
                
		ArrayList<AuctionClientInterface> l = subscribers.get(a);

		if(l != null){
			subscribers.remove(a);
			removeAuction(a);
		}else{
			throw new AuctionException(
				"The auction doest not exists or " +
				"it's already over!", a);
		}

		return l;
	}
}
