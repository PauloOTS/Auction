package com.auction.interfaces;

import com.auction.exceptions.AuctionException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import com.auction.models.*;

public interface AuctionServerInterface extends Remote{
	public static String REFERENCE_NAME = "AuctionServer";

	ArrayList<Auction> listAuctions()
		throws	RemoteException,
			AuctionException;

	void initializeAuction(AuctionClientInterface c, Auction a)
		throws	RemoteException,
			AuctionException;

	void newBid(AuctionClientInterface c, Bid b)
		throws	RemoteException,
			AuctionException;

	void finishAuction(int id, Auction a)
		throws	RemoteException,
			AuctionException;
}
