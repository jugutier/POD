package back;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class BibliotecaServiceImpl implements BibliotecaService{

	private final HashMap<String, LibroBiblioteca> stock = new HashMap<String, LibroBiblioteca>();//key: ISBN

	public BibliotecaServiceImpl(List<String[]> libros) throws RemoteException {
		UnicastRemoteObject.exportObject(this, 0);
		for (String[] libroData : libros) {
			LibroBiblioteca lb = new LibroBiblioteca(libroData);
			stock.put(lb.getLibro().getISBN(), lb);
		}
		
	}
	@Override
	public Libro prestarLibro(String isbn) throws RemoteException {
		LibroBiblioteca lb = stock.get(isbn);
		if(lb==null){
			throw new IllegalAccessError("Libro no existe.");
		}
		return lb.prestar();
	}

	@Override
	public void devolverLibro(Libro libro) throws RemoteException {
		LibroBiblioteca lb = stock.get(libro.getISBN());
		if(lb == null){
			throw new IllegalStateException("Libro no encontrado.");
		}
		lb.devolver();
		
	}

	@Override
	public ArrayList<String> listarCatalogo() throws RemoteException {
		Set<Entry<String, LibroBiblioteca>> set = stock.entrySet();
		ArrayList<String> ret = new ArrayList<String>();
		for (Entry<String, LibroBiblioteca> entry : set) {
			Libro currentLibro = entry.getValue().getLibro();
			ret.add(currentLibro.toString());
		}
		return ret;
	}

}
