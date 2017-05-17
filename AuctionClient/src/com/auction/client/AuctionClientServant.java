/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auction.client;

import com.auction.exceptions.AuctionException;
import com.auction.interfaces.AuctionClientInterface;
import com.auction.interfaces.AuctionServerInterface;
import com.auction.models.Auction;
import com.auction.models.Bid;
import com.auction.models.User;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import com.auction.views.ClientView;
import javax.swing.JOptionPane;

/**
 *
 * @author Paulo
 */
public class AuctionClientServant extends UnicastRemoteObject implements AuctionClientInterface{

    // View of the client
    final private ClientView view;
    // Reference to the server
    final private AuctionServerInterface server;
    // Information about the client
    private User clientInfo;

   
    /**
     * @brief AuctionClient Servant Constructor
     * @param server reference to server
     * @throws RemoteException 
     */
    AuctionClientServant(AuctionServerInterface server) throws RemoteException{
        this.server = server;
        this.view = new ClientView(this);
        this.view.setVisible(true);
    }
    
    /**
     * @brief AuctionClosedNotification implemented
     * @param a Auction that finished
     * @throws RemoteException 
     */
    @Override
    public void auctionClosedNotification(Auction a) throws RemoteException {

        // New dialog to notify the client
        Thread t = new Thread(() -> {
            if (a.getHighest_bid().getUser() == null)
                JOptionPane.showMessageDialog(this.view, "Auction " +
                    a.getId() + " closed.\n\nThere were no bids for this auction");
            else
                JOptionPane.showMessageDialog(this.view, "Auction " +
                    a.getId() + " closed.\n\n" +
		    "Product: " + a.getProduct().getDesc() + "\n\n" +
		    "Winner is: " +
                    a.getHighest_bid().getUser().getName()
                    + "\n\nWinner Value: "+
                    a.getHighest_bid().getValue());
        });

        t.start();
        
        // Request the list of auctions again
	try {
		this.view.setAuctions(this.server.listAuctions());
	} catch (RemoteException ex) {
                this.errorNotification(ex);
	}
    }

     /**
     * @brief AuctionBidNotification implemented
     * @param b New Bid
     * @throws RemoteException 
     */
    @Override
    public void auctionBidNotification(Bid b) throws RemoteException {
        
        //Dialog to notify the user
        Thread t = new Thread(() -> {
            JOptionPane.showMessageDialog(this.view, "New bid at Auction " + 
                                      b.getAuction_id() + "\n\nUser is: " 
                                      + b.getUser().getName()
                                      + "\n\nValue is: "+ b.getValue());
        });

        t.start();
        
        //Request the list of auctions again
	    try {
		    this.view.setAuctions(this.server.listAuctions());
	    } catch (RemoteException ex) {       
                    this.errorNotification(ex);    

	    }
    }
    
     public User getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(User clientInfo) {
        this.clientInfo = clientInfo;
    }

    public ClientView getView() {
        return view;
    }

    public AuctionServerInterface getServer() {
        return server;
    }

    /**
     * @brief Function To show an error String message
     * @param error
     */
    public void errorNotification(String error){
        
        Thread t = new Thread(() -> {
            JOptionPane.showMessageDialog(this.view, "Error: " + error);    
        });
        t.start();
            
    }
    
    /**
     * @brief Function that shows an error based on a RemoteException / AuctionException
     * @param ex exception to be notified 
     */
    public void errorNotification(RemoteException ex){
        if(ex != null && ex.getCause() != null
               && ex.getMessage().contains("Auction")){
                
                AuctionException e = (AuctionException) ex.getCause();
                String dialog_msg =     "Error in auction: \n" + 
                    ((e.getAuction() == null)?"\n":e.getAuction().toString()) +
                    e.getMessage();
                this.errorNotification(dialog_msg);
            }
    }

     /**
     * @brief AuctionClient setID implemented
     * @param id UserID
     * @throws RemoteException 
     */
    @Override
    public void setID(int id) throws RemoteException {
        this.clientInfo.setId(id);
    }
}
