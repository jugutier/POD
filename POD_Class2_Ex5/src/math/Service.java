package math;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {

    public double getMediana(int[] elements) throws RemoteException;

    public int[] getModa(int[] elements) throws RemoteException;

    public double getPromedio(int[] elements) throws RemoteException;

}
