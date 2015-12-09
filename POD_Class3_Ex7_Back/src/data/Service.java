package data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import eda.Graph;

public interface Service extends Remote 
{
	 public Graph  populate(String country) throws RemoteException;
	 public Graph  populate(Graph aG) throws RemoteException;
	 //... muchos mas
} 
