package test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import utils.Analyzer;
import back.BibliotecaService;
import back.Libro;

public class Client {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException, InterruptedException {

		Analyzer auxi= new Analyzer(args);
		Object port = auxi.get("PORT");
		Object hostname = auxi.get("HOSTNAME");
		Object service = auxi.get("SERVICE");
		auxi.dump();

		BibliotecaService handle= (BibliotecaService) Naming.lookup(String.format("//%s:%s/%s", hostname, port, service ) );
		//testear metodos
		Libro l = null;
		try{
			l=handle.prestarLibro("978-0307743657");
			System.out.println(l);
		}catch(Exception e){
			System.out.println("Libro no disponible");
			System.exit(0);
		}
		Thread.sleep(7*1000);
		handle.devolverLibro(l);
		handle.listarCatalogo();
	}

}
