package core;
import java.rmi.*;

public interface CallBack extends Remote 
{
  /** The server notifies the client for taking a turn */
  public void setToken(char token)  throws RemoteException;
  
  /** The server notifies the client for taking a turn */
  public void takeTurn(boolean turn) throws RemoteException;
  
  /** The server notifies a client of the other player's move */
  public void mark(int row, int column, char token) throws RemoteException;


  /** The server sends a message to be displayed by the client */
  public void notify(String message) throws RemoteException;

  
  /** The server notifies the game is over */
  public void notifyGameOver()  throws RemoteException;
  
  /** The server notifies you are the winner */
  public void notifyYouWon(String cause)  throws RemoteException;
  
  /** The server notifies you are not the winner */
  public void notifyYouLost(String cause)  throws RemoteException;
  

}
