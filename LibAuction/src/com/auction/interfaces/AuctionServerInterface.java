package com.auction.interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import com.auction.models.*;

public interface AuctionServerInterface extends Remote{
	ArrayList<Auction> listAuctions() throws RemoteException;
	void initializeAuction(AuctionClientInterface c, Auction a) throws RemoteException;
	void newBid(AuctionClientInterface c, Bid b) throws RemoteException;
	void finishAuction(Auction a);
}
