package back;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

import front.SubastaClientHandle;

public interface SubastaService extends Remote {

	Set<String> listarSubastas() throws RemoteException;

	void ofertar(String tituloSubasta, int oferta,
			SubastaClientHandle clientHandler) throws RemoteException;

}
