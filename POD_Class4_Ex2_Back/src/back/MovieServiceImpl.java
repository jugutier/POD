package back;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;

import front.ClientHandle;

public class MovieServiceImpl implements MovieService
{
	private List<Movie>  db;

	public MovieServiceImpl() throws RemoteException
	{
		UnicastRemoteObject.exportObject(this, 0);
		db = Arrays.asList( 
				  new Movie("Mr. Holmes", "Drama"),
				  new Movie("Las sillas musicales", "Comedia"),
			      new Movie("Un d�a perfecto", "Drama"),
			      new Movie("Aloha", "Comedia"),
			      new Movie("Anacleto: Agente secreto", "Aventura"),
			      new Movie("�tico sin ascenso", "Drama"),
			      new Movie("Everest", "Aventura"),
			      new Movie("Capit�n Diente de Sable y el tesoro de Lama Rama", "Aventura"),
			      new Movie("Una semana en C�rcega", "Comedia"),
			      new Movie("Eden", "Drama"));
	}

	// esto esta manejado por threads
	public void suscribe(ClientHandle aHandle, String aGenre) throws RemoteException 
	{
		for(int i= 0; i < db.size(); i++)
		{
			// supongamos que las news de movies se producen cada 30 segundos...
			// si la nueva noticia era la que me pidieron, la disparo
			if (db.get(i).getGenre().equalsIgnoreCase(aGenre))
				aHandle.newInformation(db.get(i).toString() );
			
			// espero por proxima noticia
			try 
			{
				Thread.sleep(1000 * 10);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		
	}

}
