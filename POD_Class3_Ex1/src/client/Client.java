package client;
import java.rmi.Naming;

import server.TotalServer;


public class Client {
	public static void main(String[] args) throws Exception
	{
		TotalServer handle= (TotalServer) Naming.lookup("//localhost:1099/Estadistica");

		System.out.println( handle.getMediana(new int[]{10, 20, 5, 6, 9} ));//=> 9
		System.out.println( handle.getMediana(new int[]{80, 10, 40, 31}));// => 35.5
		//System.out.println( handle.getMediana(new int[]{}));// => RuntimeException
		//System.out.println( handle.getMediana(null));// => RuntimeException

		System.out.println( handle.getModa(new int[]{7,2,8,2,8,8} ));// => [8]
		System.out.println( handle.getModa(new int[]{8,7,2,8,8,2,8,2,2} ));// => [2, 8] 
		System.out.println( handle.getModa(new int[]{2,2,4,3,4,3} ));// => null
		//System.out.println( handle.getModa(new int[]{}));// => RuntimeException
		//System.out.println( handle.getModa(null));// => RuntimeException

		System.out.println( handle.getPromedio(new int[]{3, 5, 5, 8, 1 } ));// => 4.4 
		//System.out.println( handle.getPromedio(new int[]{}));// => RuntimeException
		//System.out.println( handle.getPromedio(null));// => RuntimeException

	}
}

