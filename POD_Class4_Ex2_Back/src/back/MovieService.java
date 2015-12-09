package back;

import java.rmi.Remote;
import java.rmi.RemoteException;

import front.ClientHandle;

public interface MovieService extends Remote
{	
	public void suscribe( ClientHandle  aHandle,  String aGenre) throws RemoteException;
}
