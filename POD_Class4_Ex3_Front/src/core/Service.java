package core;
import java.rmi.*;

public interface Service extends Remote
{
	public void connect(CallBack client) throws RemoteException;
	
	public void disconnect(CallBack client) throws RemoteException;

	/** A client invokes this method to notify the server of its move*/
	public void myMove(int row, int column, char token) throws RemoteException;
}
