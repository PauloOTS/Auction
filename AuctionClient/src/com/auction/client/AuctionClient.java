/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auction.client;

import java.rmi.RemoteException;

/**
 *
 * @author abacate
 */
public class AuctionClient {
    
    /**
     * Main function of the client
     * @param args 
     * @throws java.rmi.RemoteException 
     */
    
    public static void main (String[] args) throws RemoteException{
        AuctionClientServant c = new AuctionClientServant();
    }
}
