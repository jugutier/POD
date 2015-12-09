package front;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientHandleImpl implements ClientHandle
{
	public ClientHandleImpl() throws RemoteException
	{
		UnicastRemoteObject.exportObject(this, 0);
	}
	
	public void newInformation(String aName) throws RemoteException 
	{
		System.out.println(String.format("Nuevo estreno %s", aName));		
	}

}
