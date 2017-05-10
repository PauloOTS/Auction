/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auction.client;

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
 * @author abacate
 */
public class AuctionClientServant extends UnicastRemoteObject implements AuctionClientInterface{

    final private ClientView view;
    final private AuctionServerInterface server;
    private User clientInfo;

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

    AuctionClientServant(AuctionServerInterface server) throws RemoteException{
        this.server = server;
        this.view = new ClientView(this);
        this.view.setVisible(true);
    }
    
    @Override
    public void auctionClosedNotification(Auction a) throws RemoteException {
        JOptionPane.showMessageDialog(null, "Auction " + 
                                      a.getId() + "closed.\n\nWinner is: " + 
                                      a.getHighest_bid().getUser()
                                      + "\n\nWinner Value: "+ 
                                      a.getHighest_bid().getValue());
        
        this.view.setAuctions(this.server.listAuctions());
    }

    @Override
    public void auctionBidNotification(Bid b) throws RemoteException {
        JOptionPane.showMessageDialog(null, "New bid at Auction " + 
                                      b.getAuction_id() + "\n\nUser is: " 
                                      + b.getUser()
                                      + "\n\nValue is: "+ b.getValue());
        this.view.setAuctions(this.server.listAuctions());
    }

    @Override
    public void errorNotification(String error) throws RemoteException {
        JOptionPane.showMessageDialog(null, "Error: " + error);    
    }

    @Override
    public void setID(int id) throws RemoteException {
        this.clientInfo.setId(id);
    }
}
