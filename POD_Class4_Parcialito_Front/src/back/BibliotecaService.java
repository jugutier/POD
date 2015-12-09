package back;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface BibliotecaService extends Remote {
  Libro prestarLibro(String isbn) throws RemoteException;
  void devolverLibro(Libro libro) throws RemoteException;
  ArrayList<String> listarCatalogo() throws RemoteException;
}