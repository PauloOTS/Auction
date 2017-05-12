package com.auction.interfaces;
import com.auction.models.*;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface AuctionClientInterface extends Remote{

	void auctionClosedNotification(Auction a) throws RemoteException;
	void auctionBidNotification(Bid b) throws RemoteException;
        void errorNotification(String error) throws RemoteException;
        void setID(int id) throws RemoteException;
}
