package test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import utils.Analyzer;
import data.Service;
import eda.ColoredNode;
import eda.Graph;

 
public class Client 
{
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException 
	{
		Analyzer auxi= new Analyzer(args);
		Object port = auxi.get("PORT");
		Object hostname = auxi.get("HOSTNAME");
		Object service = auxi.get("SERVICE");
		auxi.dump();
        
		System.out.println("URL: "+String.format("//%s:%s/%s", hostname, port, service ));
	    Service handle= (Service) Naming.lookup(String.format("//%s:%s/%s", hostname, port, service ) );
	
	    Graph rta = handle.populate("AR");
		System.out.println(rta);
		
		rta.addNode(new ColoredNode("W", "Rojo"));
		rta= handle.populate(rta); System.out.println(rta);
		
	}






}	