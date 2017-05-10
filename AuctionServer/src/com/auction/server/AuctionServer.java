/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auction.server;

import com.auction.interfaces.AuctionServerInterface;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yudi
 */
public class AuctionServer {
	public static void main(String args[])
	{

		try {
			Registry registryReference =
				LocateRegistry.createRegistry(1099);

			AuctionServerServant serverReference =
				new AuctionServerServant();

			registryReference.bind(
				AuctionServerInterface.REFERENCE_NAME,
				serverReference);

		} catch (RemoteException | AlreadyBoundException ex) {
			Logger.getLogger(AuctionServer.class.getName())
				.log(Level.SEVERE, null, ex);
		}
	}
}
