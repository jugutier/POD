package remotetest;
import java.rmi.*;   
import java.net.MalformedURLException;

import core.Service;
import utils.Analyzer;


public class Client 
{

 //private JLabel jlblIdentification = new JLabel();
  public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException 
  {
		Analyzer auxi= new Analyzer(args);
		Object port = auxi.get("PORT");
		Object hostname = auxi.get("HOSTNAME");
		Object service = auxi.get("SERVICE");
		auxi.dump();
	
	    Service handle= (Service) Naming.lookup(String.format("//%s:%s/%s", hostname, port, service ) );

 	 	FrontEnd myApp = new FrontEnd(handle);
 	 	handle.connect(myApp);
 	  	    
  }
}
