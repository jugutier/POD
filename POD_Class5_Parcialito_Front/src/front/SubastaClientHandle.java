package front;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SubastaClientHandle extends Remote {

	void notificarOfertaSuperada(String tituloSubasta, int montoGanador)
			throws RemoteException;

	void notificarOfertaAceptada(String tituloSubasta) throws RemoteException;

	void notificarOfertaRechazada(String tituloSubasta) throws RemoteException;

	void notificarSubastaGanada(Subasta subasta) throws RemoteException;

	void notificarError(String tituloSubasta, int codigoError, String mensaje)
			throws RemoteException;
}
