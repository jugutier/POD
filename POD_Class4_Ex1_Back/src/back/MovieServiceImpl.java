package back;

import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;

import javax.swing.plaf.SliderUI;

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
			      new Movie("Un día perfecto", "Drama"),
			      new Movie("Aloha", "Comedia"),
			      new Movie("Anacleto: Agente secreto", "Aventura"),
			      new Movie("Ático sin ascenso", "Drama"),
			      new Movie("Everest", "Aventura"),
			      new Movie("Capitán Diente de Sable y el tesoro de Lama Rama", "Aventura"),
			      new Movie("Una semana en Córcega", "Comedia"),
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
				aHandle.newInformation(db.get(i).getName() );
			
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
