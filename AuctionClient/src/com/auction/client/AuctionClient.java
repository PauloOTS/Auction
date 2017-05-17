/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auction.client;

import com.auction.interfaces.AuctionServerInterface;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Paulo
 */
public class AuctionClient {
    
    /**
     * Main function of the client
     * @param args 
     * @throws java.rmi.RemoteException 
     */
    
    public static void main (String[] args) throws RemoteException, NotBoundException{
        
        Registry registryRef = LocateRegistry.getRegistry();
        
        AuctionServerInterface server = (AuctionServerInterface)
                registryRef.lookup(AuctionServerInterface.REFERENCE_NAME);

        AuctionClientServant c = new AuctionClientServant(server);
    }
}
