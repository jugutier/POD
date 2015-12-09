package front;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientHandle extends Remote
{	
	public void newInformation(String aName) throws RemoteException;
}
