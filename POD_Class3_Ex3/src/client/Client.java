package client;
import java.rmi.Naming;

import math.Service;


public class Client {
	public static void main(String[] args) throws Exception
	{
		Service handle= (Service) Naming.lookup("//localhost:1099/Estadistica");
		for(int i= 0; i < 5; i++)
			System.out.println( handle.getMediana(new int[]{10, 20, 5, 6, 9} ));//=> 9

	}
}

