package test;

import java.io.IOException; 
import java.net.MalformedURLException; 
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import back.MovieService;
import front.ClientHandleImpl;
import utils.Analyzer;

 
public class Client 
{
	public static void main(String[] args) throws NotBoundException, IOException 
	{
		
		Analyzer auxi= new Analyzer(args);
		Object port = auxi.get("PORT");
		Object hostname = auxi.get("HOSTNAME");
		Object service = auxi.get("SERVICE");
		auxi.dump();
        
	    MovieService handle= (MovieService) Naming.lookup(String.format("//%s:%s/%s", hostname, port, service ) );
	
	    
	    handle.suscribe(new ClientHandleImpl(), (String) auxi.get("GENRE" ) );
	   

		
	}






}	