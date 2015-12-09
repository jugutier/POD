package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import math.Service;
import math.ServiceImpl2;

public class Server2 {
    public static void main(String[] args)
    {
        try
        {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            Service stub = new ServiceImpl2();

            registry.rebind("Estadistica", stub);
            System.out.println("Service bound");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
