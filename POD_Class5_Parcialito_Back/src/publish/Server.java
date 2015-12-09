package publish;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import utils.Analyzer;
import back.SubastaService;
import back.SubastaServiceImpl;

public class Server {

	public static void main(String[] args) throws RemoteException {
		Analyzer auxi= new Analyzer(args);
		int port = Integer.valueOf( auxi.get("PORT").toString() );
		String hostname = auxi.get("HOSTNAME").toString();
		String service = auxi.get("SERVICE").toString();
		auxi.dump();

        Registry registry = LocateRegistry.getRegistry(hostname, port);
        SubastaService stub = new SubastaServiceImpl();

        registry.rebind(service, stub);
        System.out.println("Service bound");
	}

}
