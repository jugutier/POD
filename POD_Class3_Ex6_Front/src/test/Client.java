package test;

import java.net.MalformedURLException; 
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import data.Service;
import eda.Graph;
import utils.Analyzer;

 
public class Client 
{
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException 
	{
		Analyzer auxi= new Analyzer(args);
		Object port = auxi.get("PORT");
		Object hostname = auxi.get("HOSTNAME");
		Object service = auxi.get("SERVICE");
		auxi.dump();
        
	    Service handle= (Service) Naming.lookup(String.format("//%s:%s/%s", hostname, port, service ) );
	
	    Graph rta = handle.populate("AR");
		System.out.println(rta);
		
	}






}	