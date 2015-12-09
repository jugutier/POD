package back;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import front.Oferta;
import front.Subasta;
import front.SubastaClientHandle;

public class SubastaServiceImpl implements SubastaService{
	private Map<String,Subasta> subastas;//key:title
	public SubastaServiceImpl() throws RemoteException {

		UnicastRemoteObject.exportObject(this, 0);
	}
	@Override
	public Set<String> listarSubastas() throws RemoteException {
		Set<String> ret = new HashSet<String>();
		for (Entry<String,Subasta> currentEntry : subastas.entrySet()) {
			Subasta subasta = currentEntry.getValue();
			if(subasta.isOpen()){
				ret.add(subasta.getTitulo());
			}
		}
		return ret;
	}

	@Override
	public void ofertar(String tituloSubasta, int oferta,
			SubastaClientHandle clientHandler) throws RemoteException {
		Subasta s = subastas.get(tituloSubasta);
		if(s==null || !s.isOpen()){//subasta no existe o esta cerrada
			clientHandler.notificarError(tituloSubasta, 1, "Subasta Cerrada o inexistente");
			return;
		}
		Oferta nuevaOferta = new Oferta(oferta, clientHandler);
		Oferta.Estado e = s.ofertar(nuevaOferta);
		
		switch (e) {
		case ACEPTADA:
			nuevaOferta.getHandle().notificarOfertaSuperada(tituloSubasta, oferta);
			break;
		case GANADADORA:
			
			break;
		case RECHAZADA:
			
			break;
		case ERROR:
			
			break;
		}
		
		Oferta mejorOferta = s.getMejorOferta();	
		if(oferta > s.getValorEsperado()){ //primer oferta que supera valor esperado gana
			if(mejorOferta!=null){ //si habia alguien que oferto le avisamos que perdio.
				mejorOferta.getHandle().notificarOfertaSuperada(tituloSubasta, oferta);
			}
			s.close();
			clientHandler.notificarSubastaGanada(s);
			return;
		}
		if(oferta > s.getMinimoEsperado()){
			if(mejorOferta!=null){
				if(mejorOferta.getMonto()>oferta){//si no supera una oferta existente es rechazada
					clientHandler.notificarOfertaRechazada(tituloSubasta);
					return;
				}//si la supera le aviso al anterior que perdio
				mejorOferta.getHandle().notificarOfertaSuperada(tituloSubasta, oferta);				
			}//finalmente me guardo como mejor oferta (pero no ganadora porque no supere valor esperado)
			s.setMejorOferta(new Oferta(oferta, clientHandler));
			clientHandler.notificarOfertaAceptada(tituloSubasta);
			return;
		}		

	}

}
