package com.auction.interfaces;
import com.auction.models.*;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface AuctionClientInterface extends Remote{

        /**
         * @brief Client interface function to show an auction closed Notification Pop-Up
         * @param a Auction that finished
         * @throws RemoteException 
         */
	void auctionClosedNotification(Auction a) throws RemoteException;
        /**
         * @brief Client Interface function to show a new bid notification Pop-Up
         * @param b The new bid
         * @throws RemoteException 
         */
	void auctionBidNotification(Bid b) throws RemoteException;
        
        /**
         * @brief Client Interface function to set the userID given by the server
         * @param id Client ID
         * @throws RemoteException 
         */
        void setID(int id) throws RemoteException;
}
