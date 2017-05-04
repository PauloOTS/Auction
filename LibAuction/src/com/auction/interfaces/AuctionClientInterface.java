package com.auction.interfaces;
import java.rmi.Remote;


public interface AuctionClientInterface extends Remote{
	void auctionClosedNotification();
	void auctionBidNotification();
}
