package front;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SubastaClientHandleImpl implements SubastaClientHandle{

	public SubastaClientHandleImpl() throws RemoteException {
		UnicastRemoteObject.exportObject(this, 0);
	}

	@Override
	public void notificarOfertaSuperada(String tituloSubasta, int montoGanador)
			throws RemoteException {
		System.out.println("Oferta superada, subasta: "+tituloSubasta+ " monto: "+ montoGanador);		
	}

	@Override
	public void notificarOfertaAceptada(String tituloSubasta)
			throws RemoteException {
		System.out.println("Oferta aceptada, subasta: "+tituloSubasta);
	}

	@Override
	public void notificarOfertaRechazada(String tituloSubasta)
			throws RemoteException {
		System.out.println("Oferta rechazada, subasta: "+tituloSubasta);
	}

	@Override
	public void notificarSubastaGanada(Subasta subasta) throws RemoteException {
		System.out.println("Subasta ganada!, subasta:"+subasta.getTitulo());
	}

	@Override
	public void notificarError(String tituloSubasta, int codigoError,
			String mensaje) throws RemoteException {
		System.out.println("Error en subasta: "+tituloSubasta+" con codigo y mensaje: "+codigoError+mensaje);
	}

}
