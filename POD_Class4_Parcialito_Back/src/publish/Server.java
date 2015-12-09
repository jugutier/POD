package publish;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.List;

import utils.Analyzer;
import back.BibliotecaService;
import back.BibliotecaServiceImpl;

public class Server {
	public static void main(String[] args) throws RemoteException {
		Analyzer auxi= new Analyzer(args);
		int port = Integer.valueOf( auxi.get("PORT").toString() );
		String hostname = auxi.get("HOSTNAME").toString();
		String service = auxi.get("SERVICE").toString();
		auxi.dump();

		Registry registry = LocateRegistry.getRegistry(hostname, port);
		BibliotecaService stub = new BibliotecaServiceImpl(getLibros());

		registry.rebind(service, stub);
		System.out.println("Service bound");

	}
	private static List<String[]> getLibros(){
		List<String[]> libros = Arrays.asList(
				new String[] { "3",	"978-0345533487", "A Knight of the Seven Kingdoms", "2015-10-06",  "Martin", "George R. R." }, 
				new String[] { "4", "978-0441294671","God Emperor of Dune", "1987-06-15", "Herbert", "Frank" },
				new String[] { "2", "978-0451210845", "The Gunslinger",	"2003-07-01", "King", "Stephen" }, 
				new String[] { "5",	"978-0307292063", "The Foundation Trilogy", "2011-11-25", "Asimov", "Isaac" },
				new String[] { "4", "978-0765351494", "Sandworms of Dune", "2008-07-01", "Herbert", "Brian" },
				new String[] { "1", "978-0307743657", "The Shining", "2012-06-26", "King", "Stephen" },
				new String[] { "2", "978-0553328257", "The Complete Sherlock Holmes", "1986-10-01", "Conan Doyle", "Arthur" }
				);
		return libros;
	}

}
