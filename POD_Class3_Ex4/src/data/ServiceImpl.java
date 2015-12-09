package data;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Random;

import eda.Graph;

public class ServiceImpl implements Service
{
	final private static int TRANSACTIONUPPERBOUND = 10;
	final private static String[] NODELABELS = new String[1 + 'Z'-'A'];
	static
	{
		for (char c = 'A'; c <= 'Z'; c++) 
		{
		    NODELABELS[c-'A']= Character.toString(c);
		}
		
	}
	
	
	public ServiceImpl() throws RemoteException
	{
		UnicastRemoteObject.exportObject(this, 0);
	}

	 
    // se comunica con muchas fuentes de datos y genera un grafo del pais solicitado :-)
	// para no establecer comunicacion lo hicimos random!
	public Graph populate(String country) throws RemoteException 
	{
		   return populate(new Graph(country));
	}

    // se comunica con muchas fuentes de datos y genera un grafo más completo del pais solicitado :-)
	public Graph populate(Graph myG) throws RemoteException 
	{
		   Random rnd= new Random();
		   int transactions= rnd.nextInt(TRANSACTIONUPPERBOUND);
		   
		   for(int rec= 0; rec < transactions; rec++)
		   {
			   // nodo o eje?
			   if (rnd.nextInt(2) == 0)
			   {
				   myG.addNode(String.format("%s", NODELABELS[rnd.nextInt(1 + 'Z' - 'A')]));
			   }
			   else
			   {
				   myG.addEdge(String.format("%s", NODELABELS[rnd.nextInt(1 + 'Z' - 'A')]), 
						       String.format("%s", NODELABELS[rnd.nextInt(1 + 'Z' - 'A')]));
			   }
		   }
		   
		   // se genero el siguiente grafo:
		   System.out.println(myG);
		
		   return myG;
	}

}
