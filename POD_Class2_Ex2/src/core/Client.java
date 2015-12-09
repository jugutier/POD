package core;
import utils.Service;
import utils.ServiceImpl;

public class Client {

	public static void main(String[] args) throws Exception
    {
        Service handle= new ServiceImpl();//The goal of this exercise is to show a regular call to a Client class in the same space as the Server.

		System.out.println( handle.farenheitToCelsious(120) );
		System.out.println( handle.getDate());
    }

}
