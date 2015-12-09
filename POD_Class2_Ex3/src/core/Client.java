package core;
import utils.Service;

import java.rmi.Naming;

public class Client {

    public static void main(String[] args) throws Exception
    {
        Service handle= (Service) Naming.lookup("//localhost:1099/SuperServicio");//Same usage as before, but now the server runs remotely.

        System.out.println( handle.farenheitToCelsious(120) );
        System.out.println( handle.getDate());
    }
}

